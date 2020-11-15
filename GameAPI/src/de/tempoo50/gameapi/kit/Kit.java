package de.tempoo50.gameapi.kit;

import java.util.HashMap;

import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit {
	
	private HashMap<ItemStack, Integer> Content = new HashMap<>();
	
	public void giveKit(Player player) {
		
		for(ItemStack key : Content.keySet()) {
			
			if(Content.get(key) == 100) {
				
				player.getInventory().setBoots(key);
				
			}else if(Content.get(key) == 101) {
				
				player.getInventory().setLeggings(key);
				
			}else if(Content.get(key) == 102) {
				
				player.getInventory().setChestplate(key);
				
			}else if(Content.get(key) == 103) {
				
				player.getInventory().setHelmet(key);
				
			}else if(Content.get(key) <= 35) {
				
				player.getInventory().setItem(Content.get(key), key);
				
			}else
				Log.error("Falscher Slot " + Content.get(key) + " für Item " + key);
			
		}
		
	}
	
	public void addItem(int slot, ItemStack item) {
		
		Content.put(item, slot);
		
	}

}
