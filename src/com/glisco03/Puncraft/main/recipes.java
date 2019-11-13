package com.glisco03.Puncraft.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class recipes {
	
	public static ShapedRecipe flyrecp;
	
	public recipes() {
		/*for(Player p:Bukkit.getOnlinePlayers()){
			p.sendMessage("Adding Recipes");
		}*/
		addTweakRecipes();
		addFlyBootRecipes();
	}
	
	@SuppressWarnings("unused")
	private void addEmeraldRecipes() {
		ShapedRecipe EmeraldSwordRecp = new ShapedRecipe(main.key("Sword"), vars.EmeraldSword).shape(" d ", " n ", " s ");
		EmeraldSwordRecp.setIngredient('d', Material.DIAMOND);
		EmeraldSwordRecp.setIngredient('n', Material.EMERALD);
		EmeraldSwordRecp.setIngredient('s', Material.STICK);
		Bukkit.addRecipe(EmeraldSwordRecp);
		
		ShapedRecipe EmeraldAxeRecp = new ShapedRecipe(main.key("Axe"), vars.EmeraldAxe).shape(" dn", " sd", " s ");
		EmeraldAxeRecp.setIngredient('d', Material.DIAMOND);
		EmeraldAxeRecp.setIngredient('n', Material.EMERALD);
		EmeraldAxeRecp.setIngredient('s', Material.STICK);
		Bukkit.addRecipe(EmeraldAxeRecp);
		
		ShapedRecipe EmeraldShovelRecp = new ShapedRecipe(main.key("Shovel"), vars.EmeraldShovel).shape(" n ", " d ", " s ");
		EmeraldShovelRecp.setIngredient('d', Material.DIAMOND);
		EmeraldShovelRecp.setIngredient('n', Material.EMERALD);
		EmeraldShovelRecp.setIngredient('s', Material.STICK);
		Bukkit.addRecipe(EmeraldShovelRecp);
		
		ShapedRecipe EmeraldPickRecp = new ShapedRecipe(main.key("Pick"), vars.EmeraldPick).shape("dnd", " s ", " s ");
		EmeraldPickRecp.setIngredient('d', Material.DIAMOND);
		EmeraldPickRecp.setIngredient('n', Material.EMERALD);
		EmeraldPickRecp.setIngredient('s', Material.STICK);
		Bukkit.addRecipe(EmeraldPickRecp);
		
		ShapedRecipe EmeraldBowRecp = new ShapedRecipe(main.key("Bow"), vars.EmeraldBow).shape(" sw", "n w", " sw");
		EmeraldBowRecp.setIngredient('w', Material.STRING);
		EmeraldBowRecp.setIngredient('n', Material.EMERALD);
		EmeraldBowRecp.setIngredient('s', Material.STICK);
		Bukkit.addRecipe(EmeraldBowRecp);
		
		ShapedRecipe EmeraldBootsRecp = new ShapedRecipe(main.key("Boots"), vars.EmeraldBoots).shape("n d", "d d", "   ");
		EmeraldBootsRecp.setIngredient('d', Material.DIAMOND);
		EmeraldBootsRecp.setIngredient('n', Material.EMERALD);
		Bukkit.addRecipe(EmeraldBootsRecp);
		
		ShapedRecipe EmeraldPantsRecp = new ShapedRecipe(main.key("Pants"), vars.EmeraldPants).shape("dnd", "d d", "d d");
		EmeraldPantsRecp.setIngredient('d', Material.DIAMOND);
		EmeraldPantsRecp.setIngredient('n', Material.EMERALD);
		Bukkit.addRecipe(EmeraldPantsRecp);
		
		ShapedRecipe EmeraldChestplateRecp = new ShapedRecipe(main.key("Chestplate"), vars.EmeraldChestplate).shape("d d", "dnd", "ddd");
		EmeraldChestplateRecp.setIngredient('d', Material.DIAMOND);
		EmeraldChestplateRecp.setIngredient('n', Material.EMERALD);
		Bukkit.addRecipe(EmeraldChestplateRecp);
		
		ShapedRecipe EmeraldHelmetRecp = new ShapedRecipe(main.key("Helmet"), vars.EmeraldHelmet).shape("dnd", "d d", "   ");
		EmeraldHelmetRecp.setIngredient('d', Material.DIAMOND);
		EmeraldHelmetRecp.setIngredient('n', Material.EMERALD);
		Bukkit.addRecipe(EmeraldHelmetRecp);
		
		ShapedRecipe EmeraldHoeRecp = new ShapedRecipe(main.key("EmeraldHoe"), vars.EmeraldHoe).shape(" nd", " s ", " s ");
		EmeraldHoeRecp.setIngredient('n', Material.EMERALD);
		EmeraldHoeRecp.setIngredient('d', Material.DIAMOND);
		EmeraldHoeRecp.setIngredient('s', Material.STICK);
		Bukkit.addRecipe(EmeraldHoeRecp);
	}

	private void addTweakRecipes() {
		/*ShapedRecipe cobweb = new ShapedRecipe(main.key("cobweb"), new ItemStack(Material.COBWEB)).shape(" m ", "wsw", " w ");
		cobweb.setIngredient('m', Material.BROWN_MUSHROOM);
		cobweb.setIngredient('w', Material.STRING);
		cobweb.setIngredient('s', Material.STICK);
		Bukkit.addRecipe(cobweb);*/
		
		/*ShapedRecipe BlockBreaker = new ShapedRecipe(main.key("BlockBreaker"), vars.BlockBreaker).shape("crc", "rpr", "crc");
		BlockBreaker.setIngredient('c', Material.COBBLESTONE);
		BlockBreaker.setIngredient('r', Material.REDSTONE);
		BlockBreaker.setIngredient('p', Material.DIAMOND_PICKAXE);
		Bukkit.addRecipe(BlockBreaker);*/
		
		ShapedRecipe prismarine = new ShapedRecipe(main.key("prismarine"), new ItemStack(Material.PRISMARINE, 8)).shape("ccc", "cfc", "ccc");
		prismarine.setIngredient('c', Material.COBBLESTONE);
		prismarine.setIngredient('f', Material.COD);
		Bukkit.addRecipe(prismarine);
		
		ShapedRecipe prisbricks = new ShapedRecipe(main.key("prisbricks"), new ItemStack(Material.PRISMARINE_BRICKS)).shape("sss", "sfs", "sss");
		prisbricks.setIngredient('s', Material.STONE_BRICKS);
		prisbricks.setIngredient('f', Material.COD);
		Bukkit.addRecipe(prisbricks);
		
		ShapedRecipe darkpris = new ShapedRecipe(main.key("darkpris"), new ItemStack(Material.DARK_PRISMARINE)).shape("sss", "sfs", "sss");
		darkpris.setIngredient('s', Material.STONE_BRICKS);
		darkpris.setIngredient('f', Material.SALMON);
		Bukkit.addRecipe(darkpris);
		
		ShapedRecipe sealantern = new ShapedRecipe(main.key("sealantern"), new ItemStack(Material.SEA_LANTERN, 8)).shape("ggg", "gfg", "ggg");
		sealantern.setIngredient('g', Material.GLOWSTONE);
		sealantern.setIngredient('f', Material.COD);
		Bukkit.addRecipe(sealantern);
		
		/*ShapedRecipe lilypad = new ShapedRecipe(main.key("leaves"), new ItemStack(Material.LILY_PAD)).shape("lll", "lll", "lll");
		lilypad.setIngredient('l', Material.OAK_LEAVES);
		Bukkit.addRecipe(lilypad);
		
		ShapedRecipe slimeball = new ShapedRecipe(main.key("slimeball"), new ItemStack(Material.SLIME_BALL)).shape("vsv", "sls", "vsv");
		slimeball.setIngredient('v', Material.VINE);
		slimeball.setIngredient('l', Material.LILY_PAD);
		slimeball.setIngredient('s', Material.SUGAR_CANE);
		Bukkit.addRecipe(slimeball);*/
		
		ShapedRecipe enderportal = new ShapedRecipe(main.key("enderportal"), new ItemStack(Material.END_PORTAL_FRAME)).shape("p p", "s s", "sss");
		enderportal.setIngredient('p', Material.ENDER_PEARL);
		enderportal.setIngredient('s', Material.END_STONE);
		Bukkit.addRecipe(enderportal);
		
		ShapelessRecipe craftstickrecp = new ShapelessRecipe(main.key("craftstick"), vars.CraftStick);
		craftstickrecp.addIngredient(Material.CRAFTING_TABLE);
		craftstickrecp.addIngredient(Material.STICK);
		Bukkit.addRecipe(craftstickrecp);

		ShapelessRecipe sleepbag = new ShapelessRecipe(main.key("sleepbag"), vars.sleepbag);
		sleepbag.addIngredient(Material.WHITE_CARPET);
		sleepbag.addIngredient(Material.WHITE_WOOL);
		Bukkit.addRecipe(sleepbag);
		
		ShapedRecipe MoltenPickRecp = new ShapedRecipe(main.key("MoltenPick"), vars.MoltenPickaxe).shape("dnd", " m ", " s ");
		MoltenPickRecp.setIngredient('d', Material.DIAMOND);
		MoltenPickRecp.setIngredient('n', Material.EMERALD);
		MoltenPickRecp.setIngredient('s', Material.STICK);
		MoltenPickRecp.setIngredient('m', Material.MAGMA_CREAM);
		Bukkit.addRecipe(MoltenPickRecp);
		
		/*ShapedRecipe StoneCarveRecp = new ShapedRecipe(main.key("Stonecarver"), vars.StoneCarver).shape(" d ", " s ", "   ");
		StoneCarveRecp.setIngredient('d', Material.DIAMOND);
		StoneCarveRecp.setIngredient('s', Material.IRON_SHOVEL);
		Bukkit.addRecipe(StoneCarveRecp);*/
		
		/*ShapedRecipe InstElevRecp = new ShapedRecipe(main.key("InstElev"), vars.InstElev).shape(" i ", "isi", "oi ");
		InstElevRecp.setIngredient('i', Material.IRON_INGOT);
		InstElevRecp.setIngredient('s', Material.STRING);
		InstElevRecp.setIngredient('o', Material.STICK);
		Bukkit.addRecipe(InstElevRecp);*/
		
		ShapedRecipe TeleRecp = new ShapedRecipe(main.key("Tele"), vars.Teleporter).shape(" b ", "wdw", " s ");
		TeleRecp.setIngredient('b', Material.IRON_BARS);
		TeleRecp.setIngredient('w', Material.OAK_PLANKS);
		TeleRecp.setIngredient('s', Material.STICK);
		TeleRecp.setIngredient('d', Material.DIAMOND);
		Bukkit.addRecipe(TeleRecp);
		
		/*ShapedRecipe MagRecp = new ShapedRecipe(main.key("Mag"), vars.Magnet).shape("sps", " i ", " s ");
		MagRecp.setIngredient('i', Material.IRON_INGOT);
		MagRecp.setIngredient('s', Material.STICK);
		MagRecp.setIngredient('p', Material.ENDER_PEARL);
		Bukkit.addRecipe(MagRecp);*/
		
		/*ShapedRecipe HoleRecp = new ShapedRecipe(main.key("Hole"), vars.PHole).shape(" o ", "ses", " o ");
		HoleRecp.setIngredient('o', Material.OBSIDIAN);
		HoleRecp.setIngredient('s', Material.SNOWBALL);
		HoleRecp.setIngredient('e', Material.ENDER_EYE);
		Bukkit.addRecipe(HoleRecp);*/
		
		ShapedRecipe ShellRecp = new ShapedRecipe(main.key("Shell"), vars.MShell).shape("dgd", "dhd", "c c");
		ShellRecp.setIngredient('d', Material.DIAMOND);
		ShellRecp.setIngredient('g', Material.GOLD_INGOT);
		ShellRecp.setIngredient('h', Material.TURTLE_HELMET);
		ShellRecp.setIngredient('c', Material.CONDUIT);
		Bukkit.addRecipe(ShellRecp);
		}

	@SuppressWarnings("unused")
	private void addFlyBootRecipes() {
		ShapedRecipe heavenrecp = new ShapedRecipe(main.key("heavengold"), vars.heavengold).shape(" g ", "gng", " g ");
		heavenrecp.setIngredient('g', Material.GOLD_BLOCK);
		heavenrecp.setIngredient('n', Material.NETHER_STAR);
		Bukkit.addRecipe(heavenrecp);
		
		flyrecp = new ShapedRecipe(main.key("flyboots"), vars.flyboots).shape("   ", "h h", "h h");
		flyrecp.setIngredient('h', vars.heavengold.getData());
		Bukkit.addRecipe(flyrecp);
	}
}
