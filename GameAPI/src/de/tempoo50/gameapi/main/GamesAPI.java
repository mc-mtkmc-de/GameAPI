package de.tempoo50.gameapi.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.tempoo50.gameapi.arena.Arena;
import de.tempoo50.gameapi.arena.ArenaListener;
import de.tempoo50.gameapi.countdown.CountDownEvent;
import de.tempoo50.gameapi.team.Team;
import de.tempoo50.gameapi.team.TeamListener;

public class GamesAPI extends JavaPlugin {

	public static GamesAPI plugin;
	
	public static ArrayList<Team> Teams = new ArrayList<Team>();
	public static ArrayList<Arena> Arenas = new ArrayList<Arena>();
	
	public static CountDownEvent coundown;
	
	public void onEnable() {
		
		plugin = this;
		
		coundown = new CountDownEvent();
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new TeamListener(), this);
		pm.registerEvents(new ArenaListener(), this);
		
	}
	
	public void onDisable() {
		
		Bukkit.getConsoleSender().sendMessage("API wurde beendet");
		
	}
	
	public static GamesAPI getPlugin() {
		
		return plugin;
		
	}
	
}
