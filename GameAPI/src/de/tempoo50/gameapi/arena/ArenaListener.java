package de.tempoo50.gameapi.arena;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import de.tempoo50.gameapi.main.GamesAPI;

public class ArenaListener implements Listener{

	

	
	@EventHandler
	private void canBreak(BlockBreakEvent e) {
		
		Player player = e.getPlayer();
		
		for(Arena arena : GamesAPI.Arenas) {
			
			if(arena.isPlayerIn(player)) {
				
				if(!arena.getCanBreak()) {
					
					e.setCancelled(true);
					
				}
			}
		}
	}
	
	@EventHandler
	private void canPlace(BlockPlaceEvent e) {
		
		Player player = e.getPlayer();
		
		for(Arena arena : GamesAPI.Arenas) {
			
			if(arena.isPlayerIn(player)) {
				
				if(!arena.getCanPlace()) {
					
					e.setCancelled(true);
					
				}
			}
		}
	}
	
	@EventHandler
	private void canInteract(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		
		for(Arena arena : GamesAPI.Arenas) {
			
			if(arena.isPlayerIn(player)) {
				
				if(!arena.getCanInteract()) {
					
					e.setCancelled(true);
					
				}
			}
		}
	}
	
	
}