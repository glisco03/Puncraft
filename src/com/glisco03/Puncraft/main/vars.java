package com.glisco03.Puncraft.main;

import java.io.File;
import java.util.*;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.w3c.dom.Attr;

public class vars {
	
	public static File f = new File("plugins/Puncraft", "breakers.yml");
	public static FileConfiguration configuration = YamlConfiguration.loadConfiguration(f);
	
	public static List<Integer> x = configuration.getIntegerList("xco");
	public static List<Integer> y = configuration.getIntegerList("yco");
	public static List<Integer> z = configuration.getIntegerList("zco");
	
	public static ItemStack EmeraldSword;
	public static ItemStack EmeraldAxe;
	public static ItemStack EmeraldShovel;
	public static ItemStack EmeraldPick;
	public static ItemStack EmeraldBow;
	public static ItemStack EmeraldBoots;
	public static ItemStack EmeraldPants;
	public static ItemStack EmeraldChestplate;
	public static ItemStack EmeraldHelmet;
	public static ItemStack EmeraldHoe;
	
	public static ItemStack StoneCarver;
	public static ItemStack MoltenPickaxe;
	
	public static ItemStack BlockBreaker = new ItemStack(Material.DISPENSER);
	
	public static ItemStack StickWand = new ItemStack(Material.CARROT_ON_A_STICK);
	public static ItemStack flyboots = new ItemStack(Material.GOLDEN_BOOTS);
	public static ItemStack featherboots = new ItemStack(Material.IRON_BOOTS);
	public static ItemStack heavengold = new ItemStack(Material.GOLD_BLOCK);	
	public static ItemStack sleepbag = new ItemStack(Material.CARROT_ON_A_STICK);
	public static ItemStack InstElev = new ItemStack(Material.CARROT_ON_A_STICK);
	public static ItemStack Teleporter = new ItemStack(Material.CARROT_ON_A_STICK);
	public static ItemStack Magnet = new ItemStack(Material.CARROT_ON_A_STICK);
	public static ItemStack PHole = new ItemStack(Material.CARROT_ON_A_STICK);
	public static ItemStack MShell = new ItemStack(Material.TURTLE_HELMET);
	public static ItemStack MinerHelmet = new ItemStack(Material.IRON_HELMET);
	public static ItemStack regenchest = new ItemStack(Material.DIAMOND_CHESTPLATE);
	
	public static ItemStack CraftStick = new ItemStack(Material.CARROT_ON_A_STICK);
	
	public static ArrayList<Player> toFreeze = new ArrayList<Player>();
	
	public static ArrayList<Material> meltores = new ArrayList<Material>();

	public static HashMap<Integer, Material> levitationPlatform = new HashMap<Integer, Material>();
	public static HashMap<Integer, Material> jumpPlatform = new HashMap<Integer, Material>();
	
	public static String prefix = "ß1[ßdUmfrageß1]ßr ";
	public static String punprefix = "ße[ß2Puntestße]ßr ";
	
	public static Location quiz(World w) {
		return new Location(w, 98, 66, 4064);
	}
	
	@SuppressWarnings("deprecation")
	public vars() {
		EmeraldSword = ItemManager.getUnbreakableItem(Material.DIAMOND_SWORD);
		EmeraldAxe = ItemManager.getUnbreakableItem(Material.DIAMOND_AXE);
		EmeraldBoots = ItemManager.getUnbreakableItem(Material.DIAMOND_BOOTS);
		EmeraldBow = ItemManager.getUnbreakableItem(Material.BOW);
		EmeraldChestplate = ItemManager.getUnbreakableItem(Material.DIAMOND_CHESTPLATE);
		EmeraldHelmet = ItemManager.getUnbreakableItem(Material.DIAMOND_HELMET);
		EmeraldPants = ItemManager.getUnbreakableItem(Material.DIAMOND_LEGGINGS);
		EmeraldPick = ItemManager.getUnbreakableItem(Material.DIAMOND_PICKAXE);
		EmeraldShovel = ItemManager.getUnbreakableItem(Material.DIAMOND_SHOVEL);
		EmeraldHoe = ItemManager.getUnbreakableItem(Material.DIAMOND_HOE);

		ItemMeta StMeta = StickWand.getItemMeta();
		StMeta.setDisplayName("ß4ßlFEUERBALL");
		StMeta.setUnbreakable(true);
		StMeta.setLore(Arrays.asList("ßbSchieﬂt" , "ßbFeuerb‰lle"));
		StickWand.setItemMeta(StMeta);
		StickWand.setDurability((short) 7);
		
		ItemMeta BootsMeta = flyboots.getItemMeta();
		BootsMeta.setDisplayName("ß6ßlFLYBOOTS");
		BootsMeta.setUnbreakable(true);
		flyboots.setItemMeta(BootsMeta);

		ItemMeta plateMeta = regenchest.getItemMeta();
		plateMeta.setDisplayName("ßcßlRegeneration Chestplate");
		plateMeta.setUnbreakable(true);
		regenchest.setItemMeta(plateMeta);
		regenchest.setDurability((short) 1);

		ItemMeta featherMeta = featherboots.getItemMeta();
		AttributeModifier featherMod = new AttributeModifier(UUID.randomUUID(),"GENERIC_ARMOR", -3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
		featherMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, featherMod);
		featherMeta.setDisplayName("ßbßlLong Fall Boots");
		featherMeta.setUnbreakable(true);
		featherboots.setItemMeta(featherMeta);
		
		ItemMeta InstMeta = InstElev.getItemMeta();
		InstMeta.setDisplayName("ß6Instant Elevator");
		InstMeta.setUnbreakable(true);
		InstElev.setItemMeta(InstMeta);
		InstElev.setDurability((short) 5);
		
		ItemMeta ShellMeta = MShell.getItemMeta();
		ShellMeta.setDisplayName("ß2Master ßbShell");
		ShellMeta.setUnbreakable(true);
		MShell.setItemMeta(ShellMeta);
		MShell.setDurability((short) 2);
		
		ItemMeta TeleMeta = Teleporter.getItemMeta();
		TeleMeta.setDisplayName("ßbTeleporter");
		TeleMeta.setUnbreakable(true);
		Teleporter.setItemMeta(TeleMeta);
		Teleporter.setDurability((short) 1);
		
		ItemMeta MagMeta = Magnet.getItemMeta();
		MagMeta.setDisplayName("ß5Item Magnet");
		MagMeta.setUnbreakable(true);
		Magnet.setItemMeta(MagMeta);
		Magnet.setDurability((short) 3);
		
		ItemMeta HoleMeta = PHole.getItemMeta();
		HoleMeta.setDisplayName("ß1Portable Hole");
		HoleMeta.setUnbreakable(true);
		PHole.setItemMeta(HoleMeta);
		PHole.setDurability((short) 4);
		
		ItemMeta heavenmeta = heavengold.getItemMeta();
		heavenmeta.setDisplayName("ßbHimmlisches ß6GOLD");
		heavengold.setItemMeta(heavenmeta);
		
		ItemMeta craftmeta = CraftStick.getItemMeta();
		craftmeta.setDisplayName("ßbPortable Crafting Table");
		craftmeta.setUnbreakable(true);
		CraftStick.setItemMeta(craftmeta);
		CraftStick.setDurability((short) 6);
		
		ItemMeta sleepmeta = sleepbag.getItemMeta();
		sleepmeta.setDisplayName("ßaSchlafsack");
		sleepmeta.setUnbreakable(true);
		sleepbag.setItemMeta(sleepmeta);
		sleepbag.setDurability((short) 2);

		ItemMeta minermeta = MinerHelmet.getItemMeta();
		minermeta.setDisplayName("ß8Miners Helmet");
		minermeta.setUnbreakable(true);
		AttributeModifier helmMod = new AttributeModifier(UUID.randomUUID(),"GENERIC_ARMOR", -6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
		minermeta.addAttributeModifier(Attribute.GENERIC_ARMOR, helmMod);
		MinerHelmet.setItemMeta(minermeta);
		MinerHelmet.setDurability((short) 1);
		
		MoltenPickaxe = ItemManager.getUnbreakableItem(Material.DIAMOND_PICKAXE);
		ItemMeta moltenmeta = MoltenPickaxe.getItemMeta();
		moltenmeta.setDisplayName("ß6ßlMolten Pickaxe");
		moltenmeta.setUnbreakable(true);
		MoltenPickaxe.setItemMeta(moltenmeta);
		MoltenPickaxe.setDurability((short) 1);
		
		StoneCarver = ItemManager.getUnbreakableItem(Material.IRON_SHOVEL);
		ItemMeta CarveMeta = StoneCarver.getItemMeta();
		CarveMeta.setDisplayName("ß7Stonecarver");
		CarveMeta.setUnbreakable(true);
		StoneCarver.setItemMeta(CarveMeta);
		StoneCarver.setDurability((short) 1);
		
		ItemMeta BBMeta = BlockBreaker.getItemMeta();
		BBMeta.setDisplayName("ß2Block Breaker");
		BlockBreaker.setItemMeta(BBMeta);
		
		meltores.add(Material.IRON_ORE);
		meltores.add(Material.GOLD_ORE);
		
		levitationPlatform.put(2, Material.IRON_BLOCK);
		levitationPlatform.put(4, Material.IRON_BLOCK);
		levitationPlatform.put(6, Material.IRON_BLOCK);
		levitationPlatform.put(8, Material.IRON_BLOCK);
		levitationPlatform.put(5, Material.LAPIS_BLOCK);
		
		jumpPlatform.put(2, Material.GRAY_GLAZED_TERRACOTTA);
		jumpPlatform.put(4, Material.GRAY_GLAZED_TERRACOTTA);
		jumpPlatform.put(6, Material.GRAY_GLAZED_TERRACOTTA);
		jumpPlatform.put(8, Material.GRAY_GLAZED_TERRACOTTA);
		jumpPlatform.put(5, Material.GOLD_BLOCK);
	}
}
