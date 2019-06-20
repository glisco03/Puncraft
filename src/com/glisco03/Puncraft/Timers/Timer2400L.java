package com.glisco03.Puncraft.Timers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.glisco03.Puncraft.Utils.SurveyManager;
import com.glisco03.Puncraft.main.vars;

public class Timer2400L implements Runnable{

	public void run() {
		if(SurveyManager.isRunning()) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(!SurveyManager.alreadyVoted(p.getUniqueId().toString())) {
					p.sendMessage(vars.prefix + "§a" + SurveyManager.getCurrentSurvey());
				}
			}
		}
	}
}
