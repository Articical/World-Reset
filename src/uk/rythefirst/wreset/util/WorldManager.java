package uk.rythefirst.wreset.util;

import java.util.ArrayList;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import uk.rythefirst.wreset.Main;

public class WorldManager {

	public ArrayList<World> createWorld() {
		WorldCreator wcn = new WorldCreator("world-" + Main.worldNumber + "_nether");
		WorldCreator wc = new WorldCreator("world-" + Main.worldNumber);
		// WorldCreator wce = new WorldCreator("world-" + Main.worldNumber + "_end");

		wc.hardcore(true);
		wc.type(WorldType.NORMAL);
		wc.generateStructures(true);

		wcn.hardcore(true);
		wcn.environment(Environment.NETHER);
		wcn.generateStructures(true);

		/*
		 * wce.hardcore(true); wce.environment(Environment.THE_END);
		 * wce.generateStructures(true);
		 */

		ArrayList<World> wl = new ArrayList<World>();

		wl.add(wc.createWorld());
		wl.add(wcn.createWorld());
		// wl.add(wce.createWorld());

		return wl;
	}

}
