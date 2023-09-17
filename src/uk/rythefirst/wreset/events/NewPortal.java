package uk.rythefirst.wreset.events;

import org.bukkit.World.Environment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

import uk.rythefirst.wreset.util.PortalManager;

public class NewPortal implements Listener {

	@EventHandler
	public void PortalCreate(PortalCreateEvent e) {
		if (e.getEntity() != null && e.getEntity().getType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			if (!(p.getWorld().getEnvironment() == Environment.NETHER)) {
				PortalManager.setLocation(p.getWorld().getName(), p.getLocation());
			}
		}
	}

}
