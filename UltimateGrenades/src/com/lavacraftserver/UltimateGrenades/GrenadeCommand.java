package com.lavacraftserver.UltimateGrenades;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class GrenadeCommand implements Listener, CommandExecutor {
	
	public static HashMap<Player, Boolean> egg = new HashMap<Player, Boolean>();
	public static HashMap<Player, Boolean> snowball = new HashMap<Player, Boolean>();
	//public static HashMap<Player, Boolean> arrow = new HashMap<Player, Boolean>();
	//public static HashMap<Player, Boolean> xpBottle = new HashMap<Player, Boolean>();
	public static HashMap<Player, Boolean> enderPearl = new HashMap<Player, Boolean>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {		
		if (commandLabel.equalsIgnoreCase("grenade")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[UltimateGrenades] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("ultimategrenades.use") || s.isOp()) {									
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (args[0].equalsIgnoreCase("egg")) {
							if (egg.containsKey(s)) {
								egg.remove(s);
								sender.sendMessage(ChatColor.GREEN + "Egg grenade disabled!");
								return true;
							}
							if (!(egg.containsKey(s))) {
								egg.put(s, true);
								sender.sendMessage(ChatColor.GREEN + "Egg grenade enabled!");
								return true;
							}
						}
						
						if (args[0].equalsIgnoreCase("snowball")) {
							if (snowball.containsKey(s)) {
								snowball.remove(s);
								sender.sendMessage(ChatColor.GREEN + "Snowball grenade disabled!");
								return true;
							}
							if (!(snowball.containsKey(s))) {
								snowball.put(s, true);
								sender.sendMessage(ChatColor.GREEN + "Snowball grenade enabled!");
								return true;
							}
						}
						
						/*if (args[0].equalsIgnoreCase("arrow")) {
							if (arrow.containsKey(s)) {
								arrow.remove(s);
								sender.sendMessage(ChatColor.GREEN + "Arrow grenade disabled!");
								return true;
							}
							if (!(arrow.containsKey(s))) {
								arrow.put(s, true);
								sender.sendMessage(ChatColor.GREEN + "Arrow grenade enabled!");
								return true;
							}
						}
						
						if (args[0].equalsIgnoreCase("xpbottle")) {
							if (xpBottle.containsKey(s)) {
								xpBottle.remove(s);
								sender.sendMessage(ChatColor.GREEN + "XPBottle grenade disabled!");
								return true;
							}
							if (!(xpBottle.containsKey(s))) {
								xpBottle.put(s, true);
								sender.sendMessage(ChatColor.GREEN + "XPBottle grenade enabled!");
								return true;
							}
						}*/
						
						if (args[0].equalsIgnoreCase("enderpearl")) {
							if (enderPearl.containsKey(s)) {
								enderPearl.remove(s);
								sender.sendMessage(ChatColor.GREEN + "Enderpearl grenade disabled!");
								return true;
							}
							if (!(enderPearl.containsKey(s))) {
								enderPearl.put(s, true);
								sender.sendMessage(ChatColor.GREEN + "Enderpearl grenade enabled!");
								return true;
							}
						}						
						if (!(args[0].equalsIgnoreCase("egg") || args[0].equalsIgnoreCase("snowball") || args[0].equalsIgnoreCase("arrow") || args[0].equalsIgnoreCase("xpbottle") || args[0].equalsIgnoreCase("enderpearl"))) {
							sender.sendMessage(ChatColor.RED + "Invalid grenade type.");
							return true;
						}

					}
				
				} else {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
				}
			
			}
		
		}
		return true;
	}
}