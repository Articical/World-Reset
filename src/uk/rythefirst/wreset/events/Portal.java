package uk.rythefirst.wreset.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import uk.rythefirst.wreset.Main;
import uk.rythefirst.wreset.util.PortalManager;

public class Portal implements Listener {

	@EventHandler
	public void onPortal(PlayerPortalEvent e) {

		/*
		 * if ((e.getCause() == TeleportCause.END_PORTAL) && !(Main.worldNumber == 0)) {
		 * e.getPlayer().teleport(Main.wrldEnd.getSpawnLocation());
		 * e.setCancelled(true); return; }
		 */

		if (!(e.getCause() == TeleportCause.NETHER_PORTAL)) {
			return;
		}

		Location l = e.getTo();
		l.setWorld(Bukkit.getWorld(Main.getWorldName() + "_nether"));

		e.setTo(l);
		e.setCanCreatePortal(true);

		if (!(e.getPlayer().getWorld() == Bukkit.getWorld(Main.getWorldName()))) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
				@Override
				public void run() {
					e.getPlayer().teleport(PortalManager.getLocation(Main.getWorldName()));
				}
			}, 1l);
			e.setCancelled(true);
		}
	}

}
