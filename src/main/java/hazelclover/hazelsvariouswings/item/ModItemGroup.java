package hazelclover.hazelsvariouswings.item;

import hazelclover.hazelsvariouswings.HazelsVariousWings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup HazlesVariousWingsItemGroup = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(HazelsVariousWings.MOD_ID, "hazels-various-wings"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.hazels-various-wings-item-group"))
                    .icon(() -> new ItemStack(ModItems.FLIMSY_WINGS)).entries((displayContext, entries) -> {
                        entries.add(ModItems.FLIMSY_WINGS);
                        entries.add(ModItems.FLEDGLING_WINGS);

                    }).build());

    public static void registerItemGroups() {
        HazelsVariousWings.LOGGER.info("Registering Item Groups for " + HazelsVariousWings.MOD_ID);
    }
}
