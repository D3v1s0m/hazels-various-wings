package hazelclover.hazelsvariouswings.item;

import hazelclover.hazelsvariouswings.HazelsVariousWings;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item FLIMSY_WINGS = registerItem("flimsy_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(55), 0.8f, 2f, 0.22f, 3f, 0.16f, false, false));
    public static final Item FLEDGLING_WINGS = registerItem("fledgling_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item PHANTOM_WINGS = registerItem("phantom_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item LEAF_WINGS = registerItem("leaf_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item CORAL_WINGS = registerItem("coral_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item BEE_WINGS = registerItem("bee_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item DEMON_WINGS = registerItem("demon_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item BLAZE_WINGS = registerItem("blaze_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item GEM_WINGS = registerItem("gem_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item VOID_WINGS = registerItem("void_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item ELYTRA_WINGS = registerItem("elytra_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item PRISTINE_WINGS = registerItem("pristine_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item NEBULOUS_WINGS = registerItem("nebulous_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item MECHANICAL_WINGS = registerItem("mechanical_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));
    public static final Item BRASS_WINGS = registerItem("brass_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2200), 0.9f, 3f, 0.4f, 8f, 0.05f, false, false));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(HazelsVariousWings.MOD_ID, name), item);
    }

    public static void registerModItems() {
        HazelsVariousWings.LOGGER.info("Registering Mod Items for " + HazelsVariousWings.MOD_ID);
    }
}
