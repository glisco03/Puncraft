package com.glisco03.Puncraft.Timers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.glisco03.Puncraft.Utils.CombinerManager;
import com.glisco03.Puncraft.Utils.WorldManager;
import com.glisco03.Puncraft.main.main;
import com.glisco03.Puncraft.main.vars;

public class Timer1L implements Runnable{
	
	World world;
	Location loc;
	Block block;
	int strenght;
	
	InventoryView inv;
	
	Location loc1 ;
	
	ItemStack item1;
	ItemStack item2;
	
	int xx;
	int yy;
	int zz;

	List<Player> toremove = new ArrayList<Player>();
	
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {	
			boostPlatforms(p);
			flyBoots(p);
			minerHelmet(p);
			featherBoots(p);
			regenChest(p);
			p.removeMetadata("delay1", main.pcplugin);
		}
		
		freezedInventory();
		combinerClock();
	}	
	
	public void boostPlatforms(Player p) {
		loc = p.getLocation().subtract(0, 1, 0);
		
		if(WorldManager.getCross(loc).equals(vars.levitationPlatform)) {
			strenght = 6;
			loc = p.getLocation().subtract(0, 2, 0);
			if(loc.getBlock().getType().equals(Material.DIAMOND_BLOCK)) {
				strenght = 12;
				loc = loc.subtract(0, 1, 0);
				if(loc.getBlock().getType().equals(Material.EMERALD_BLOCK)) {
					strenght = 21;
				}
			}
			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, strenght));
		}
		
		loc = p.getLocation().subtract(0, 1, 0);
		
		if(WorldManager.getCross(loc).equals(vars.jumpPlatform)) {
			strenght = 4;
			loc = p.getLocation().subtract(0, 2, 0);
			if(loc.getBlock().getType().equals(Material.PISTON)) {
				strenght = 9;
				loc = loc.subtract(0, 1, 0);
				if(loc.getBlock().getType().equals(Material.PISTON)) {
					strenght = 15;
				}
			}
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, strenght));
		}
	}

	public void flyBoots(Player p){
		if(p.getInventory().getBoots() != null) {
			if(p.getInventory().getBoots().getItemMeta().hasDisplayName()) {
				if(p.getInventory().getBoots().getItemMeta().getDisplayName().equals("§6§lFLYBOOTS") && p.getAllowFlight() == false) {
					p.setAllowFlight(true);
				}
			} else if(!p.getGameMode().equals(GameMode.CREATIVE) && !p.getGameMode().equals(GameMode.SPECTATOR)){
				p.setAllowFlight(false);
			}
		} else if(p.getInventory().getBoots() == null) {
			if(!p.getGameMode().equals(GameMode.CREATIVE) && !p.getGameMode().equals(GameMode.SPECTATOR)){
				p.setAllowFlight(false);
			}
		}
	}

	public void minerHelmet(Player p){
		if(p.getInventory().getHelmet() != null) {
			if(p.getInventory().getHelmet().getItemMeta().hasDisplayName()) {
				if(p.getInventory().getHelmet().getItemMeta().getDisplayName().equals("§8Miners Helmet")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,19999980,0),false);
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,19999980,0), false);
				}
			}
		}
	}

	public void featherBoots(Player p){
		if(p.getInventory().getBoots() != null) {
			if(p.getInventory().getBoots().getItemMeta().hasDisplayName()) {
				if(p.getInventory().getBoots().getItemMeta().getDisplayName().equals("§b§lLong Fall Boots") && !p.isGliding()) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,30,0),true);
				}
			}
		}
	}

	public void regenChest(Player p){
		if(p.getInventory().getChestplate() != null) {
			if(p.getInventory().getChestplate().getItemMeta().hasDisplayName()) {
				if(p.getInventory().getChestplate().getItemMeta().getDisplayName().equals("§c§lRegeneration Chestplate")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,60,1),true);
				}
			}
		}
	}

	public void freezedInventory() {
		if(vars.toFreeze.size()>0) {
			for(Player p : vars.toFreeze) {
				p.closeInventory();
			}	
		}
	}

	public void combinerClock() {
		for(Player p : CombinerManager.isCombining()) {
			inv = p.getOpenInventory();
			if(!inv.getItem(1).getType().equals(Material.AIR) && !inv.getItem(3).getType().equals(Material.AIR)) {
				if(inv.getItem(1).getType().equals(inv.getItem(3).getType())) {
					item1 = inv.getItem(1);
					item2 = inv.getItem(3);
					item1.addEnchantments(item2.getEnchantments());
					inv.setItem(7, item1);
					inv.setItem(1, null);
					inv.setItem(3, null);
				} else if(inv.getItem(3).getType().equals(Material.ENCHANTED_BOOK)){
					item1 = inv.getItem(1);
					item2 = inv.getItem(3);
					EnchantmentStorageMeta BookEnchants = (EnchantmentStorageMeta)item2.getItemMeta();
					item1.addEnchantments(BookEnchants.getStoredEnchants());
					//p.sendMessage(item2.getItemMeta().getEnchants() + "");
					inv.setItem(7, item1);
					inv.setItem(1, null);
					inv.setItem(3, null);
				} else if(inv.getItem(1).getType().equals(Material.BOOK)){
					item1 = inv.getItem(1);
					item2 = inv.getItem(3);
					item1.setType(Material.ENCHANTED_BOOK);
					EnchantmentStorageMeta BookEnchants = (EnchantmentStorageMeta)item1.getItemMeta();
					for(Enchantment ench : item2.getEnchantments().keySet()) {
						BookEnchants.addStoredEnchant(ench, item2.getEnchantmentLevel(ench), false);
					}
					item1.setItemMeta((ItemMeta) BookEnchants);
					inv.setItem(7, item1);
					inv.setItem(1, null);
					inv.setItem(3, null);
				}
			}
			toremove.add(p);
		}
		for(int i = 0; i < toremove.size(); i++) {
			CombinerManager.stopCombining(toremove.get(0));	
			}
		toremove.clear();
	}

}

