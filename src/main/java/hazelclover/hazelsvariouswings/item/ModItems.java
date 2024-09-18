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
    public static final Item FLIMSY_WINGS = registerItem("flimsy_wings", new WingsItem(new WingsItem.Settings(), 0.5f));
    public static final Item FLEDGLING_WINGS = registerItem("fledgling_wings", new WingsItem(new WingsItem.Settings(), 2f));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(HazelsVariousWings.MOD_ID, name), item);
    }

    public static void registerModItems() {
        HazelsVariousWings.LOGGER.info("Registering Mod Items for " + HazelsVariousWings.MOD_ID);
    }
}
