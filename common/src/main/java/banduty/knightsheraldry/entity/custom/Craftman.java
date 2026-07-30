package banduty.knightsheraldry.entity.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
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
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.entity.player.Player;
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
    private static final EntityDataAccessor<CraftmanData> DATA_VILLAGER_DATA;
    private boolean variantInitialized = false;

    public Craftman(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0);
    }

    public void setCraftmanData(CraftmanData data) {
        CraftmanData villagerdata = this.getCraftmanData();
        if (!villagerdata.biomeKey().equals(data.biomeKey())) {
            this.offers = null;
        }

        this.entityData.set(DATA_VILLAGER_DATA, data);
    }

    public CraftmanData getCraftmanData() {
        return this.entityData.get(DATA_VILLAGER_DATA);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide && !this.variantInitialized) {
            this.variantInitialized = true;

            ResourceLocation biomeLoc = this.level().getBiome(this.blockPosition())
                    .unwrapKey()
                    .map(ResourceKey::location)
                    .orElse(Biomes.PLAINS.location());

            setCraftmanData(new CraftmanData(biomeLoc));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("CraftmanData", 10)) {
            DataResult<CraftmanData> villagerData = CraftmanData.CODEC.parse(NbtOps.INSTANCE, compound.get("CraftmanData"));
            if (villagerData.isSuccess()) this.entityData.set(DATA_VILLAGER_DATA, villagerData.getOrThrow());
        }
        this.variantInitialized = compound.getBoolean("VariantInitialized");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        CraftmanData.CODEC.encodeStart(NbtOps.INSTANCE, this.getCraftmanData())
                .result()
                .ifPresent(tag -> compound.put("CraftmanData", tag));
        compound.putBoolean("VariantInitialized", this.variantInitialized);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        ResourceLocation biomeLoc = level.getBiome(this.blockPosition())
                .unwrapKey()
                .map(ResourceKey::location)
                .orElse(Biomes.PLAINS.location());

        setCraftmanData(new CraftmanData(biomeLoc));
        this.setPersistenceRequired();
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VILLAGER_DATA, new CraftmanData(Biomes.PLAINS.location()));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isAlive() && !this.isTrading() && !this.isBaby()) {
            if (hand == InteractionHand.MAIN_HAND) {
                player.awardStat(net.minecraft.stats.Stats.TALKED_TO_VILLAGER);
            }
            if (!this.level().isClientSide) {
                this.setTradingPlayer(player);
                this.openTradingScreen(player, this.getDisplayName(), 1);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected void updateTrades() {
        if (this.level().isClientSide) return;

        MerchantOffers offers = this.getOffers();
        offers.clear();

        Map<String, CraftmanTradeManager.TradeDataContainer> biomeMap = CraftmanTradeManager.PROFESSION_TRADES.get("craftman");
        if (biomeMap == null) return;

        ResourceLocation loc = getCraftmanData().biomeKey();
        String biomeKey = loc.getNamespace().equals("minecraft")
                ? loc.getPath()
                : loc.getNamespace() + "_" + loc.getPath();

        CraftmanTradeManager.TradeDataContainer container = biomeMap.getOrDefault(biomeKey, biomeMap.get("default"));
        if (container == null) return;

        RandomSource random = this.getRandom();
        for (int i = 0; i < 2; i++) {
            List<CraftmanTradeManager.DatapackTrade> validTrades = container.trades.stream()
                    .filter(t -> offers.stream().noneMatch(o -> o.getResult().is(t.result().getItem())))
                    .toList();

            if (validTrades.isEmpty()) break;

            int totalWeight = validTrades.stream().mapToInt(CraftmanTradeManager.DatapackTrade::weight).sum();
            if (totalWeight <= 0) break;

            int randomIndex = random.nextInt(totalWeight);
            int currentWeightSum = 0;

            for (CraftmanTradeManager.DatapackTrade trade : validTrades) {
                currentWeightSum += trade.weight();
                if (randomIndex < currentWeightSum) {
                    offers.add(new MerchantOffer(
                            trade.costA(),
                            Optional.ofNullable(trade.costB()),
                            trade.result().copy(),
                            trade.maxUses(),
                            trade.xp(),
                            trade.priceMultiplier()
                    ));
                    break;
                }
            }
        }
    }

    @Override
    public void rewardTradeXp(MerchantOffer offer) {
        if (offer.shouldRewardExp()) {
            int xp = offer.getXp();
            this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5D, this.getZ(), xp));
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    private static final EntityDataSerializer<CraftmanData> CRAFTMAN_DATA_SERIALIZER = new EntityDataSerializer<>() {
        private final StreamCodec<RegistryFriendlyByteBuf, CraftmanData> CODEC = StreamCodec.of(
                (buffer, data) -> ResourceLocation.STREAM_CODEC.encode(buffer, data.biomeKey()),
                buffer -> new CraftmanData(ResourceLocation.STREAM_CODEC.decode(buffer))
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

    static {
        EntityDataSerializers.registerSerializer(CRAFTMAN_DATA_SERIALIZER);
        DATA_VILLAGER_DATA = SynchedEntityData.defineId(Craftman.class, CRAFTMAN_DATA_SERIALIZER);
    }

    public record CraftmanData(ResourceLocation biomeKey) {
        public static final Codec<CraftmanData> CODEC = ResourceLocation.CODEC.xmap(CraftmanData::new, CraftmanData::biomeKey);
    }
}