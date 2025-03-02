package hazelclover.hazelsvariouswings.loot;

import hazelclover.hazelsvariouswings.HazelsVariousWings;
import hazelclover.hazelsvariouswings.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

import java.util.Optional;

public class TraderTrades {
    public static void register() {
        HazelsVariousWings.LOGGER.info("Registering Wandering Trader trades for " + HazelsVariousWings.MOD_ID);
        // Adding wing trades to Wandering Trader
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add((entity, random) -> new TradeOffer(
                new TradedItem(Items.EMERALD, 4),
                new ItemStack(ModItems.FLEDGLING_WINGS, 1),
                1,                                   // Max uses of the trade
                5,                                   // Experience given
                0.1F                                // Price multiplier
        )));
        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 12),
                    new ItemStack(ModItems.CORAL_WINGS, 1),
                    1,                                   // Max uses of the trade
                    10,                                   // Experience given
                    0.2F                                // Price multiplier
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 8),
                    Optional.of(new TradedItem(Items.HONEYCOMB, 2)),
                    new ItemStack(ModItems.BEE_WINGS, 1),
                    1,                                   // Max uses of the trade
                    10,                                   // Experience given
                    0.2F                                // Price multiplier
            ));
        });
    }
}
