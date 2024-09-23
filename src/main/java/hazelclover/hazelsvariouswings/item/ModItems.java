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
    public static final Item FLIMSY_WINGS = registerItem("flimsy_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(54), 0.7f, 0.3f, 0.2f, 0f, 0f, false, false));
    public static final Item FLEDGLING_WINGS = registerItem("fledgling_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(47), 0.8f, 0f, 0f, 0.3f, 0.2f, false, false));
    public static final Item PHANTOM_WINGS = registerItem("phantom_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(130), 1f, 0.3f, 0.29f, 0f, 0f, false, false));
    public static final Item LEAF_WINGS = registerItem("leaf_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(122), 0.9f, 0.3f, 0.28f, 0.6f, 0.2f, false, false));
    public static final Item CORAL_WINGS = registerItem("coral_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(260), 1f, 0.3f, 0.22f, 1f, 0.22f, true, false));
    public static final Item BEE_WINGS = registerItem("bee_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(235), 0.8f, 0.36f, 0.22f, 1.6f, 0.18f, false, false));
    public static final Item DEMON_WINGS = registerItem("demon_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(145), 1.1f, 0.35f, 0.22f, 1.4f, 0.2f, false, false));
    public static final Item BLAZE_WINGS = registerItem("blaze_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(478), 1.3f, 0.5f, 0.3f, 1.9f, 0.17f, false, false));
    public static final Item GEM_WINGS = registerItem("gem_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(811), 1.2f, 0.81f, 0.28f, 0.8f, 0.2f, false, false));
    public static final Item VOID_WINGS = registerItem("void_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(1100), 1.3f, 0.7f, 0.34f, 1.6f, 0.14f, false, false));
    public static final Item ELYTRA_WINGS = registerItem("elytra_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(1620), 1.1f, 0f, 0f, 0f, 0f, false, true));
    public static final Item PRISTINE_WINGS = registerItem("pristine_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2120).fireproof(), 1.4f, 1.4f, 0.4f, 4f, 0.16f, true, true));
    public static final Item NEBULOUS_WINGS = registerItem("nebulous_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2034), 1.4f, 1.7f, 0.5f, 9999f, 0.1f, true, false));
    public static final Item MECHANICAL_WINGS = registerItem("mechanical_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(220), 1.1f, 0.5f, 0.26f, 1.2f, 0.17f, false, false));
    public static final Item BRASS_WINGS = registerItem("brass_wings", new WingsItem(new WingsItem.Settings().maxCount(1).maxDamage(2300), 1.4f, 9f, 0.3f, 9999f, 0.2f, true, false));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(HazelsVariousWings.MOD_ID, name), item);
    }

    public static void registerModItems() {
        HazelsVariousWings.LOGGER.info("Registering Mod Items for " + HazelsVariousWings.MOD_ID);
    }
}
