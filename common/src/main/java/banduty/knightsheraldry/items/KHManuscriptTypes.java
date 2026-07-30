package banduty.knightsheraldry.items;

import banduty.stoneycore.items.custom.manuscript.ManuscriptType;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public enum KHManuscriptTypes implements ManuscriptType {
    DAGGER("dagger", KHItems.MANUSCRIPT_DAGGER, KHItems.DAGGER_HEAD, KHItems.TONGS_DAGGER),
    SWORD("sword", KHItems.MANUSCRIPT_SWORD, KHItems.SWORD_HEAD, KHItems.TONGS_SWORD),
    AXE("axe", KHItems.MANUSCRIPT_AXE, KHItems.AXE_HEAD, KHItems.TONGS_AXE),
    HAMMER("hammer", KHItems.MANUSCRIPT_HAMMER, KHItems.HAMMER_HEAD, KHItems.TONGS_HAMMER),
    MACE("mace", KHItems.MANUSCRIPT_MACE, KHItems.MACE_HEAD, KHItems.TONGS_MACE),
    HALBERD("halberd", KHItems.MANUSCRIPT_HALBERD, KHItems.HALBERD_HEAD, KHItems.TONGS_HALBERD),
    LONGSWORD("longsword", KHItems.MANUSCRIPT_LONGSWORD, KHItems.LONGSWORD_HEAD, KHItems.TONGS_LONGSWORD),
    GREATSWORD("greatsword", KHItems.MANUSCRIPT_GREATSWORD, KHItems.GREATSWORD_HEAD, KHItems.TONGS_GREATSWORD),
    SPEAR("spear", KHItems.MANUSCRIPT_SPEAR, KHItems.SPEAR_HEAD, KHItems.TONGS_SPEAR),
    PITCHFORK("pitchfork", KHItems.MANUSCRIPT_PITCHFORK, KHItems.PITCHFORK_HEAD, KHItems.TONGS_PITCHFORK),

    BARBUTE("barbute", KHItems.MANUSCRIPT_BARBUTE, KHItems.BARBUTE_HEAD, KHItems.TONGS_BARBUTE),
    BASCINET("bascinet", KHItems.MANUSCRIPT_BASCINET, KHItems.BASCINET_HEAD, KHItems.TONGS_BASCINET),
    KETTLE("kettle", KHItems.MANUSCRIPT_KETTLE, KHItems.KETTLE_HEAD, KHItems.TONGS_KETTLE),
    NASAL("nasal", KHItems.MANUSCRIPT_NASAL, KHItems.NASAL_HEAD, KHItems.TONGS_NASAL),
    BURGONET("burgonet", KHItems.MANUSCRIPT_BURGONET, KHItems.BURGONET_HEAD, KHItems.TONGS_BURGONET),
    SALLET("sallet", KHItems.MANUSCRIPT_SALLET, KHItems.SALLET_HEAD, KHItems.TONGS_SALLET),
    ARMET("armet", KHItems.MANUSCRIPT_ARMET, KHItems.ARMET_HEAD, KHItems.TONGS_ARMET),
    CAGE("cage", KHItems.MANUSCRIPT_CAGE, KHItems.CAGE_HEAD, KHItems.TONGS_CAGE),
    GREAT_HELMET("great_helmet", KHItems.MANUSCRIPT_GREAT_HELMET, KHItems.GREAT_HELMET_HEAD, KHItems.TONGS_GREAT_HELMET),
    CLOSE_HELMET("close_helmet", KHItems.MANUSCRIPT_CLOSE_HELMET, KHItems.CLOSE_HELMET_HEAD, KHItems.TONGS_CLOSE_HELMET),
    FROGMOUTH("frogmouth", KHItems.MANUSCRIPT_FROGMOUTH, KHItems.FROGMOUTH_HEAD, KHItems.TONGS_FROGMOUTH),
    MAXIMILIAN("maximilian", KHItems.MANUSCRIPT_MAXIMILIAN, KHItems.MAXIMILIAN_HEAD, KHItems.TONGS_MAXIMILIAN);

    private final String name;
    private final Supplier<Item> manuscriptSupplier;
    private final Supplier<Item> hotIronSupplier;
    private final Supplier<Item> tongsSupplier;

    KHManuscriptTypes(String name, Supplier<Item> manuscriptSupplier, Supplier<Item> hotIronSupplier, Supplier<Item> tongsSupplier) {
        this.name = name;
        this.manuscriptSupplier = manuscriptSupplier;
        this.hotIronSupplier = hotIronSupplier;
        this.tongsSupplier = tongsSupplier;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    @Override
    public Item getManuscriptItem() {
        return this.manuscriptSupplier != null ? this.manuscriptSupplier.get() : null;
    }

    @Override
    public Item getHotIronItem() {
        return this.hotIronSupplier != null ? this.hotIronSupplier.get() : null;
    }

    @Override
    public Item getTongsItem() {
        return this.tongsSupplier != null ? this.tongsSupplier.get() : null;
    }

    public static void registerAll() {
        for (KHManuscriptTypes type : values()) {
            ManuscriptType.register(type);
        }
    }
}