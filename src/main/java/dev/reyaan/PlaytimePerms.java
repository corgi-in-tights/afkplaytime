package dev.reyaan;


import net.fabricmc.api.ModInitializer;

import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class PlaytimePerms implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("playtimeperms");
	public static String MOD_ID = "playtimeperms";

	public static final ScoreboardCriterion PLAYTIME = ScoreboardCriterion.create("playtimeperms:playtime");

	@Override
	public void onInitialize() {
		LOGGER.info("Playtime Perms is a-go!");
	}
}