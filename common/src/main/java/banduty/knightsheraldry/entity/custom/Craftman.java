package banduty.knightsheraldry.entity.custom;

import banduty.knightsheraldry.KnightsHeraldry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Craftman extends AbstractVillager {

    public static final ResourceLocation NO_BIOME =
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "no_biome");

    private static final int[] NEXT_LEVEL_XP_THRESHOLD = {10, 70, 150, 250};
    private static final int MAX_LEVEL = 5;
    private static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.HOME,
            MemoryModuleType.JOB_SITE,
            MemoryModuleType.POTENTIAL_JOB_SITE,
            MemoryModuleType.MEETING_POINT,
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.VISIBLE_VILLAGER_BABIES,
            MemoryModuleType.NEAREST_PLAYERS,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM,
            MemoryModuleType.ITEM_PICKUP_COOLDOWN_TICKS,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.INTERACTION_TARGET,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.PATH,
            MemoryModuleType.DOORS_TO_CLOSE,
            MemoryModuleType.NEAREST_BED,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.NEAREST_HOSTILE,
            MemoryModuleType.SECONDARY_JOB_SITE,
            MemoryModuleType.HIDING_PLACE,
            MemoryModuleType.HEARD_BELL_TIME,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.LAST_SLEPT,
            MemoryModuleType.LAST_WOKEN,
            MemoryModuleType.LAST_WORKED_AT_POI,
            MemoryModuleType.GOLEM_DETECTED_RECENTLY
    );

    private static final ImmutableList<SensorType<? extends Sensor<? super Craftman>>> SENSOR_TYPES =
            ImmutableList.of(
                    SensorType.NEAREST_LIVING_ENTITIES,
                    SensorType.NEAREST_PLAYERS,
                    SensorType.NEAREST_ITEMS,
                    SensorType.NEAREST_BED,
                    SensorType.HURT_BY,
                    SensorType.VILLAGER_HOSTILES,
                    SensorType.GOLEM_DETECTED
            );

    private static final Brain.Provider<Craftman> BRAIN_PROVIDER =
            Brain.provider(MEMORY_TYPES, SENSOR_TYPES);

    public Craftman(
            EntityType<? extends AbstractVillager> entityType,
            Level level
    ) {
        super(entityType, level);

        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
        this.getNavigation().setCanFloat(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0);
    }

    public static void init() {
        EntityDataSerializers.registerSerializer(CRAFTMAN_DATA_SERIALIZER);
    }

    public CraftmanData getCraftmanData() {
        return this.entityData.get(DATA_CRAFTMAN_DATA);
    }

    public void setCraftmanData(CraftmanData data) {
        CraftmanData current = this.getCraftmanData();

        if (!current.biomeKey().equals(data.biomeKey())) {
            this.offers = null;
        }

        this.entityData.set(DATA_CRAFTMAN_DATA, data);
    }

    private static boolean canLevelUp(int level, int xp, int maxLevel) {
        return level >= 1
                && level < maxLevel
                && level <= NEXT_LEVEL_XP_THRESHOLD.length
                && xp >= NEXT_LEVEL_XP_THRESHOLD[level - 1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Brain<Craftman> getBrain() {
        return (Brain<Craftman>) super.getBrain();
    }

    @Override
    protected Brain.Provider<Craftman> brainProvider() {
        return BRAIN_PROVIDER;
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        Brain<Craftman> brain = this.brainProvider().makeBrain(dynamic);
        registerBrainActivities(brain);
        return brain;
    }

    public void refreshBrain(ServerLevel serverLevel) {
        Brain<Craftman> brain = this.getBrain();

        brain.stopAll(serverLevel, this);
        this.brain = brain.copyWithoutBehaviors();

        registerBrainActivities(this.getBrain());
    }

    private void registerBrainActivities(Brain<Craftman> brain) {
        brain.setSchedule(Schedule.VILLAGER_DEFAULT);

        brain.addActivity(Activity.CORE, ImmutableList.of(
                Pair.of(0, new Swim(0.8F)),
                Pair.of(0, InteractWithDoor.create()),
                Pair.of(0, panicTrigger()),
                Pair.of(0, new LookAtTargetSink(45, 90)),
                Pair.of(0, WakeUp.create()),
                Pair.of(0, ReactToBell.create()),
                Pair.of(0, SetRaidStatus.create()),
                Pair.of(1, new MoveToTargetSink()),
                Pair.of(10, AcquirePoi.create(
                        poiType -> poiType.is(PoiTypes.HOME),
                        MemoryModuleType.HOME,
                        false,
                        Optional.of((byte) 14)
                ))
        ));

        brain.addActivity(Activity.REST, ImmutableList.of(
                Pair.of(2, setWalkTargetFromHome()),
                Pair.of(3, new SleepInBed()),
                Pair.of(4, ValidateNearbyPoi.create(
                        poiType -> poiType.is(PoiTypes.HOME),
                        MemoryModuleType.HOME
                )),
                Pair.of(99, UpdateActivityFromSchedule.create())
        ));

        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(99, UpdateActivityFromSchedule.create())
        ));

        brain.addActivity(Activity.WORK, ImmutableList.of(
                Pair.of(99, UpdateActivityFromSchedule.create())
        ));

        brain.addActivity(Activity.MEET, ImmutableList.of(
                Pair.of(99, UpdateActivityFromSchedule.create())
        ));

        float panicSpeed = 0.75F;

        brain.addActivity(Activity.PANIC, ImmutableList.of(
                Pair.of(0, VillagerCalmDown.create()),
                Pair.of(1, SetWalkTargetAwayFrom.entity(MemoryModuleType.NEAREST_HOSTILE, panicSpeed, 6, false)),
                Pair.of(1, SetWalkTargetAwayFrom.entity(MemoryModuleType.HURT_BY_ENTITY, panicSpeed, 6, false)),
                Pair.of(3, VillageBoundRandomStroll.create(panicSpeed, 2, 2))
        ));
        brain.addActivity(Activity.HIDE, ImmutableList.of(
                Pair.of(0, SetHiddenState.create(15, 3)),
                Pair.of(1, LocateHidingPlace.create(32, panicSpeed, 3))
        ));
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.setActiveActivityIfPossible(Activity.IDLE);

        brain.updateActivityFromSchedule(
                this.level().getDayTime(),
                this.level().getGameTime()
        );
    }

    private static OneShot<Craftman> panicTrigger() {
        return new OneShot<>() {
            @Override
            public boolean trigger(ServerLevel level, Craftman entity, long gameTime) {
                Brain<Craftman> brain = entity.getBrain();

                if (brain.isActive(Activity.HIDE)) {
                    return false; // don't override an active hide
                }

                boolean threatened = brain.hasMemoryValue(MemoryModuleType.HURT_BY)
                        || brain.hasMemoryValue(MemoryModuleType.NEAREST_HOSTILE);

                if (!threatened) {
                    return false;
                }

                if (entity.isSleeping()) {
                    entity.stopSleeping();
                }

                if (!brain.isActive(Activity.PANIC)) {
                    brain.eraseMemory(MemoryModuleType.PATH);
                    brain.eraseMemory(MemoryModuleType.WALK_TARGET);
                    brain.eraseMemory(MemoryModuleType.LOOK_TARGET);
                    brain.eraseMemory(MemoryModuleType.BREED_TARGET);
                    brain.eraseMemory(MemoryModuleType.INTERACTION_TARGET);
                }

                brain.setActiveActivityIfPossible(Activity.PANIC);

                boolean hasHome = brain.hasMemoryValue(MemoryModuleType.HOME);
                Activity next = hasHome ? Activity.HIDE : Activity.PANIC;

                if (!brain.isActive(next)) {
                    brain.eraseMemory(MemoryModuleType.PATH);
                    brain.eraseMemory(MemoryModuleType.WALK_TARGET);
                    brain.eraseMemory(MemoryModuleType.LOOK_TARGET);
                }

                brain.setActiveActivityIfPossible(next);
                return true;
            }
        };
    }

    private static OneShot<Craftman> calmDown() {
        return new OneShot<>() {
            @Override
            public boolean trigger(ServerLevel level, Craftman entity, long gameTime) {
                Brain<Craftman> brain = entity.getBrain();

                boolean nearbyAttacker = brain.getMemory(MemoryModuleType.HURT_BY_ENTITY)
                        .filter(e -> e.distanceToSqr(entity) <= 36.0D)
                        .isPresent();

                boolean threatened = brain.hasMemoryValue(MemoryModuleType.HURT_BY)
                        || brain.hasMemoryValue(MemoryModuleType.NEAREST_HOSTILE)
                        || nearbyAttacker;

                if (!threatened) {
                    brain.eraseMemory(MemoryModuleType.HURT_BY);
                    brain.eraseMemory(MemoryModuleType.HURT_BY_ENTITY);
                    brain.updateActivityFromSchedule(level.getDayTime(), level.getGameTime());
                }

                return true;
            }
        };
    }

    private static OneShot<Craftman> setWalkTargetFromHome() {
        return new OneShot<>() {
            @Override
            public boolean trigger(
                    ServerLevel level,
                    Craftman entity,
                    long gameTime
            ) {
                Brain<Craftman> brain = entity.getBrain();

                Optional<GlobalPos> homeMemory =
                        brain.getMemory(MemoryModuleType.HOME);

                if (homeMemory.isEmpty()
                        || !homeMemory.get().dimension().equals(level.dimension())
                        || brain.hasMemoryValue(MemoryModuleType.WALK_TARGET)) {
                    return false;
                }

                BlockPos homePos = homeMemory.get().pos();

                if (entity.blockPosition().distSqr(homePos) <= 4.0D) {
                    return false;
                }

                Optional<Long> unreachable =
                        brain.getMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);

                if (unreachable.isPresent()
                        && gameTime - unreachable.get() > 1200L) {
                    brain.eraseMemory(MemoryModuleType.HOME);
                    brain.eraseMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
                    return false;
                }

                brain.setMemory(
                        MemoryModuleType.WALK_TARGET,
                        new WalkTarget(homePos, 0.5F, 1)
                );

                return true;
            }
        };
    }

    @Nullable
    private CraftmanTradeManager.TradeDataContainer getTradeContainer() {
        Map<String, CraftmanTradeManager.TradeDataContainer> biomeMap =
                CraftmanTradeManager.PROFESSION_TRADES.get("craftman");

        if (biomeMap == null) {
            return null;
        }

        ResourceLocation location = getCraftmanData().biomeKey();

        String biomeKey = location.getNamespace().equals("minecraft")
                ? location.getPath()
                : location.getNamespace() + "_" + location.getPath();

        CraftmanTradeManager.TradeDataContainer exact = biomeMap.get(biomeKey);

        if (exact != null) {
            return exact;
        }

        for (Map.Entry<String, CraftmanTradeManager.TradeDataContainer> entry : biomeMap.entrySet()) {
            if (entry.getKey().replace(':', '_').equals(biomeKey)) {
                return entry.getValue();
            }
        }

        return biomeMap.get("default");
    }

    private int getMaxAvailableLevel() {
        CraftmanTradeManager.TradeDataContainer container = getTradeContainer();

        if (container == null || container.trades.isEmpty()) {
            return MAX_LEVEL;
        }

        int highestDefinedLevel = container.trades.stream()
                .mapToInt(CraftmanTradeManager.DatapackTrade::level)
                .max()
                .orElse(MAX_LEVEL);

        return Math.min(MAX_LEVEL, highestDefinedLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            return;
        }

        long time = this.level().getDayTime() % 24000L;

        if (this.isSleeping() && (time < 12000L || time >= 23000L)) {
            this.stopSleeping();
        }

        CraftmanData data = this.getCraftmanData();

        if (data.biomeKey().equals(NO_BIOME)) {
            ResourceLocation biomeLocation = this.level()
                    .getBiome(this.blockPosition())
                    .unwrapKey()
                    .map(ResourceKey::location)
                    .orElse(Biomes.PLAINS.location());

            setCraftmanData(new CraftmanData(
                    biomeLocation,
                    data.level(),
                    data.xp()
            ));

            this.updateTrades();
        } else if (this.getOffers().isEmpty()) {
            this.updateTrades();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_CRAFTMAN_DATA, new CraftmanData(NO_BIOME, 1, 0));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        CraftmanData.CODEC.encodeStart(NbtOps.INSTANCE, this.getCraftmanData())
                .result()
                .ifPresent(tag -> compound.put("CraftmanData", tag));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("CraftmanData", 10)) {
            DataResult<CraftmanData> result = CraftmanData.CODEC.parse(
                    NbtOps.INSTANCE,
                    compound.get("CraftmanData")
            );

            result.result().ifPresent(data ->
                    this.entityData.set(DATA_CRAFTMAN_DATA, data)
            );
        }

        if (this.level() instanceof ServerLevel serverLevel) {
            this.refreshBrain(serverLevel);
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            MobSpawnType spawnType,
            @Nullable SpawnGroupData spawnGroupData
    ) {
        SpawnGroupData result = super.finalizeSpawn(
                level,
                difficulty,
                spawnType,
                spawnGroupData
        );

        CraftmanData data = this.getCraftmanData();

        if (data.biomeKey().equals(NO_BIOME)) {
            ResourceLocation biomeLocation = level.getBiome(this.blockPosition())
                    .unwrapKey()
                    .map(ResourceKey::location)
                    .orElse(Biomes.PLAINS.location());

            setCraftmanData(new CraftmanData(biomeLocation, 1, 0));
        }

        this.setPersistenceRequired();
        this.updateTrades();
        return result;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
       /* this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Evoker.class, 12.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vindicator.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vex.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Pillager.class, 15.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Illusioner.class, 12.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zoglin.class, 10.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5));*/
        this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.35) {
            @Override
            public boolean canUse() {
                return !Craftman.this.getBrain().isActive(Activity.REST)
                        && super.canUse();
            }
        });
        this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.isAlive() || this.isBaby() || this.isTrading() || this.isSleeping()) {
            return super.mobInteract(player, hand);
        }

        if (hand == InteractionHand.MAIN_HAND) {
            player.awardStat(Stats.TALKED_TO_VILLAGER);
        }

        if (!this.level().isClientSide) {
            if (this.getOffers().isEmpty()) {
                return InteractionResult.CONSUME;
            }

            this.setTradingPlayer(player);
            this.openTradingScreen(
                    player,
                    this.getDisplayName(),
                    this.getCraftmanData().level()
            );
        }

        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    protected void customServerAiStep() {
        if (this.level() instanceof ServerLevel serverLevel) {
            this.getBrain().tick(serverLevel, this);
        }

        super.customServerAiStep();
    }

    @Override
    public void startSleeping(BlockPos pos) {
        super.startSleeping(pos);

        this.getBrain().setMemory(
                MemoryModuleType.LAST_SLEPT,
                this.level().getGameTime()
        );

        this.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        this.getBrain().eraseMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
    }

    @Override
    public void stopSleeping() {
        super.stopSleeping();

        this.getBrain().setMemory(
                MemoryModuleType.LAST_WOKEN,
                this.level().getGameTime()
        );
    }

    @Override
    public void die(DamageSource cause) {
        if (this.level() instanceof ServerLevel serverLevel) {
            this.getBrain().getMemory(MemoryModuleType.HOME).ifPresent(home -> {
                ServerLevel homeLevel = serverLevel.getServer().getLevel(home.dimension());

                if (homeLevel != null) {
                    homeLevel.getPoiManager().getType(home.pos())
                            .filter(poiType -> poiType.is(PoiTypes.HOME))
                            .ifPresent(poiType ->
                                    homeLevel.getPoiManager().release(home.pos())
                            );
                }
            });
        }

        super.die(cause);
    }

    @Override
    protected void updateTrades() {
        if (this.level().isClientSide) {
            return;
        }

        if (this.getOffers().isEmpty()) {
            addTradesForCurrentLevel(2);
        }
    }

    private void addTradesForCurrentLevel(int count) {
        MerchantOffers offers = this.getOffers();

        CraftmanTradeManager.TradeDataContainer container = getTradeContainer();

        if (container == null) {
            return;
        }

        addAlwaysAvailableTrades(offers, container);

        RandomSource random = this.getRandom();
        int currentLevel = this.getCraftmanData().level();

        for (int i = 0; i < count; i++) {
            List<CraftmanTradeManager.DatapackTrade> validTrades = container.trades.stream()
                    .filter(trade -> trade.level() > 0)
                    .filter(trade -> trade.level() <= currentLevel)
                    .filter(trade -> !trade.result().isEmpty())
                    .filter(trade -> offers.stream().noneMatch(
                            offer -> offer.getResult().is(trade.result().getItem())
                    ))
                    .toList();

            if (validTrades.isEmpty()) {
                break;
            }

            CraftmanTradeManager.DatapackTrade trade =
                    validTrades.get(random.nextInt(validTrades.size()));

            offers.add(new MerchantOffer(
                    trade.costA(),
                    Optional.ofNullable(trade.costB()),
                    trade.result().copy(),
                    trade.maxUses(),
                    trade.xp(),
                    trade.priceMultiplier()
            ));
        }
    }

    private void addAlwaysAvailableTrades(
            MerchantOffers offers,
            CraftmanTradeManager.TradeDataContainer container
    ) {
        container.trades.stream()
                .filter(trade -> trade.level() == 0)
                .filter(trade -> !trade.result().isEmpty())
                .filter(trade -> offers.stream().noneMatch(
                        offer -> offer.getResult().is(trade.result().getItem())
                ))
                .forEach(trade -> offers.add(new MerchantOffer(
                        trade.costA(),
                        Optional.ofNullable(trade.costB()),
                        trade.result().copy(),
                        trade.maxUses(),
                        trade.xp(),
                        trade.priceMultiplier()
                )));
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (!offer.shouldRewardExp() || this.level().isClientSide) {
            return;
        }

        int xpGained = offer.getXp();

        this.level().addFreshEntity(new ExperienceOrb(
                this.level(),
                this.getX(),
                this.getY() + 0.5D,
                this.getZ(),
                xpGained
        ));

        CraftmanData data = this.getCraftmanData();
        int maxLevel = getMaxAvailableLevel();

        if (data.level() >= maxLevel) {
            return;
        }

        int newXp = data.xp() + xpGained;
        int newLevel = data.level();

        while (canLevelUp(newLevel, newXp, maxLevel)) {
            newLevel++;
        }

        if (newLevel == data.level() && newXp == data.xp()) {
            return;
        }

        setCraftmanData(new CraftmanData(
                data.biomeKey(),
                newLevel,
                newXp
        ));

        if (newLevel > data.level()) {
            addTradesForCurrentLevel(2);
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    public static final EntityDataSerializer<CraftmanData> CRAFTMAN_DATA_SERIALIZER =
            new EntityDataSerializer<>() {
                private final StreamCodec<RegistryFriendlyByteBuf, CraftmanData> CODEC =
                        StreamCodec.of(
                                (buffer, data) -> {
                                    ResourceLocation.STREAM_CODEC.encode(buffer, data.biomeKey());
                                    buffer.writeVarInt(data.level());
                                    buffer.writeVarInt(data.xp());
                                },
                                buffer -> new CraftmanData(
                                        ResourceLocation.STREAM_CODEC.decode(buffer),
                                        buffer.readVarInt(),
                                        buffer.readVarInt()
                                )
                        );

                @Override
                public StreamCodec<? super RegistryFriendlyByteBuf, CraftmanData> codec() {
                    return CODEC;
                }

                @Override
                public CraftmanData copy(CraftmanData value) {
                    return value;
                }
            };

    private static final EntityDataAccessor<CraftmanData> DATA_CRAFTMAN_DATA =
            SynchedEntityData.defineId(
                    Craftman.class,
                    CRAFTMAN_DATA_SERIALIZER
            );

    public record CraftmanData(ResourceLocation biomeKey, int level, int xp) {
        public static final Codec<CraftmanData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        ResourceLocation.CODEC.fieldOf("biome_key")
                                .forGetter(CraftmanData::biomeKey),
                        Codec.INT.fieldOf("level")
                                .forGetter(CraftmanData::level),
                        Codec.INT.fieldOf("xp")
                                .forGetter(CraftmanData::xp)
                ).apply(instance, CraftmanData::new)
        );
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isSleeping()) {
            return null;
        } else {
            return this.isTrading() ? SoundEvents.VILLAGER_TRADE : SoundEvents.VILLAGER_AMBIENT;
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }
}
