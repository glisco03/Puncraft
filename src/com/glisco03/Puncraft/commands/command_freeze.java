package com.glisco03.Puncraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.glisco03.Puncraft.main.vars;


public class command_freeze implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("freeze")) {
			if(args.length > 0) {
				if(sender.hasPermission("Puncraft.freeze")) {
					if(!vars.toFreeze.contains(Bukkit.getPlayer(args[0]))) {
						sender.sendMessage("§aPlayer §b" + args[0] + " §afreezed");
						vars.toFreeze.add(Bukkit.getPlayer(args[0]));
						return true;
					} else {
						sender.sendMessage("§aPlayer §b" + args[0] + " §ade-freezed");
						vars.toFreeze.remove(Bukkit.getPlayer(args[0]));
						return true;
					}
				}
			}	
		}
		return false;
	}

}
