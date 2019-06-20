package com.glisco03.Puncraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.glisco03.Puncraft.main.vars;

public class command_stickwand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = null;
		PlayerInventory inv;
		
		if(sender instanceof Player) {
			p = (Player) sender;
		}
		
		if(command.getName().equalsIgnoreCase("stickwand")) {
			if(p != null) {
				if(p.hasPermission("Puncraft.stickwand")) {
					inv = p.getInventory();
					inv.addItem(vars.StickWand);
					p.sendMessage("§aStickWand received!");
					return true;
				} else {
					p.sendMessage("§4Du Idiot, das darfst du nicht!");
					return true;
				}
			} else {
				sender.sendMessage("§cPlayers only!");
			}
		}
		
		return false;
	}

}
