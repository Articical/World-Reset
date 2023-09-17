package uk.rythefirst.wreset.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import uk.rythefirst.wreset.Main;

public class Join implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		Plugin pl = Main.instance;

		if (pl.getConfig().isSet(e.getPlayer().getUniqueId().toString())
				&& !(e.getPlayer().getWorld() == Bukkit.getWorld("world"))
				&& !(e.getPlayer().getWorld() == Bukkit.getWorld("world_nether"))) {
			Location lastLoc = Main.worldData.getLocation(e.getPlayer().getUniqueId().toString());

			if ((lastLoc.getWorld() == Main.wrld) || (lastLoc.getWorld() == Main.wrldNether)) {

				Bukkit.getScheduler().runTaskLater(pl, new Runnable() {

					@Override
					public void run() {
						e.getPlayer().teleport(lastLoc);
					}

				}, 1l);
			} else {
				Location worldSpawn = Bukkit.getWorld(Main.getWorldName()).getSpawnLocation();

				Bukkit.getScheduler().runTaskLater(pl, new Runnable() {

					@Override
					public void run() {
						e.getPlayer().getInventory().clear();
						e.getPlayer().teleport(worldSpawn);
						e.getPlayer()
								.setHealth(e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
						e.getPlayer().setFoodLevel(25);
					}

				}, 1l);
			}
		} else if (!(e.getPlayer().getWorld() == Bukkit.getWorld("world"))
				|| !(e.getPlayer().getWorld() == Bukkit.getWorld("world_nether"))) {

			Location worldSpawn = Bukkit.getWorld(Main.getWorldName()).getSpawnLocation();

			Bukkit.getScheduler().runTaskLater(pl, new Runnable() {

				@Override
				public void run() {
					e.getPlayer().getInventory().clear();
					e.getPlayer().teleport(worldSpawn);
					e.getPlayer().setHealth(e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
					e.getPlayer().setFoodLevel(20);
				}

			}, 1l);

		}

	}
}
