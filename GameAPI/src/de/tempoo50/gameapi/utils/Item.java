package de.tempoo50.gameapi.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	
	private ItemStack current;
	
	public Item(ItemStack i) {
		
		this.current = i;
		
	}
	

	public static ItemStack ItemName(Material material, int count, short damage, String name) {
		
		ItemStack items = new ItemStack(material, count, damage);
		ItemMeta itemMeta = items.getItemMeta();
		itemMeta.setDisplayName(name);
		items.setItemMeta(itemMeta);
		
		return items;
		
	}
	
	public static ItemStack ItemName(Material material, int count, String name) {
		
		ItemStack items = new ItemStack(material, count);
		ItemMeta itemMeta = items.getItemMeta();		
		itemMeta.setDisplayName(name);		
		items.setItemMeta(itemMeta);
		
		return items;
		
	}
	
	public static ItemStack ItemEnchant(Material material, int count, short damage, Enchantment[] enchantment, int[] level) {
		
		ItemStack items = new ItemStack(material, count, damage);
		
		for(int i=1; i< enchantment.length; i++) {
			
			items.addEnchantment(enchantment[i], level[i]);
			
		}
		
		return items;
		
	}
	
	public static ItemStack ItemEnchant(Material material, int count, Enchantment[] enchantment, int[] level) {
		
		ItemStack items = new ItemStack(material, count);
		
		for(int i=1; i< enchantment.length; i++) {
			
			items.addEnchantment(enchantment[i], level[i]);
		
		}
		
		return items;
	}
	
	public static ItemStack ItemLore(Material material, int count, short damage, String[] lore) {
		
		ItemStack items = new ItemStack(material, count, damage);
		ItemMeta itemMeta = items.getItemMeta();
		
		ArrayList<String> Lore = new ArrayList<String>();
		
		for(String string : lore) {
			
			Lore.add(string);
			
		}
		
		itemMeta.setLore(Lore);		
		items.setItemMeta(itemMeta);
		
		return items;
		
	}
	
	public static ItemStack ItemLore(Material material, int count, String[] lore) {
		
		ItemStack items = new ItemStack(material, count);
		ItemMeta itemMeta = items.getItemMeta();
		
		ArrayList<String> Lore = new ArrayList<String>();
		
		for(String string : lore) {
			
			Lore.add(string);
		
		}
		
		itemMeta.setLore(Lore);		
		items.setItemMeta(itemMeta);
		
		return items;
	
	}
	

	
	public Item(Material m, int amount, short ID) {
		
		this(new ItemStack(m, amount, ID));
		
	}
	
	public Item(Material m, int amount) {
		
		this(new ItemStack(m, amount));
	}
	
	public Item(int ID, int amount, short doubleID) {
		
		this(new ItemStack(ID, amount, doubleID));
		
	}
	
	public Item setDisplayname(String displayname) {
		
		ItemMeta m = current.getItemMeta();
		m.setDisplayName(displayname);
		current.setItemMeta(m);
		return this;
		
	}
	
	public Item setLore(List<String> lore) {
		
		ItemMeta m = current.getItemMeta();
		m.setLore(lore);
		current.setItemMeta(m);
		return this;
		
	}
	
	public Item addEnchantment(Enchantment ench, int level, boolean bool) {
		
		ItemMeta m = current.getItemMeta();
		m.addEnchant(ench, level, bool);
		current.setItemMeta(m);
		return this;
		
	}
	
	public Item addUnsafeEnchantment(Enchantment ench, int level) {
		
		current.addUnsafeEnchantment(ench, level);
		return this;
		
	}
	
	public Item setAmount(int am) {
		
		current.setAmount(am);
		return this;
		
	}
	
	public ItemStack build() {
		
		return this.current;
		
	}

}
