package uk.rythefirst.wreset.util;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import uk.rythefirst.wreset.Main;

public class PortalManager {

	public static TreeMap<String, Location> portalMap = new TreeMap<String, Location>();

	public static Location getLocation(String worldname) {
		if (portalMap.containsKey(worldname)) {
			return portalMap.get(worldname);
		} else {
			return Bukkit.getWorld(worldname).getSpawnLocation();
		}
	}

	public static void setLocation(String worldname, Location loc) {
		portalMap.put(worldname, loc);
	}

	public static void saveLocations() {
		for (Map.Entry<String, Location> entry : portalMap.entrySet()) {
			Main.worldData.set(entry.getKey(), entry.getValue());
			Bukkit.getLogger().log(Level.INFO, "Saved location for world " + entry.getKey());
		}
	}

	public static void loadLocation() {
		String worldName = Main.getWorldName();
		if (Main.worldData.isSet(worldName)) {
			Location loc = Main.worldData.getLocation(worldName);
			portalMap.put(worldName, loc);
			Bukkit.getLogger().log(Level.INFO, "Loaded nether portal return location for world " + worldName);
		} else {
			Bukkit.getLogger().log(Level.INFO, "No portal return location found for world " + worldName);
		}
	}

}
