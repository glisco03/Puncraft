package com.glisco03.Puncraft.main;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import com.glisco03.Puncraft.Timers.Timer1L;
import com.glisco03.Puncraft.Timers.Timer2400L;
import com.glisco03.Puncraft.Utils.CombinerManager;
import com.glisco03.Puncraft.Utils.EventListener;
import com.glisco03.Puncraft.Utils.Infinity;
import com.glisco03.Puncraft.commands.command_combiner;
import com.glisco03.Puncraft.commands.command_freeze;
import com.glisco03.Puncraft.commands.command_getitem;
import com.glisco03.Puncraft.commands.command_puntest;
import com.glisco03.Puncraft.commands.command_stickwand;
import com.glisco03.Puncraft.commands.command_umfrage;

public class main extends JavaPlugin{
	
	public static JavaPlugin pcplugin;

	@Override
	public void onEnable() {
		new vars();
		pcplugin = this;
		Bukkit.getScheduler().runTaskTimer(this, new Timer1L(), 1L, 1L);
		Bukkit.getScheduler().runTaskTimer(this, new Timer2400L(), 2400L, 2400L);
		new EventListener(this);
		new recipes();
		new CombinerManager();
		new Infinity();
		getCommand("umfrage").setExecutor(new command_umfrage());
		getCommand("stickwand").setExecutor(new command_stickwand());
		getCommand("freeze").setExecutor(new command_freeze());
		getCommand("combiner").setExecutor(new command_combiner());
		getCommand("getitem").setExecutor(new command_getitem());
		getCommand("puntest").setExecutor(new command_puntest());
		//getCommand("infinity").setExecutor(new command_infinity());
		//getCommand("setinfi").setExecutor(new command_setinfi());
	}
	
	public static NamespacedKey key(String name) {
		return new NamespacedKey(pcplugin, name);
	}
}
