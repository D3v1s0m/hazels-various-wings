package hazelclover.hazelsvariouswings;

import hazelclover.hazelsvariouswings.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HazelsVariousWings implements ModInitializer {
	public static final String MOD_ID = "hazels-various-wings";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}