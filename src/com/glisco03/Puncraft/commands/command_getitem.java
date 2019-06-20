package com.glisco03.Puncraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.glisco03.Puncraft.main.vars;


public class command_getitem implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = null;
		
		if(sender instanceof Player) {
			p = (Player) sender;
		}
		
		if(command.getName().equalsIgnoreCase("getitem")) {
			if(p != null && p.hasPermission("Puncraft.debug")) {
				p.getInventory().addItem(vars.BlockBreaker);
			} else {
				sender.sendMessage("Players only!");
				return true;
			}
		}
		return false;
	}

}
