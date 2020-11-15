package de.tempoo50.gameapi.team;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import de.tempoo50.gameapi.main.GamesAPI;

public class TeamListener implements Listener {
	
	@EventHandler
	public void friendlyFire(EntityDamageByEntityEvent event) {
		
		if(event.getEntity() instanceof Player) {
			
			Player geter = (Player) event.getEntity();
			Player seter = (Player) event.getDamager();
			
			for(Team team : GamesAPI.Teams) {
				
				if(team.containsPlayer(geter) && team.containsPlayer(seter)) {
					
					if(team.getFriendlyFire()) {
						
						event.setCancelled(true);
						
					}
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void InteractBlock(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		for(Team team : GamesAPI.Teams) {
			
			if(team.containsPlayer(player)) {
				
				if(!team.getCanInteract()) {
				
					event.setCancelled(true);
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void allowInteractEntity(PlayerInteractAtEntityEvent event) {
		
		Player player = event.getPlayer();
		
		for(Team team : GamesAPI.Teams) {
			
			if(team.containsPlayer(player)) {
				
				if(!team.getCanInteract()) {
					
					event.setCancelled(true);
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void allowDamage(EntityDamageByEntityEvent event) {
		
		if(event.getDamager() instanceof Player) {
			
			Player player = (Player) event.getDamager();
			
			for(Team team : GamesAPI.Teams) {
				
				if(team.containsPlayer(player)) {
					
					if(!team.getCanInteract()) {
						
						event.setCancelled(true);
						
					}
					
				}
				
			}
			
		}
		
	}
	
	private void canBreak(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		
		for(Team team : GamesAPI.Teams) {
			
			if(team.containsPlayer(player)) {
				
				if(!team.getCanBreak()) {
					
					event.setCancelled(true);
					
				}
			}
			
		}
		
	}
	
	@EventHandler
	private void canPlace(BlockPlaceEvent event) {
		
		Player player = event.getPlayer();
		
		for(Team team : GamesAPI.Teams) {
			
			if(team.containsPlayer(player)) {
				
				if(!team.getCanPlace()) {
					
					event.setCancelled(true);
					
				}
				
			}
			                                                           
		}
		
	}

}
