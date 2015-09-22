package com.nisovin.magicspells.spelleffects;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import com.nisovin.magicspells.MagicSpells;

import de.slikey.effectlib.EffectManager;

public class EffectLibEffect extends SpellEffect {

	ConfigurationSection effectLibSection;
	EffectManager manager = MagicSpells.plugin.effectManager;
	String className;
	
	@Override
	public void loadFromString(String string) {
	}

	@Override
	protected void loadFromConfig(ConfigurationSection config) {
		effectLibSection = config.getConfigurationSection("effectlib");
		className = effectLibSection.getString("class");
	}

	protected void playEffectLocation(Location location) {
		manager.start(className, effectLibSection, location);
	}

}