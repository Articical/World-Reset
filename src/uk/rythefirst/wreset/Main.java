package uk.rythefirst.wreset;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import uk.rythefirst.wreset.commands.Idied;
import uk.rythefirst.wreset.events.Join;
import uk.rythefirst.wreset.events.NewPortal;
import uk.rythefirst.wreset.events.Portal;
import uk.rythefirst.wreset.events.Quit;
import uk.rythefirst.wreset.events.Spawn;
import uk.rythefirst.wreset.util.PortalManager;
import uk.rythefirst.wreset.util.TabComplete;
import uk.rythefirst.wreset.util.WorldManager;

public class Main extends JavaPlugin {

	public static JavaPlugin instance;

	public boolean died = false;

	public static World wrld = null;

	public static World wrldNether = null;

	public static World wrldEnd = null;

	public static int worldNumber;

	public static String WorldName;

	public static File worldFile;

	public static FileConfiguration worldData;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();

		Bukkit.getPluginManager().registerEvents(new Join(), this);
		Bukkit.getPluginManager().registerEvents(new Portal(), this);
		Bukkit.getPluginManager().registerEvents(new NewPortal(), this);
		Bukkit.getPluginManager().registerEvents(new Quit(), this);
		Bukkit.getPluginManager().registerEvents(new Spawn(), this); // YES I MISSED THIS, WHICH IS WHY IT WASN'T FIXED,
																		// GO AWAY PLEASE THANKS

		this.getCommand("idied").setExecutor(new Idied());
		this.getCommand("idied").setTabCompleter(new TabComplete());

		worldNumber = this.getConfig().getInt("worldnum");
		Bukkit.getLogger().log(Level.INFO, "[WR] World Number = " + worldNumber);

		worldFile = new File(this.getDataFolder() + "/worlds.yml");

		if (!(worldFile.exists())) {
			try {
				worldFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		WorldManager wm = new WorldManager();

		if (worldNumber != 0) {
			List<World> wrldLst = wm.createWorld();
			wrld = wrldLst.get(0);
			wrldNether = wrldLst.get(1);
			// wrldEnd = wrldLst.get(2);
			wrld.setAutoSave(true);
			WorldName = "world-" + worldNumber;
			Bukkit.getServer().getWorlds().add(wrld);
			Bukkit.getLogger().log(Level.INFO, "World " + worldNumber + " Loaded!");
			setCurrentWorld(Bukkit.getWorld("world-" + worldNumber));
		} else {
			setCurrentWorld(Bukkit.getWorld("world"));
			setWorldName("world");
		}

		instance = this;
		worldData = YamlConfiguration.loadConfiguration(worldFile);
		PortalManager.loadLocation();
	}

	@Override
	public void onDisable() {

		if (worldNumber > 0) {
			getCurrentWorld().save();
		}

		PortalManager.saveLocations();

		this.getConfig().set("worldnum", worldNumber);

		this.saveConfig();

		for (Player p : Bukkit.getOnlinePlayers()) {
			this.getConfig().set(p.getUniqueId().toString(), p.getLocation());
		}

		this.saveConfig();

		try {
			worldData.save(worldFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void setCurrentWorld(World world) {
		wrld = world;
	}

	public static World getCurrentWorld() {
		return wrld;
	}

	/*
	 * public static World getCurrentEnd() { return wrldEnd; }
	 */

	public static void setWorldNum(int num) {
		worldNumber = num;
	}

	public static String getWorldName() {
		return WorldName;
	}

	public static void setWorldName(String name) {
		WorldName = name;
	}

}
