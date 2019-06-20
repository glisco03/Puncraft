package com.glisco03.Puncraft.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CombinerManager {
	
	public static ItemStack wool = new ItemStack(Material.GREEN_WOOL);
	public static ItemStack barrier = new ItemStack(Material.BARRIER);
	
	public CombinerManager(){
		initCombiner();
	}

	static Inventory combiner = Bukkit.createInventory(null, 9, "§bCombiner/Amboss");
	static InventoryView openedCombiner;
	
	static List<Player> isOpened = new ArrayList<Player>();
	static List<Player> isCombining = new ArrayList<Player>();
	
	public static List<Player> getPlayers(){
		return isOpened;
	}
	
	public static void openCombiner(Player p) {
		p.openInventory(combiner);
		openedCombiner = p.getOpenInventory();
		isOpened.add(p);
	}
	
	public static void combinerClosed(Player p) {
		isOpened.remove(p);
		initCombiner();
	}
	
	public static void startCombining(Player p) {
		isCombining.add(p);
	}
	
	public static void stopCombining(Player p) {
		isCombining.remove(p);
	}
	
	public static List<Player> isCombining(){
		return isCombining;
	}
	
	public static void initCombiner() {
		ItemMeta barrm = barrier.getItemMeta();
		barrm.setDisplayName(" ");
		barrier.setItemMeta(barrm);
		combiner.setItem(0, barrier);
		combiner.setItem(2, barrier);
		combiner.setItem(4, barrier);
		combiner.setItem(6, barrier);
		combiner.setItem(8, barrier);
		combiner.setItem(1, null);
		combiner.setItem(3, null);
		combiner.setItem(7, null);
		ItemMeta woolm = wool.getItemMeta();
		woolm.setDisplayName("§2OK");
		wool.setItemMeta(woolm);
		combiner.setItem(5, wool);
	}
}
