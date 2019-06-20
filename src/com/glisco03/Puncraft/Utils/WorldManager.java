package com.glisco03.Puncraft.Utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;

import com.glisco03.Puncraft.main.vars;

public class WorldManager {
	
	private static void save(File file, FileConfiguration configuration) {
		try {
			configuration.save(file);
		} catch (IOException e) {
		}
	}

	public static HashMap<Integer, Material> getSquare(Location loc) {
		HashMap<Integer, Material> square  = new HashMap<Integer, Material>();
		square.put(1, loc.add(1, 0, -1).getBlock().getType());
		square.put(2, loc.add(0, 0, 1).getBlock().getType());
		square.put(3, loc.add(0, 0, 1).getBlock().getType());
		square.put(4, loc.add(-1, 0, -2).getBlock().getType());
		square.put(5, loc.add(0, 0, 1).getBlock().getType());
		square.put(6, loc.add(0, 0, 1).getBlock().getType());
		square.put(7, loc.add(-1, 0, -2).getBlock().getType());
		square.put(8, loc.add(0, 0, 1).getBlock().getType());
		square.put(9, loc.add(0, 0, 1).getBlock().getType());
		return square;
	}
	
	public static HashMap<Integer, Material> getCross(Location loc) {
		HashMap<Integer, Material> cross  = new HashMap<Integer, Material>();
		cross.put(2, loc.add(1, 0, 0).getBlock().getType());
		cross.put(4, loc.add(-1, 0, -1).getBlock().getType());
		cross.put(5, loc.add(0, 0, 1).getBlock().getType());
		cross.put(6, loc.add(0, 0, 1).getBlock().getType());
		cross.put(8, loc.add(-1, 0, -1).getBlock().getType());
		return cross;
	}
	
	public static HashMap<Integer, Block> getCrossBlocks(Location loc) {
		HashMap<Integer, Block> crossBlocks  = new HashMap<Integer, Block>();
		crossBlocks.put(2, loc.add(1, 0, 0).getBlock());
		crossBlocks.put(4, loc.add(-1, 0, -1).getBlock());
		crossBlocks.put(5, loc.add(0, 0, 1).getBlock());
		crossBlocks.put(6, loc.add(0, 0, 1).getBlock());
		crossBlocks.put(8, loc.add(-1, 0, -1).getBlock());
		return crossBlocks;
	}

	public static void registerBreaker(Block b) {
		vars.x.add(b.getX());
		vars.y.add(b.getY());
		vars.z.add(b.getZ());
		vars.configuration.set("xco", vars.x);
		vars.configuration.set("yco", vars.y);
		vars.configuration.set("zco", vars.z);
		save(vars.f, vars.configuration);
	}
}
