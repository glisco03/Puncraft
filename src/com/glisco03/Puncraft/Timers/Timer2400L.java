package com.glisco03.Puncraft.Timers;

import com.glisco03.Puncraft.Utils.PuntestManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.glisco03.Puncraft.Utils.SurveyManager;
import com.glisco03.Puncraft.main.vars;

import java.util.UUID;

public class Timer2400L implements Runnable{

	public void run() {
		if(SurveyManager.isRunning()) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(!SurveyManager.alreadyVoted(p.getUniqueId().toString())) {
					p.sendMessage(vars.prefix + "§a" + SurveyManager.getCurrentSurvey());
				}
			}
		}

		if(PuntestManager.isRunning()) {
			for(Player p : Bukkit.getOnlinePlayers()) {

				if(PuntestManager.getState()){
					if (!PuntestManager.getVoted().contains(p.getUniqueId().toString())) {
						p.playSound(p.getLocation(), "minecraft:pc.bell", 0.8f, 2f);
						p.sendMessage(vars.punprefix + "§4§lDu hast noch nicht abgestimmt");
						p.sendMessage(vars.punprefix + "§bAlle Puns:");
						UUID uid;
						for (String uuid : PuntestManager.getSubmitted()) {
							uid = UUID.fromString(uuid);
							p.sendMessage(vars.punprefix + "§b" + Bukkit.getServer().getOfflinePlayer(uid).getName() + "§a: " + PuntestManager.getPun(uuid) + "(" + PuntestManager.getPunID(uuid) + ")");
						}
						p.sendMessage(vars.punprefix + "§5§lStimme jetzt mit /puntest vote \"Zahl hinter dem Pun\" für den besten Pun ab");
					}
				} else {
					p.playSound(p.getLocation(), "minecraft:pc.bell", 0.8f, 2f);
					p.sendMessage(vars.punprefix + "§bEin Puntest läuft und du hast noch nichts eingereicht!");
					p.sendMessage(vars.punprefix + "§bDas Thema lautet: §a" + PuntestManager.getCurrentTopic());
					p.sendMessage(vars.punprefix + "§bSchicke deinen Pun mit /puntest submit \"Dein Pun\" ab!");
				}
			}


		}
	}
}
