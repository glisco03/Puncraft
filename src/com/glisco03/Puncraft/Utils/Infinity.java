package com.glisco03.Puncraft.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Infinity {
	
	public static Inventory infinity;
	public static Inventory define;
	
	public static ItemStack infi;

	public Infinity() {
		infinity = Bukkit.createInventory(null, 54, "§c§lInfinity and beyond!");
		define = Bukkit.createInventory(null, 9);
	}
	
	private static void initInfinity() {
		infinity.clear();
		infi = define.getItem(0);
		infi.setAmount(64);
		for(int i = 0; i < 54 ; i++) {
			infinity.setItem(i, define.getItem(0));
		}	
	}
	
	public static void openInfinity(Player p) {
		initInfinity();
		p.openInventory(infinity);
	}
	
	public static void openDefiner(Player p) {
		p.openInventory(define);
	}
	
	public static boolean isDefined() {
		if(define.getItem(0) != null) {
			return true;
		} else {
			return false;
		}
	}
}
