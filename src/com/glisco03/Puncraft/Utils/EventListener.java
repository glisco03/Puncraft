package com.glisco03.Puncraft.Utils;

import com.earth2me.essentials.Essentials;
import com.glisco03.Puncraft.main.vars;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

;

public class EventListener implements Listener {

    Fireball fb;

    Location loc;

    Dispenser dis;

    Inventory inv = Bukkit.createInventory(null, 9, "haha");

    Essentials E;

    Economy eco;

    public EventListener(JavaPlugin plugin, Economy e) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        E = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
        eco = e;
    }
	
	/*@EventHandler
	public void onQuiz(AsyncPlayerChatEvent e) {
		if(!QuizManager.hasRegged(e.getPlayer().getUniqueId().toString())) {
			e.setCancelled(true);
			if(e.getMessage().equalsIgnoreCase("fertig") && !e.getPlayer().hasMetadata("quizstep1")) {
				e.getPlayer().setMetadata("quizstep1", new FixedMetadataValue(main.fplugin, "0"));
				e.getPlayer().sendMessage("ï¿½cFrage 1: ï¿½bWas ist laut Punkt 4 der Regeln verboten?");
			} else if(e.getPlayer().hasMetadata("quizstep1")) {
				if(e.getMessage().equalsIgnoreCase("stehlen")) {
					e.getPlayer().sendMessage("ï¿½2ï¿½lRichtig!");
					e.getPlayer().removeMetadata("quizstep1", main.fplugin);
					e.getPlayer().setMetadata("quizstep2", new FixedMetadataValue(main.fplugin, "0"));
					e.getPlayer().sendMessage("ï¿½cFrage 2: ï¿½bWenn man Bï¿½ume abholzt, muss man diese ...");
				} else {
					e.getPlayer().sendMessage("ï¿½cï¿½lFalsch!");
				}
			} else if(e.getPlayer().hasMetadata("quizstep2")) {
				if(e.getMessage().equalsIgnoreCase("nachpflanzen")) {
					e.getPlayer().sendMessage("ï¿½2ï¿½lRichtig!");
					e.getPlayer().removeMetadata("quizstep2", main.fplugin);
					e.getPlayer().setMetadata("quizstep3", new FixedMetadataValue(main.fplugin, "0"));
					e.getPlayer().sendMessage("ï¿½cFrage 3: ï¿½bMehr als insgesamt ... Blï¿½cke Grundstï¿½cksflï¿½che mï¿½ssen in einer Umfrage genehmight werden.");
				} else {
					e.getPlayer().sendMessage("ï¿½cï¿½lFalsch!");
				}
			} else if(e.getPlayer().hasMetadata("quizstep3")) {
				if(e.getMessage().equalsIgnoreCase("256")) {
					e.getPlayer().sendMessage("ï¿½2ï¿½lRichtig!");
					e.getPlayer().removeMetadata("quizstep3", main.fplugin);
					e.getPlayer().sendMessage("ï¿½aï¿½lGlï¿½ckwunsch, du hast das Quiz bestanden!");
					BountifulAPI.sendTitle(e.getPlayer(), 5, 280, 40, "ï¿½aFï¿½2uï¿½3nï¿½4cï¿½5rï¿½6aï¿½7fï¿½8t ï¿½94", "ï¿½bWillkommen ï¿½6" + e.getPlayer().getDisplayName());
					QuizManager.register(e.getPlayer().getUniqueId().toString());
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "bp user " + e.getPlayer().getName() + " group set Spieler");
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					e.getPlayer().performCommand("spawn");
				} else {
					e.getPlayer().sendMessage("ï¿½cï¿½lFalsch!");
				}
			}
		} 
	}*/
	
	/*@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(!QuizManager.hasRegged(e.getPlayer().getUniqueId().toString())) {
			e.setJoinMessage("ï¿½bViel Glï¿½ck " + e.getPlayer().getDisplayName());
			e.getPlayer().sendMessage("ï¿½aHallo! Lies dir bitte sorgfï¿½ltig mit ï¿½l/rules 1/2/3 ï¿½rï¿½adie Regeln durch!");
			e.getPlayer().sendMessage("ï¿½aWenn du das getan hast, schreibe \"fertig\" in den Chat, um das Quiz zu starten!");
			if(e.getPlayer().getWorld().getName().equalsIgnoreCase("Funcraft 4")) {
				e.getPlayer().teleport(vars.quiz(e.getPlayer().getWorld()));	
			}
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "bp user " + e.getPlayer().getName() + " group set Quiz");
		}
	}*/

    @EventHandler
    public void onStickWand(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().equals(vars.StickWand) && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            Player player = e.getPlayer();
            Location playerloc = e.getPlayer().getLocation();
            Location fbLocation = playerloc.add(playerloc.getDirection().normalize().multiply(2).toLocation(player.getWorld(), playerloc.getYaw(), playerloc.getPitch())).add(0, 1D, 0);
            final Fireball f = player.getWorld().spawn(fbLocation, Fireball.class);
            f.setYield(5f);
        }
    }

    @EventHandler
    public void onBootsCrafted(CraftItemEvent e) {
        if (e.getInventory().getItem(4) != null && e.getInventory().getItem(6) != null && e.getInventory().getItem(7) != null && e.getInventory().getItem(9) != null) {
            if (e.getInventory().getItem(4).getType().equals(Material.GOLD_BLOCK) && e.getInventory().getItem(6).getType().equals(Material.GOLD_BLOCK) && e.getInventory().getItem(7).getType().equals(Material.GOLD_BLOCK) && e.getInventory().getItem(9).getType().equals(Material.GOLD_BLOCK)) {
                if (e.getInventory().getItem(4).equals(vars.heavengold) && e.getInventory().getItem(6).equals(vars.heavengold) && e.getInventory().getItem(7).equals(vars.heavengold) && e.getInventory().getItem(9).equals(vars.heavengold)) {
                } else {
                    e.getInventory().clear();
                    e.setCancelled(true);
                }
            }
        }
    }

	/*@EventHandler
	public void onFarmlandCreated(PlayerInteractEvent e) {
		
		Location loc;
		
		if(e.getClickedBlock() != null) {
			if(e.getClickedBlock().getType().equals(Material.DIRT) || e.getClickedBlock().getType().equals(Material.GRASS)) {
				if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					if(e.getPlayer().getInventory().getItemInMainHand().equals(vars.EmeraldHoe)) {
						loc = e.getClickedBlock().getLocation();
						loc.subtract(0, 0, 1);
						if(loc.getBlock().getType().equals(Material.DIRT) || loc.getBlock().getType().equals(Material.GRASS)) {
							loc.getBlock().setType(Material.FARMLAND);
						}
						loc.subtract(0, 0, -2);
						if(loc.getBlock().getType().equals(Material.DIRT) || loc.getBlock().getType().equals(Material.GRASS)) {
							loc.getBlock().setType(Material.FARMLAND);
						}
						loc.subtract(1, 0, 1);
						if(loc.getBlock().getType().equals(Material.DIRT) || loc.getBlock().getType().equals(Material.GRASS)) {
							loc.getBlock().setType(Material.FARMLAND);
						}
						loc.subtract(-2, 0, 0);
						if(loc.getBlock().getType().equals(Material.DIRT) || loc.getBlock().getType().equals(Material.GRASS)) {
							loc.getBlock().setType(Material.FARMLAND);
						}
						loc.subtract(0, 0, 1);
						if(loc.getBlock().getType().equals(Material.DIRT) || loc.getBlock().getType().equals(Material.GRASS)) {
							loc.getBlock().setType(Material.FARMLAND);
						}
						loc.subtract(0, 0, -2);
						if(loc.getBlock().getType().equals(Material.DIRT) || loc.getBlock().getType().equals(Material.GRASS)) {
							loc.getBlock().setType(Material.FARMLAND);
						}
						loc.subtract(2, 0, 0);
						if(loc.getBlock().getType().equals(Material.DIRT) || loc.getBlock().getType().equals(Material.GRASS)) {
							loc.getBlock().setType(Material.FARMLAND);
						}
						loc.subtract(0, 0, 2);
						if(loc.getBlock().getType().equals(Material.DIRT) || loc.getBlock().getType().equals(Material.GRASS)) {
							loc.getBlock().setType(Material.FARMLAND);
						}
					}
				}
			}
		}
			
	}*/

    @EventHandler
    public void onFreezedPlayerMove(PlayerMoveEvent e) {
        if (vars.toFreeze.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFreezedPlayerBreak(BlockBreakEvent e) {
        if (vars.toFreeze.contains(e.getPlayer())) {
            e.setCancelled(true);
        } else if (vars.x.contains(e.getBlock().getX()) && vars.y.contains(e.getBlock().getY()) && vars.z.contains(e.getBlock().getZ())) {
            vars.x.remove(vars.x.indexOf(e.getBlock().getX()));
            vars.y.remove(vars.y.indexOf(e.getBlock().getY()));
            vars.z.remove(vars.z.indexOf(e.getBlock().getZ()));
        }
    }

    @EventHandler
    public void onFreezedPlayerBuild(BlockPlaceEvent e) {
        if (vars.toFreeze.contains(e.getPlayer())) {
            e.setCancelled(true);
        } else if (e.getBlock().getType().equals(Material.DISPENSER)) {
            dis = (Dispenser) e.getBlock().getState();
            if (dis.getCustomName() != null) {
                if (dis.getCustomName().equals(vars.BlockBreaker.getItemMeta().getDisplayName())) {
                    WorldManager.registerBreaker(e.getBlock());
                }
            }
        }
    }

    @SuppressWarnings("incomplete-switch")
    @EventHandler
    public void onBlockBreaker(BlockRedstoneEvent e) {
        for (Block b : SurroundingBlocks(e.getBlock())) {
            if (b.getType().equals(Material.DISPENSER)) {
                if (vars.x.contains(b.getX()) && vars.y.contains(b.getY()) && vars.z.contains(b.getZ()) && b.isBlockPowered() || vars.x.contains(b.getX()) && vars.y.contains(b.getY()) && vars.z.contains(b.getZ()) && b.isBlockIndirectlyPowered()) {
                    @SuppressWarnings("deprecation")
                    BlockFace targetFace = ((org.bukkit.material.Dispenser) b.getState().getData()).getFacing();
                    dis = (Dispenser) b.getState();
                    loc = b.getLocation();
                    switch (targetFace) {
                        case UP:
                            loc.add(0, 1, 0);
                            if (e.getBlock().getType() != Material.BEDROCK) {
                                dis.getInventory().addItem(new ItemStack(loc.getBlock().getType()));
                                loc.getBlock().setType(Material.AIR);
                            }
                            break;
                        case DOWN:
                            loc.add(0, -1, 0);
                            if (e.getBlock().getType() != Material.BEDROCK) {
                                dis.getInventory().addItem(new ItemStack(loc.getBlock().getType()));
                                loc.getBlock().setType(Material.AIR);
                            }
                            break;
                        case NORTH:
                            loc.add(0, 0, -1);
                            if (e.getBlock().getType() != Material.BEDROCK) {
                                dis.getInventory().addItem(new ItemStack(loc.getBlock().getType()));
                                loc.getBlock().setType(Material.AIR);
                            }
                            break;
                        case EAST:
                            loc.add(1, 0, 0);
                            if (e.getBlock().getType() != Material.BEDROCK) {
                                dis.getInventory().addItem(new ItemStack(loc.getBlock().getType()));
                                loc.getBlock().setType(Material.AIR);
                            }
                            break;
                        case WEST:
                            loc.add(-1, 0, 0);
                            if (e.getBlock().getType() != Material.BEDROCK) {
                                dis.getInventory().addItem(new ItemStack(loc.getBlock().getType()));
                                loc.getBlock().setType(Material.AIR);
                            }
                            break;
                        case SOUTH:
                            loc.add(0, 0, 1);
                            if (e.getBlock().getType() != Material.BEDROCK) {
                                dis.getInventory().addItem(new ItemStack(loc.getBlock().getType()));
                                loc.getBlock().setType(Material.AIR);
                            }
                            break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBreakerDrop(BlockDispenseEvent e) {
        if (vars.x.contains(e.getBlock().getX()) && vars.y.contains(e.getBlock().getY()) && vars.z.contains(e.getBlock().getZ())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCombinerControls(InventoryClickEvent e) {
        if (CombinerManager.getPlayers().contains(e.getWhoClicked())) {
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().equals(CombinerManager.barrier)) {
                    e.setCancelled(true);
                } else if (e.getCurrentItem().equals(CombinerManager.wool)) {
                    CombinerManager.startCombining((Player) e.getWhoClicked());
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onCombinerExit(InventoryCloseEvent e) {
        if (CombinerManager.getPlayers().contains(e.getPlayer())) {
            e.getPlayer().getInventory().addItem(e.getPlayer().getOpenInventory().getItem(1));
            e.getPlayer().getInventory().addItem(e.getPlayer().getOpenInventory().getItem(3));
            e.getPlayer().getInventory().addItem(e.getPlayer().getOpenInventory().getItem(7));
            CombinerManager.combinerClosed((Player) e.getPlayer());
        }
    }

    @EventHandler
    public void onCraftStick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getPlayer().getInventory().getItemInMainHand().equals(vars.CraftStick)) {
                e.getPlayer().openWorkbench(e.getPlayer().getLocation(), true);
            }
        }
    }

    @EventHandler
    public void onHelmet(InventoryClickEvent e) {
        if(e.getSlotType() == InventoryType.SlotType.ARMOR && e.getCurrentItem().getItemMeta().hasDisplayName()){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8Miners Helmet")){
                e.getWhoClicked().removePotionEffect(PotionEffectType.NIGHT_VISION);
                e.getWhoClicked().removePotionEffect(PotionEffectType.FAST_DIGGING);
            }
        }
    }

    @EventHandler
    public void onTeleport(EntityTeleportEvent e) {
        e.getFrom().getWorld().spawnParticle(Particle.CRIT_MAGIC, e.getFrom(), 50);
        e.getTo().getWorld().spawnParticle(Particle.CRIT_MAGIC, e.getFrom(), 50);
    }

	/*@EventHandler
	public void onCreeper(ExplosionPrimeEvent e) {
		if(e.getEntityType() == EntityType.CREEPER) {
			e.setCancelled(true);
			Location loc = e.getEntity().getLocation();
			for(int z = 0; z < 250; z++) {
				e.getEntity().getWorld().spawnEntity(loc, EntityType.EXPERIENCE_ORB);
				loc.add(0, 0.5, 0);
			}
			ParticleManager.spawnGenericPublicParticle(e.getEntity().getLocation(), 2, 10, 2, EnumParticle.CLOUD, (float) 0.05, 100);
			e.getEntity().remove();
		}
	}*/

	/*@EventHandler
	public void onSleepBag(PlayerInteractEvent e) throws InterruptedException {
		if(e.getPlayer().getInventory().getItemInMainHand().equals(vars.sleepbag) && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			Player p = e.getPlayer();
			World w = p.getWorld();
			if(w.getFullTime() > 13000) {
				p.setBedSpawnLocation(p.getLocation(), true);
				w.setFullTime(1000);
				w.setStorm(false);
				w.setThundering(false);
			} else if(w.getFullTime() < 13000 && w.getFullTime() > 1020){
				BountifulAPI.sendActionBar(p, "You can only sleep at night");
			}
		}
	}*/

    @EventHandler
    public void onOreBreak(BlockBreakEvent e) {

        int amount = 1;

        if (e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()) != null) {
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
                if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§6§lMolten Pickaxe")) {
                    if (vars.meltores.contains(e.getBlock().getType())) {
                        e.setDropItems(false);

                        if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                            amount = amount + e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                        }

                        ItemStack ironIngot = new ItemStack(Material.IRON_INGOT, amount);
                        ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT, amount);

                        if (e.getBlock().getType().equals(vars.meltores.get(0))) {
                            e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), ironIngot);
                        }
                        if (e.getBlock().getType().equals(vars.meltores.get(1))) {
                            e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), goldIngot);
                        }

                        e.getBlock().getLocation().getWorld().spawnParticle(Particle.FLAME, e.getBlock().getLocation(), 25);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onHeadBuy(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType() == Material.JUNGLE_SIGN) {
                Sign s = (Sign) e.getClickedBlock().getState();
                if (s.getLine(2).equalsIgnoreCase("Player_Head")) {
                    Boolean found = false;
                    for (ItemStack i : e.getPlayer().getInventory().getContents()) {
                        try {
                            if (i.getType().equals(Material.PLAYER_HEAD)) {
                                if (!i.getItemMeta().hasDisplayName()) {
                                    ItemMeta m = i.getItemMeta();
                                    m.setDisplayName("§6Credit");
                                    i.setItemMeta(m);
                                } else {
                                    if (i.getItemMeta().getDisplayName().equals("§6Credit") && i.getAmount() != 64 && found == false) {
                                        found = true;
                                        for (ItemStack it : e.getPlayer().getInventory().getContents()) {
                                            if (it.getType().equals(Material.PLAYER_HEAD)) {
                                                ItemMeta n = it.getItemMeta();
                                                if (!n.hasDisplayName()) {
                                                    it.setAmount(0);
                                                    i.setAmount(i.getAmount() + 1);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Block> SurroundingBlocks(Block block) {
        ArrayList<Block> Blocks = new ArrayList<Block>();
        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.UP) {
                Block above = block.getRelative(BlockFace.UP);
                Block above2 = above.getRelative(BlockFace.UP);
                Blocks.add(above);
                Blocks.add(above2);
            }
            Blocks.add(block.getRelative(face));
        }
        return Blocks;
    }
}
