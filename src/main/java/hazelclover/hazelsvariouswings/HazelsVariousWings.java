package hazelclover.hazelsvariouswings;

import hazelclover.hazelsvariouswings.config.GeneralConfig;
import hazelclover.hazelsvariouswings.config.WingsConfig;
import hazelclover.hazelsvariouswings.item.ModItemGroup;
import hazelclover.hazelsvariouswings.item.ModItems;
import hazelclover.hazelsvariouswings.loot.LootInjector;
import hazelclover.hazelsvariouswings.loot.TraderTrades;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HazelsVariousWings implements ModInitializer {
	public static final String MOD_ID = "hazels-various-wings";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		GeneralConfig.init();
		WingsConfig.init();
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		TraderTrades.register();
		LootInjector.register();
	}
}