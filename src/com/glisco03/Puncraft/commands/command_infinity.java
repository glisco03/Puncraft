package com.glisco03.Puncraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.glisco03.Puncraft.Utils.Infinity;


public class command_infinity implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = null;
		
		if(sender instanceof Player) {
			p = (Player) sender;
		}
		
		if(command.getName().equalsIgnoreCase("infinity")) {
			if(p != null) {
				if(Infinity.isDefined()) {
					Infinity.openInfinity(p);
				}
				return true;
			} else {
				sender.sendMessage("§cPlayers only!");
				return true;
			}
		}
		return false;
	}

}
