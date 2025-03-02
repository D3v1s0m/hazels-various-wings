package hazelclover.hazelsvariouswings.item;

import hazelclover.hazelsvariouswings.HazelsVariousWings;
import hazelclover.hazelsvariouswings.config.WingsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item FLIMSY_WINGS = registerItem("flimsy_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.flimsyWings));
    public static final Item FLEDGLING_WINGS = registerItem("fledgling_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.fledglingWings));
    public static final Item PHANTOM_WINGS = registerItem("phantom_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.phantomWings));
    public static final Item LEAF_WINGS = registerItem("leaf_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.leafWings));
    public static final Item CORAL_WINGS = registerItem("coral_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.coralWings));
    public static final Item BEE_WINGS = registerItem("bee_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.beeWings));
    public static final Item MOTH_WINGS = registerItem("moth_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.mothWings));
    public static final Item DEMON_WINGS = registerItem("demon_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.demonWings));
    public static final Item BLAZE_WINGS = registerItem("blaze_wings", new WingsItem(new WingsItem.Settings().rarity(Rarity.UNCOMMON), WingsConfig.INSTANCE.blazeWings));
    public static final Item GEM_WINGS = registerItem("gem_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.gemWings));
    public static final Item VOID_WINGS = registerItem("void_wings", new WingsItem(new WingsItem.Settings().rarity(Rarity.UNCOMMON), WingsConfig.INSTANCE.voidWings));
    public static final Item ELYTRA_WINGS = registerItem("elytra_wings", new WingsItem(new WingsItem.Settings().rarity(Rarity.UNCOMMON), WingsConfig.INSTANCE.elytraWings));
    public static final Item PRISTINE_WINGS = registerItem("pristine_wings", new WingsItem(new WingsItem.Settings().fireproof().rarity(Rarity.RARE), WingsConfig.INSTANCE.pristineWings));
    public static final Item NEBULOUS_WINGS = registerItem("nebulous_wings", new WingsItem(new WingsItem.Settings().rarity(Rarity.RARE), WingsConfig.INSTANCE.nebulousWings));

    public static final Item ASLERIX_WINGS = registerItem("aslerix_wings", new WingsItem(new WingsItem.Settings().fireproof().rarity(Rarity.RARE), WingsConfig.INSTANCE.aslerixWings));
    public static final Item HAZEL_WINGS = registerItem("hazel_wings", new WingsItem(new WingsItem.Settings().fireproof().rarity(Rarity.RARE), WingsConfig.INSTANCE.hazelWings));

    public static final Item MECHANICAL_WINGS = registerItem("mechanical_wings", new WingsItem(new WingsItem.Settings(), WingsConfig.INSTANCE.mechanicalWings));
    public static final Item BRASS_WINGS = registerItem("brass_wings", new WingsItem(new WingsItem.Settings().rarity(Rarity.RARE), WingsConfig.INSTANCE.brassWings));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(HazelsVariousWings.MOD_ID, name), item);
    }

    public static void registerModItems() {
        HazelsVariousWings.LOGGER.info("Registering Mod Items for " + HazelsVariousWings.MOD_ID);
        //HazelsVariousWings.LOGGER.info("a " + );
    }
}
