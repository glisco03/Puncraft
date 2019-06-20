package com.glisco03.Puncraft.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.glisco03.Puncraft.Utils.PuntestManager;
import com.glisco03.Puncraft.main.vars;

public class command_puntest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player p = null;

		if (sender instanceof Player) {
			p = (Player) sender;
		}

		if (command.getName().equalsIgnoreCase("puntest")) {
			if (args.length < 1) {
			} else if (args.length == 1 && isAlphaNumeric(args[0])) {
				if (args[0].equalsIgnoreCase("info")) {
					if (PuntestManager.isRunning()) {
						sender.sendMessage(vars.punprefix + "§bThema: §a" + PuntestManager.getCurrentTopic());
						sender.sendMessage(vars.punprefix + "§bTeilnehmer: §a" + PuntestManager.getPunAmount());
						if (PuntestManager.hasSubmitted(p.getUniqueId().toString())) {
							sender.sendMessage("§bDein Pun: §a" + PuntestManager.getPun(p.getUniqueId().toString()));
						} else {
							sender.sendMessage("§4§lDu hast noch keinen Pun abgeschickt!");
						}
						return true;
					} else {
						sender.sendMessage(vars.punprefix + "§cEs läuft kein Puntest!");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("stop")) {
					if (sender.hasPermission("Puncraft.puntest.stop")) {
						if (PuntestManager.isRunning()) {
							for (Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(vars.punprefix + "§bDer Puntest hat geendet!");
								if (PuntestManager.getPunAmount() == 0) {
									player.sendMessage("§4Es wurden keine Puns abgeschickt!");
								} else {
									player.sendMessage(vars.punprefix + "§6Sieger: ");
									player.sendMessage(vars.punprefix + "§b" + Bukkit.getPlayer(UUID.fromString(PuntestManager.getRanking().get(0))).getDisplayName() + "§b: " + PuntestManager.getPun(PuntestManager.getRanking().get(0)));
									for (int i = 0; i < PuntestManager.getRanking().size() - 1; i++) {
										player.sendMessage(vars.punprefix + "§7" + (i + 2) + ". Platz: ");
										player.sendMessage(vars.punprefix + Bukkit.getPlayer(UUID.fromString(PuntestManager.getRanking().get(i + 1))).getDisplayName() + "§b: " + PuntestManager.getPun(PuntestManager.getRanking().get(i + 1)));
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
							for (Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(vars.punprefix + "§bDie Einreichphase ist vorbei!");
								player.sendMessage(vars.punprefix + "§bAlle Puns:");
								UUID uid = null;
								for (String uuid : PuntestManager.getSubmitted()) {
									uid = UUID.fromString(uuid);
									player.sendMessage(vars.punprefix + "§b" + Bukkit.getPlayer(uid).getDisplayName() + ": " + PuntestManager.getPun(uuid) + "(" + PuntestManager.getPunID(uuid) + ")");
								}
								player.sendMessage(vars.punprefix + "§5§lStimme jetzt mit /puntest vote \"Zahl hinter dem Pun\" für den besten Pun ab");
							}

							PuntestManager.setState(true);
							return true;
						} else {
							sender.sendMessage(vars.punprefix + "§cEs läuft kein Puntest!");
							return true;
						}

					} else {
						sender.sendMessage("§cDu hast keine Permission!");
						return true;
					}

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
							sender.sendMessage("§cDu hast bereits einen Pun abgeschickt!");
							return true;
						}
					} else {
						sender.sendMessage(vars.punprefix + "§cEs läuft kein Puntest!");
						return true;
					}

				} else if (args[0].equalsIgnoreCase("vote")) {
					if (PuntestManager.isRunning()) {
						if (!PuntestManager.getVoted().contains(p.getUniqueId().toString())) {
							String punid = args[1];
							PuntestManager.voteForPun(punid, p.getUniqueId().toString());
							sender.sendMessage(vars.punprefix + "§2Dein Vote wurde abgeschickt!");
							// (sender.sendMessage(vars.punprefix +
							// PuntestManager.getVotes(PuntestManager.getUUIDByPunID(punid)));
							return true;
						} else {
							sender.sendMessage("§cDu hast bereits gevotet!");
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

	public boolean isAlphaNumeric(String s) {
		String pattern = "^[a-zA-Z0-9]*$";
		return s.matches(pattern);
	}
}
