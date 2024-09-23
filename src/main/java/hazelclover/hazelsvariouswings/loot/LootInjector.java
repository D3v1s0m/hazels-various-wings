package hazelclover.hazelsvariouswings.loot;

import hazelclover.hazelsvariouswings.HazelsVariousWings;
import hazelclover.hazelsvariouswings.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootInjector {
    public static void register() {
        HazelsVariousWings.LOGGER.info("Registering Loot Injector for " + HazelsVariousWings.MOD_ID);

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (EntityType.PHANTOM.getLootTableId().equals(key) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.PHANTOM_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.091f).build());
                tableBuilder.pool(pool);
	        }



            if (EntityType.ELDER_GUARDIAN.getLootTableId().equals(key) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.CORAL_WINGS));
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/underwater_ruin_big")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.CORAL_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.12f).build());
                tableBuilder.pool(pool);
            }


            if (key.getValue().equals(Identifier.of("minecraft", "chests/abandoned_mineshaft")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.FLEDGLING_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.04f).build());
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/trial_chambers/reward_common")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.FLEDGLING_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.03f).build());
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/buried_treasure")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.FLEDGLING_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.22f).build());
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/desert_pyramid")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.FLEDGLING_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.2f).build());
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/pillager_outpost")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.FLEDGLING_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f).build());
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/simple_dungeon")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.FLEDGLING_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.08f).build());
                tableBuilder.pool(pool);
            }



            if (key.getValue().equals(Identifier.of("minecraft", "chests/woodland_mansion")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.MOTH_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.09f).build());
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/jungle_temple")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.MOTH_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.2f).build());
                tableBuilder.pool(pool);
            }


            if (key.getValue().equals(Identifier.of("minecraft", "chests/bastion_treasure")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.DEMON_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.24f).build());
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/bastion_bridge")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.DEMON_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.14f).build());
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/bastion_other")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.DEMON_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.08f).build());
                tableBuilder.pool(pool);
            }
            if (key.getValue().equals(Identifier.of("minecraft", "chests/nether_bridge")) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(ModItems.DEMON_WINGS)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.044f).build());
                tableBuilder.pool(pool);
            }
        });
    }
}
