package com.glisco03.Puncraft.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SurveyManager {

	private static File f = new File("plugins/Puncraft", "survey.yml");
	private static FileConfiguration configuration = YamlConfiguration.loadConfiguration(f);

	private static void save(File file, FileConfiguration configuration) {
		try {
			configuration.save(file);
		} catch (IOException e) {
		}
	}

	private static List<String> hasVoted = configuration.getStringList("hasVoted");

	public static boolean alreadyVoted(String uuid) {
		return hasVoted.contains(uuid);
	}

	public static void createSurvey(String survey) {
		configuration.set("survey", survey);
		save(f, configuration);
	}

	public static String getCurrentSurvey() {
		return configuration.getString("survey");
	}

	public static boolean isRunning() {
		return f.exists();
	}

	public static void deleteSurvey() {
		f.delete();
		configuration.set("yes", 0);
		configuration.set("no", 0);
		hasVoted.clear();
	}

	public static int getYes() {
		return configuration.getInt("yes");
	}

	public static int getNo() {
		return configuration.getInt("no");
	}

	public static void voteYes(String uuid) {
		int yes = getYes() + 1;
		configuration.set("yes", yes);
		hasVoted.add(uuid);
		configuration.set("hasVoted",hasVoted);
		save(f, configuration);
	}

	public static void voteNo(String uuid) {
		int no = getNo() + 1;
		configuration.set("no", no);
		hasVoted.add(uuid);
		configuration.set("hasVoted", hasVoted);
		save(f, configuration);
	}

	public static int getVotes() {
		return hasVoted.size();
	}

}