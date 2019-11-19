package com.glisco03.Puncraft.Utils;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class PuntestManager {

	private static File f = new File("plugins/Puncraft", "puntest.yml");
	private static FileConfiguration configuration = YamlConfiguration.loadConfiguration(f);

	private static File v = new File("plugins/Puncraft", "votes.yml");
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(v);

	private static File leaderboardfile = new File("plugins/Puncraft", "leaderboard.yml");
	private static FileConfiguration leadconfig = YamlConfiguration.loadConfiguration(leaderboardfile);

	private static void save(File file, FileConfiguration configuration) {
		try {
			configuration.save(file);
		} catch (IOException e) {
		}
	}

	private static List<String> hasSubmitted = configuration.getStringList("hasSubmitted");
	private static List<String> puns = configuration.getStringList("puns");
	private static List<String> hasVoted = configuration.getStringList("hasVoted");

	private static int punAmount;

	public static boolean alreadySubmitted(String uuid) {
		return hasSubmitted.contains(uuid);
	}

	public static void createPuntest(String topic) {
		configuration = YamlConfiguration.loadConfiguration(f);
		config = YamlConfiguration.loadConfiguration(v);
		configuration.set("topic", topic);
		configuration.set("state", false);
		save(f, configuration);
		save(v, config);
	}

	public static String getCurrentTopic() {
		return configuration.getString("topic");
	}

	public static boolean isRunning() {
		return f.exists();
	}

	public static void deletePuntest() {
		f.delete();
		v.delete();
		puns.clear();
		hasSubmitted.clear();
		hasVoted.clear();
		punAmount = 0;
	}

	public static void submitPun(String pun, String uuid) {
		hasSubmitted.add(uuid);
		puns.add(pun);
		punAmount = configuration.getInt("punAmount") + 1;
		configuration.set("hasSubmitted", hasSubmitted);
		configuration.set("puns", puns);
		configuration.set(uuid, pun);
		configuration.set(pun, punAmount);
		configuration.set("punAmount", punAmount);
		config.set(punAmount + "", uuid);
		save(v, config);
		save(f, configuration);
	}

	public static int getPunAmount() {
		return puns.size();
	}

	public static List<String> getSubmitted() {
		return hasSubmitted;
	}

	public static boolean hasSubmitted(String uuid) {
		return hasSubmitted.contains(uuid);
	}

	public static String getPun(String uuid) {
		return configuration.getString(uuid);
	}

	public static boolean getState() {
		return configuration.getBoolean("state");
	}

	public static void setState(boolean state) {
		configuration.set("state", state);
		save(f, configuration);
	}

	public static void voteForPun(String punid, String uid) {
		String uuid = config.getString(punid + "");
		config.set(uuid, config.getInt(uuid) + 1);
		hasVoted.add(uid);
		configuration.set("hasVoted", hasVoted);
		config.set("votesSubmitted", true);
		save(v, config);
		save(f, configuration);
	}

	public static int getPunID(String uuid) {
		return configuration.getInt(getPun(uuid));
	}

	public static int getVotes(String uuid) {
		return config.getInt(uuid);
	}

	public static String getUUIDByPunID(String punid) {
		return config.getString(punid);
	}

	public static Boolean votesSubmitted(){
		return config.getBoolean("votesSubmitted");
	}

	public static HashMap<String, Integer> getStateMap() {
		HashMap<String, Integer> uwp = new HashMap<String, Integer>();
		for (String uuid : hasSubmitted) {
			uwp.put(uuid, PuntestManager.getVotes(uuid));
		}

		return sortByValue(uwp);
	}
	
	public static List<String> getRanking(){
		HashMap<String, Integer> uwp = getStateMap();
		List<String> reversePlaces = new ArrayList<String>();
		List<String> places = new ArrayList<String>();
		//System.out.println(uwp.keySet());
		for (String uuid : uwp.keySet()) {
			reversePlaces.add(uuid);
		}
		//System.out.println(reversePlaces);
		int i = 1;
		
		for(int l = 0 ; l < reversePlaces.size(); l++) {
			places.add("k");
		}
		//System.out.println(places);
		for(String uuid : reversePlaces) {
			places.set(reversePlaces.size() - i, uuid);
			i++;
		}
		return places;
	}
	
	public static List<String> getVoted() {
		return configuration.getStringList("hasVoted");
	}

	public static void calculateLeaderboard(List<String> places){
		leadconfig = YamlConfiguration.loadConfiguration(leaderboardfile);
		World w = Bukkit.getWorld("world");
		Location l = new Location(w, 167.0, 122, 237.2);
		WorldManager.spawnText(l, "§b---- §2Leaderboard §b----",  w);
		Boolean set = false;
			for(Integer n = 0; n<3; n++){
				if(getVotes(places.get(0)) != 0 && getVotes(places.get(0)) >= leadconfig.getInt("votes" + n.toString())  && !set){
					switch(n){
						case 0:
							leadconfig.set("2", leadconfig.get("1"));
							leadconfig.set("1", leadconfig.get("0"));
							leadconfig.set("votes2", leadconfig.get("votes1"));
							leadconfig.set("votes1", leadconfig.get("votes0"));
							break;
						case 1:
							leadconfig.set("2", leadconfig.get("1"));
							leadconfig.set("votes2", leadconfig.get("votes1"));
							break;
					}
					leadconfig.set(n.toString(), "§b" + getPun(places.get(0)) + "§2(" + Bukkit.getOfflinePlayer(UUID.fromString(places.get(0))).getName() + ")");
					leadconfig.set("votes" + n.toString(), getVotes(places.get(0)));
					save(leaderboardfile, leadconfig);
					set = true;
				}
			}
		for(Integer h = 0; h<3; h++){
			if(leadconfig.get(h.toString()) != null){
				WorldManager.spawnText(l.subtract(0, (0.3+(h*0.1)), 0), "§6" + (h+1) +  ". §a" + leadconfig.get(h.toString()),  w);
			}
		}
	}

	public static void redrawLeaderboard(){
		Collection<Entity> j = Bukkit.getWorld("world").getNearbyEntities(new Location(Bukkit.getWorld("world"), 165, 118, 236), 2, 6, 2);
		for(Entity e : j){
			if(e.getType() == EntityType.ARMOR_STAND){
				e.remove();
			}
		}
		World w = Bukkit.getWorld("world");
		Location l = new Location(w, 167.0, 122, 237.2);
		WorldManager.spawnText(l, "§b---- §2Leaderboard §b----",  w);
		for(Integer h = 0; h<3; h++){
			if(leadconfig.get(h.toString()) != null){
				WorldManager.spawnText(l.subtract(0, (0.3+(h*0.1)), 0), "§6" + (h+1) +  ". §a" + leadconfig.get(h.toString()),  w);
			}
		}
	}

	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> > () { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }

}