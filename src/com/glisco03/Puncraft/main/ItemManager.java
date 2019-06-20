package com.glisco03.Puncraft.main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

	public static ItemMeta Meta;
	public static ItemStack Stack; 

	public static ItemStack getUnbreakableItem(Material mat) {
		Stack = new ItemStack(mat);
		Meta = Stack.getItemMeta();
		Meta.setUnbreakable(true);
		Stack.setItemMeta(Meta);
		return Stack;
	}
}
