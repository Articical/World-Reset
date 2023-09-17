package uk.rythefirst.wreset.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import uk.rythefirst.wreset.Main;

public class Spawn implements Listener {

	@EventHandler
	public void SpawnEvent(PlayerRespawnEvent e) {
		if (!(Main.worldNumber == 0)) {
			e.getPlayer().teleport(Main.wrld.getSpawnLocation());
		}
	}

}
