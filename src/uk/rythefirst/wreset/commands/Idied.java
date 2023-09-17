package uk.rythefirst.wreset.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.wreset.util.Manager;

public class Idied implements CommandExecutor {

	public String conf = "confirm";

	public List<Player> confirmLst = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("Please use \"/idied confirm\" to confirm that you wish to reset the world.");
				confirmLst.add(p);
			} else if (args.length == 1 && args[0].equalsIgnoreCase("confirm") && confirmLst.contains(p)) {
				Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "World is being reset, please wait!");
				Manager mgr = new Manager();
				mgr.dead();
			}
			return true;
		}
		return false;
	}

}
