package com.glisco03.Puncraft.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.mojang.authlib.yggdrasil.response.User;
import io.netty.handler.codec.socksx.SocksPortUnificationServerHandler;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.glisco03.Puncraft.Utils.PuntestManager;
import com.glisco03.Puncraft.main.vars;
import sun.security.action.PutAllAction;

public class command_puntest implements TabExecutor {

    Economy eco;

    public command_puntest(Economy e){
        eco = e;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = null;

        if (sender instanceof Player) {
            p = (Player) sender;
        }

        if (command.getName().equalsIgnoreCase("puntest")) {
            if (args.length < 1) {
                p.sendMessage(vars.punprefix + "§4Da fehlt was, mein Freund!");
                return true;
            } else if (args.length == 1 && isAlphaNumeric(args[0])) {
                if (args[0].equalsIgnoreCase("info")) {
                    if (PuntestManager.isRunning()) {
                        sender.sendMessage(vars.punprefix + "§bThema: §a" + PuntestManager.getCurrentTopic());
                        sender.sendMessage(vars.punprefix + "§bTeilnehmer: §a" + PuntestManager.getPunAmount());
                        if (PuntestManager.hasSubmitted(p.getUniqueId().toString())) {
                            sender.sendMessage(vars.punprefix + "§bDein Pun: §a" + PuntestManager.getPun(p.getUniqueId().toString()));
                        } else {
                            sender.sendMessage(vars.punprefix + "§4§lDu hast noch keinen Pun abgeschickt!");
                        }
                        if (PuntestManager.getState() == true && !PuntestManager.getVoted().contains(p.getUniqueId().toString())) {
                            sender.sendMessage(vars.punprefix + "§4§lDu hast noch nicht abgestimmt");
                            p.sendMessage(vars.punprefix + "§bAlle Puns:");
                            UUID uid;
                            for (String uuid : PuntestManager.getSubmitted()) {
                                uid = UUID.fromString(uuid);
                                p.sendMessage(vars.punprefix + "§b" + Bukkit.getServer().getOfflinePlayer(uid).getName() + "§a: " + PuntestManager.getPun(uuid) + "(" + PuntestManager.getPunID(uuid) + ")");
                            }
                            p.sendMessage(vars.punprefix + "§5§lStimme jetzt mit /puntest vote \"Zahl hinter dem Pun\" für den besten Pun ab");
                        }
                        return true;
                    } else {
                        sender.sendMessage(vars.punprefix + "§cEs läuft kein Puntest!");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("stop")) {
                    if (sender.hasPermission("Puncraft.puntest.stop")) {
                        if (PuntestManager.isRunning()) {
                            Collection<Entity> j = Bukkit.getWorld("world").getNearbyEntities(new Location(Bukkit.getWorld("world"), 165, 118, 236), 2, 6, 2);
                            for(Entity e : j){
                                if(e.getType() == EntityType.ARMOR_STAND){
                                    e.remove();
                                }
                            }
                            PuntestManager.calculateLeaderboard(PuntestManager.getRanking());
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.playSound(player.getLocation(), "minecraft:pc.doom", 0.8f, 1f);
                                player.sendMessage(vars.punprefix + "§7------§r§bDer Puntest hat geendet!§7------");
                                if (PuntestManager.getPunAmount() == 0) {
                                    player.sendMessage(vars.punprefix + "§4Es wurden keine Puns abgeschickt!");
                                } else if (PuntestManager.votesSubmitted() == false) {
                                    player.sendMessage(vars.punprefix + "§4Es wurde nicht gevoted!");
                                } else {
                                    OfflinePlayer contestant = Bukkit.getServer().getOfflinePlayer(UUID.fromString(PuntestManager.getRanking().get(0)));
                                    player.sendMessage(vars.punprefix + "§61. Platz: ");
                                    player.sendMessage(vars.punprefix + "§b" + contestant.getName() + "§a: " + PuntestManager.getPun(PuntestManager.getRanking().get(0)));
                                    if(player == contestant){
                                        eco.depositPlayer(contestant, 2000);
                                        if(Bukkit.getOnlinePlayers().contains((Player)contestant)){
                                            ((Player) contestant).sendMessage(vars.punprefix + "§aDu hast §6§l2000 PunPoints§r§a gewonnen!");
                                        }
                                    }
                                    for (int i = 0; i < PuntestManager.getRanking().size() - 1; i++) {
                                        contestant = Bukkit.getServer().getOfflinePlayer(UUID.fromString(PuntestManager.getRanking().get(i + 1)));
                                        if (PuntestManager.getStateMap().get(PuntestManager.getRanking().get(i + 1)) == PuntestManager.getStateMap().get(PuntestManager.getRanking().get(i))) {
                                            player.sendMessage(vars.punprefix + "§7---");
                                            player.sendMessage(vars.punprefix  + "§b" + contestant.getName() + "§a: " + PuntestManager.getPun(PuntestManager.getRanking().get(i + 1)));
                                            if(player == contestant) {
                                                eco.depositPlayer(contestant, 2500 - (i + 1) * 500);
                                                if (Bukkit.getOnlinePlayers().contains((Player) contestant)) {
                                                    ((Player) contestant).sendMessage(vars.punprefix + "§aDu hast §6§l" + (2500 - (i + 1) * 500) + " PunPoints§r§a gewonnen!");
                                                }
                                            }
                                        } else {
                                            player.sendMessage(vars.punprefix + "§7----------");
                                            player.sendMessage(vars.punprefix + "§6" + (i + 2) + ". Platz: ");
                                            player.sendMessage(vars.punprefix  + "§b" + contestant.getName() + "§a: " + PuntestManager.getPun(PuntestManager.getRanking().get(i + 1)));
                                            if(player == contestant) {
                                                eco.depositPlayer(contestant, 2500 - (i + 2) * 500);
                                                if (Bukkit.getOnlinePlayers().contains((Player) contestant)) {
                                                    ((Player) contestant).sendMessage(vars.punprefix + "§aDu hast §6§l" + (2500 - (i + 2) * 500) + " PunPoints§r§a gewonnen!");
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            PuntestManager.deletePuntest();
                            sender.sendMessage(vars.punprefix + "§cPuntest gelöscht");
                            return true;
                        } else {
                            sender.sendMessage(vars.punprefix + "§cEs läuft kein Puntest!");
                            return true;
                        }

                    } else {
                        sender.sendMessage("§cDu hast keine Permission!");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("changestate")) {
                    if (sender.hasPermission("Puncraft.puntest.changestate")) {
                        if (PuntestManager.isRunning()) {
                            if (PuntestManager.getState() != true) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.sendMessage(vars.punprefix + "§bDie Einreichphase ist vorbei!");
                                    player.sendMessage(vars.punprefix + "§bAlle Puns:");
                                    UUID uid = null;
                                    for (String uuid : PuntestManager.getSubmitted()) {
                                        uid = UUID.fromString(uuid);
                                        player.sendMessage(vars.punprefix + "§b" + Bukkit.getServer().getOfflinePlayer(uid).getName() + "§a: " + PuntestManager.getPun(uuid) + "(" + PuntestManager.getPunID(uuid) + ")");
                                    }
                                    player.sendMessage(vars.punprefix + "§5§lStimme jetzt mit /puntest vote \"Zahl hinter dem Pun\" für den besten Pun ab");
                                }

                                PuntestManager.setState(true);
                                return true;
                            } else {
                                sender.sendMessage(vars.punprefix + "§cDie Votephase läuft bereits!");
                            }
                        } else {
                            sender.sendMessage(vars.punprefix + "§cEs läuft kein Puntest!");
                            return true;
                        }
                    } else {
                        sender.sendMessage("§cDu hast keine Permission!");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("reloadleaderbaord")) {
                    PuntestManager.redrawLeaderboard();
                    return true;
                } else {
                    p.sendMessage(vars.punprefix + "§4Da fehlt was, mein Freund!");
                    return true;
                }
            } else if (args.length > 1 && isAlphaNumeric(args[0]) && isAlphaNumeric(args[1])) {
                if (args[0].equalsIgnoreCase("start")) {
                    if (sender.hasPermission("Puncraft.puntest.start")) {
                        if (!PuntestManager.isRunning()) {
                            String topic = "";
                            for (int i = 1; i < args.length; i++) {
                                topic = topic + args[i] + " ";
                            }
                            PuntestManager.createPuntest(topic);
                            sender.sendMessage(vars.punprefix + "§2Puntest erstellt");
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.playSound(player.getLocation(), "minecraft:pc.bell", 0.8f, 2f);
                                player.sendMessage(vars.punprefix + "§bEin neuer Puntest hat begonnen!");
                                player.sendMessage(vars.punprefix + "§bDas Thema lautet: §a" + PuntestManager.getCurrentTopic());
                                player.sendMessage(vars.punprefix + "§bSchicke deinen Pun mit /puntest submit \"Dein Pun\" ab!");
                            }
                            return true;

                        } else {
                            sender.sendMessage(vars.punprefix + "§cEs läuft ein Puntest, lösche ihn zuerst!");
                            return true;
                        }
                    } else {
                        sender.sendMessage("§cDu hast keine Permission!");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("submit")) {
                    if (PuntestManager.isRunning()) {
                        if (!PuntestManager.getSubmitted().contains(p.getUniqueId().toString())) {

                            String submission = "";
                            for (int i = 1; i < args.length; i++) {
                                submission = submission + args[i] + " ";
                            }
                            PuntestManager.submitPun(submission, p.getUniqueId().toString());
                            sender.sendMessage(vars.punprefix + "§2Dein Pun wurde abgeschickt!");
                            return true;
                        } else {
                            sender.sendMessage(vars.punprefix + "§cDu hast bereits einen Pun abgeschickt!");
                            return true;
                        }
                    } else {
                        sender.sendMessage(vars.punprefix + "§cEs läuft kein Puntest!");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("vote")) {
                    if (PuntestManager.isRunning()) {
                        if (PuntestManager.getState() == true) {
                            if (!PuntestManager.getVoted().contains(p.getUniqueId().toString())) {
                                if (!(args.length > 1)) {
                                    p.sendMessage(vars.punprefix + "§4Du musst auch eine Nummer angeben!");
                                    return true;
                                } else {
                                    String punid = args[1];
                                    PuntestManager.voteForPun(punid, p.getUniqueId().toString());
                                    sender.sendMessage(vars.punprefix + "§2Dein Vote wurde abgeschickt!");
                                    return true;
                                }
                            } else {
                                sender.sendMessage(vars.punprefix + "§cDu hast bereits gevotet!");
                                return true;
                            }
                        } else {
                            sender.sendMessage(vars.punprefix + "§cDie Votephase hat noch nicht begonnen!");
                            return true;
                        }
                    } else {
                        sender.sendMessage(vars.punprefix + "§cEs läuft kein Puntest!");
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> result = new ArrayList<>();
        result.add("submit");
        result.add("vote");
        result.add("info");
        result.add("start");
        result.add("stop");
        result.add("changestate");
        result.add("reloadleaderbaord");
        return result;
    }

    public boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9öäüÖÄÜ]*$";
        return s.matches(pattern);
    }
}
