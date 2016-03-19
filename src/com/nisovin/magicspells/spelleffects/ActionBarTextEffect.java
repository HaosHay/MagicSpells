package com.nisovin.magicspells.spelleffects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.nisovin.magicspells.MagicSpells;
import com.nisovin.magicspells.util.ConfigData;

/**
 * public class ActionBarTextEffect<p>
 * Configuration fields:<br>
 * <ul>
 * <li>message</li>
 * <li>broadcast</li>
 * </ul>
 */
public class ActionBarTextEffect extends SpellEffect {

	@ConfigData(field="message", dataType="String", defaultValue="")
	String message = "";
	
	@ConfigData(field="broadcast", dataType="boolean", defaultValue="false")
	boolean broadcast = false;
	
	@Override
	public void loadFromString(String string) {
		message = ChatColor.translateAlternateColorCodes('&', string);
	}

	@Override
	protected void loadFromConfig(ConfigurationSection config) {
		message = ChatColor.translateAlternateColorCodes('&', config.getString("message", message));
		broadcast = config.getBoolean("broadcast", broadcast);
	}
	
	@Override
	protected Runnable playEffectEntity(Entity entity) {
		if (broadcast) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				MagicSpells.getVolatileCodeHandler().sendActionBarMessage(player, message);
			}
		} else if (entity != null && entity instanceof Player) {
			MagicSpells.getVolatileCodeHandler().sendActionBarMessage((Player)entity, message);
		}
		return null;
	}
	
}