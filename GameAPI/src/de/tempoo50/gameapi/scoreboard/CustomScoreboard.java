package de.tempoo50.gameapi.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import de.tempoo50.gameapi.team.Team;

public class CustomScoreboard {
	
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard score =    manager.getNewScoreboard();
	
	Objective obj = null;
	
	public CustomScoreboard(String name, DisplaySlot display) {
		
		obj = score.registerNewObjective(name, "dummy");
		obj.setDisplayName(name);
		obj.setDisplaySlot(display);
		
	}
	
	public void setScore(String name, Integer sc) {
		
		Score sco = obj.getScore(name);
		sco.setScore(sc);
		
	}
	
	public int getScore(String name) {
		
		Score sco = obj.getScore(name);
		
		return sco.getScore();
		
	}
	
	public void setScoreboard(Player player) {
		
		player.setScoreboard(score);
		
	}
	
	public void setScoreboard(Team team) {
		
		for(Player player : team.getPlayers()) {
			
			player.setScoreboard(score);
			
		}
		
	}

}
