package uk.rythefirst.wreset.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import uk.rythefirst.wreset.Main;

public class Manager {

	WorldManager wm = new WorldManager();

	Integer i = Main.worldNumber;

	public void dead() {
		Main.setWorldNum(Main.worldNumber + 1);
		ArrayList<World> wl = wm.createWorld();
		World wrld = wl.get(0);
		wrld.setAutoSave(true);
		wrld.setHardcore(true);
		Bukkit.getWorlds().add(wrld);
		Bukkit.broadcastMessage("World " + wrld.getName() + " created!");
		Bukkit.broadcastMessage("WorldNumber " + Main.worldNumber);
		Main.setCurrentWorld(Bukkit.getWorld(wrld.getName()));
		Main.setWorldName("world-" + Main.worldNumber);
		Bukkit.getScheduler().runTaskTimer(Main.instance, new Runnable() {
			Integer counter = 0;

			@Override
			public void run() {

				counter = counter + 1;

				if (counter < 5) {
					Integer left = 5 - counter;
					Bukkit.broadcastMessage("Resetting in " + left + " seconds...");
				} else if (counter == 5) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.teleport(Bukkit.getServer().getWorld(Main.getWorldName()).getSpawnLocation());
						p.setGameMode(GameMode.SURVIVAL);
						p.getInventory().clear();
						p.getEnderChest().clear();
						p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
						p.setFoodLevel(15);
					}
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Mission Failed! We'll Get 'Em Next Time.");
					return;
				} else {
					return;
				}
			}

		}, 20, 20);
	}

}
