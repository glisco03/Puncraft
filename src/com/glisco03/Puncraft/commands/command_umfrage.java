package com.glisco03.Puncraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.glisco03.Puncraft.Utils.SurveyManager;
import com.glisco03.Puncraft.main.vars;

public class command_umfrage implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = null;
		
		if(sender instanceof Player) {
			p = (Player) sender;
		}
		
		if(command.getName().equalsIgnoreCase("umfrage")) {
			if(args.length<1) {
			} else if(args.length == 1 && isAlphaNumeric(args[0])) {
				if(args[0].equalsIgnoreCase("info")) {
					if(SurveyManager.isRunning()) {
						sender.sendMessage(vars.prefix + "§bFrage: §a" + SurveyManager.getCurrentSurvey());
						sender.sendMessage(vars.prefix + "§bJa-Stimmen: §a" + SurveyManager.getYes());
						sender.sendMessage(vars.prefix + "§bNein-Stimmen: §a" + SurveyManager.getNo());
						return true;
					} else {
						sender.sendMessage(vars.prefix + "§cEs läuft keine Umfrage!");
						return true;
					}
				} else if(args[0].equalsIgnoreCase("stop")) {
					if(sender.hasPermission("Puncraft.umfrage.stop")) {
						if(SurveyManager.isRunning()) {
							for(Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(vars.prefix + "§bDie Umfrage hat geendet!");
								player.sendMessage(vars.prefix + "§bErgebnisse:");
								player.sendMessage(vars.prefix + "§bJa-Stimmen: §a" + SurveyManager.getYes());
								player.sendMessage(vars.prefix + "§bNein-Stimmen: §a" + SurveyManager.getNo());
								player.sendMessage(vars.prefix + "§bTeilnehmer: §a" + SurveyManager.getVotes());
								if(SurveyManager.getYes()>SurveyManager.getNo()) {
									player.sendMessage(vars.prefix + "§bDie Umfrage wurde für §a§lJA§r§b entschieden!");	
								} else if(SurveyManager.getYes()<SurveyManager.getNo()){
									player.sendMessage(vars.prefix + "§bDie Umfrage wurde für §c§lNEIN§r§b entschieden!");
								} else if(SurveyManager.getYes()==SurveyManager.getNo()){
									player.sendMessage(vars.prefix + "§bDie Umfrage endete §1§lUNENTSCHIEDEN");
								}
							}
							
							SurveyManager.deleteSurvey();
							sender.sendMessage(vars.prefix + "§cUmfrage gelöscht");
							return true;
						} else {
							sender.sendMessage(vars.prefix + "§cEs läuft keine Umfrage!");
							return true;
						}
					
					} else {
						sender.sendMessage("§cDu hast keine Permission!");
						return true;
					}
					
				} else if(args[0].equalsIgnoreCase("ja")) {
					if(SurveyManager.isRunning()) {
						if(!SurveyManager.alreadyVoted(p.getUniqueId().toString())) {
							SurveyManager.voteYes(p.getUniqueId().toString());
							sender.sendMessage(vars.prefix + "§bDu hast für §a§lJA§r§b gestimmt!");
							return true;
						} else {
							sender.sendMessage(vars.prefix + "§bDu hast bereits abgestimmt!");
							return true;
						}
					} else {
						sender.sendMessage(vars.prefix + "§cEs läuft keine Umfrage!");
						return true;
					}
				} else if(args[0].equalsIgnoreCase("nein")) {
					if(SurveyManager.isRunning()) {
						if(!SurveyManager.alreadyVoted(p.getUniqueId().toString())) {
							SurveyManager.voteNo(p.getUniqueId().toString());
							sender.sendMessage(vars.prefix + "§bDu hast für §c§lNEIN§r§b gestimmt!");
							return true;
						} else {
							sender.sendMessage(vars.prefix + "§bDu hast bereits abgestimmt!");
							return true;
						}
					} else {
						sender.sendMessage(vars.prefix + "§cEs läuft keine Umfrage!");
						return true;
					}
				}
				
			} else if(args.length > 1 && isAlphaNumeric(args[0]) && isAlphaNumeric(args[1])) {
				if(args[0].equalsIgnoreCase("start")) {
					if(sender.hasPermission("Puncraft.umfrage.start")) {
						if(!SurveyManager.isRunning()) {
							String umfrage = "";
							for (int i = 1; i < args.length; i++) {
								umfrage = umfrage + args[i] + " ";
							}
						SurveyManager.createSurvey(umfrage);
						sender.sendMessage(vars.prefix + "§bUmfrage erstellt");
						for(Player player : Bukkit.getOnlinePlayers()) {
							player.sendMessage(vars.prefix + "§bEine neue Umfrage hat begonnen!");
							player.sendMessage(vars.prefix + "§bDie Frage lautet: §a" + SurveyManager.getCurrentSurvey());
							player.sendMessage(vars.prefix + "§bStimme mit /umfrage ja/nein ab!");
						}
						return true;
						} else {
							sender.sendMessage(vars.prefix + "§cEs läuft bereits eine Umfrage, lösche sie zuerst!");
							return true;
						}
					} else {
						sender.sendMessage("§cDu hast keine Permission!");
						return true;
					}
					
				}
			}
		}
		return false;
	}
	public boolean isAlphaNumeric(String s){
	    String pattern= "^[a-zA-Z0-9]*$";
	    return s.matches(pattern);
	}
}
