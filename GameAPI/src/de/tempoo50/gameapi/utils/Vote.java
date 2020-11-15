package de.tempoo50.gameapi.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class Vote {
	
	private ArrayList<Player> votePlayer = new ArrayList<>();
	private HashMap<String, Integer> votes = new HashMap<>();
	
	public void addVote(Player player, String vote, Integer voteValue) {
		
		if(!hasVote(player)) {
			
			votePlayer.add(player);
			if(votes.containsKey(vote)) {
				
				votes.put(vote, votes.get(vote) +voteValue);
				
			}else
				votes.put(vote, voteValue);
			
		}
		
	}
	
	public void removeVote(Player player, String vote, Integer voteValue) {
		
		if(hasVote(player)) {
			
			if(votes.containsKey(vote) && votes.get(vote) >=voteValue) {
				
				votes.put(vote, votes.get(vote) -voteValue);
				
			}
			
		}
		
	}
	
	public int getVote(String vote) {
		
		if(votes.containsKey(vote)) {
			
			return votes.get(vote);
			
		}
		
		return 0;
		
	}
	
	public int getHighestVote() {
		
		int highest = 0;
		
		if(!votes.isEmpty()) {
			
			for(String key : votes.keySet()) {
				
				if(votes.get(key) > highest) {
					
				highest = votes.get(key);	
					
				}
				
			}
			
		}
		
		return highest;
		
	}
	
	public boolean hasVote(Player player) {
		
		if(votePlayer.contains(player)) return true;
		
		return false;
		
	}

}
