package hazelclover.hazelsvariouswings.item;

import hazelclover.hazelsvariouswings.HazelsVariousWings;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModTags extends FabricTagProvider<Item> {
    public static final TagKey<Item> WINGS = TagKey.of(RegistryKeys.ITEM, Identifier.of(HazelsVariousWings.MOD_ID, "wings"));

    public ModTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(WINGS)
                .add(ModItems.FLIMSY_WINGS)
                .add(ModItems.FLEDGLING_WINGS)
                .add(ModItems.PHANTOM_WINGS)
                .add(ModItems.LEAF_WINGS)
                .add(ModItems.CORAL_WINGS)
                .add(ModItems.BEE_WINGS)
                .add(ModItems.MOTH_WINGS)
                .add(ModItems.DEMON_WINGS)
                .add(ModItems.BLAZE_WINGS)
                .add(ModItems.GEM_WINGS)
                .add(ModItems.VOID_WINGS)
                .add(ModItems.ELYTRA_WINGS)
                .add(ModItems.PRISTINE_WINGS)
                .add(ModItems.NEBULOUS_WINGS)
                .add(ModItems.ASLERIX_WINGS)
                .add(ModItems.HAZEL_WINGS)
                .add(ModItems.MECHANICAL_WINGS)
                .add(ModItems.BRASS_WINGS);
    }
}
