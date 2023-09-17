package uk.rythefirst.wreset.events;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import uk.rythefirst.wreset.Main;

public class Quit implements Listener {

	public Plugin pl = Main.instance;

	@EventHandler
	public void PlayerLeave(PlayerQuitEvent e) throws IOException {
		Main.worldData.set(e.getPlayer().getUniqueId().toString(), e.getPlayer().getLocation());
		Main.worldData.save(Main.worldFile);
	}

}
