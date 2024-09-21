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
                        entries.add(ModItems.PHANTOM_WINGS);
                        entries.add(ModItems.LEAF_WINGS);
                        entries.add(ModItems.CORAL_WINGS);
                        entries.add(ModItems.BEE_WINGS);
                        entries.add(ModItems.DEMON_WINGS);
                        entries.add(ModItems.BLAZE_WINGS);
                        entries.add(ModItems.GEM_WINGS);
                        entries.add(ModItems.VOID_WINGS);
                        entries.add(ModItems.ELYTRA_WINGS);
                        entries.add(ModItems.PRISTINE_WINGS);
                        entries.add(ModItems.NEBULOUS_WINGS);
                        entries.add(ModItems.MECHANICAL_WINGS);
                        entries.add(ModItems.BRASS_WINGS);
                    }).build());

    public static void registerItemGroups() {
        HazelsVariousWings.LOGGER.info("Registering Item Groups for " + HazelsVariousWings.MOD_ID);
    }
}
