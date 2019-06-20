package com.glisco03.Puncraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.glisco03.Puncraft.Utils.CombinerManager;


public class command_combiner implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = null;
		
		if(sender instanceof Player) {
			p = (Player) sender;
		}
		
		if(command.getName().equalsIgnoreCase("combiner")) {
			if(p != null) {
				if(CombinerManager.getPlayers().isEmpty()) {
					CombinerManager.openCombiner(p);
					return true;
				} else {
					p.sendMessage("§cDer Combiner ist Gerade in Benutzung!");
					return true;
				}
			} else {
				sender.sendMessage("Players only!");
				return true;
			}
		}
		return false;
	}

}
