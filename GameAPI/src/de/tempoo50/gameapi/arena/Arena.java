package de.tempoo50.gameapi.arena;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

import de.tempoo50.gameapi.config.Config;
import de.tempoo50.gameapi.log.Log;
import de.tempoo50.gameapi.main.GamesAPI;

public class Arena {

	private int X1, Y1, Z1;
	private int X2, Y2, Z2;
	
	private String name;
	
	private World world;
	
	private Boolean canBreak = true;
	private Boolean canPlace = true;
	private Boolean canInteract = true;
	
	private HashMap<String, Location> locations = new HashMap<>();
	
	private Location spawn;

	public Arena(Player player, String name) {

		WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

		if (worldEdit == null) {
		
			Log.Error("WorldEdit wurde nicht gefunden");
			
		}

		Selection sel = worldEdit.getSelection(player);

		this.X1 = sel.getMaximumPoint().getBlockX();
		this.Y1 = sel.getMaximumPoint().getBlockY();
		this.Z1 = sel.getMaximumPoint().getBlockZ();

		this.X2 = sel.getMinimumPoint().getBlockX();
		this.Y2 = sel.getMinimumPoint().getBlockY();
		this.Z2 = sel.getMinimumPoint().getBlockZ();

		this.world = sel.getWorld();

		this.name = name;

		GamesAPI.Arenas.add(this);

	}

	public Arena(int X1, int Y1, int Z1, int X2, int Y2, int Z2, World world, String name) {
		
		this.X1 = Math.max(X1, X2);
		this.Y1 = Math.max(Y1, Y2);
		this.Z1 = Math.max(Z1, Z2);

		this.X2 = Math.min(X1, X2);
		this.Y2 = Math.min(Y1, Y2);
		this.Z2 = Math.min(Z1, Z2);

		this.name = name;

		this.world = world;

		GamesAPI.Arenas.add(this);
		
	}

	public boolean isPlayerIn(Player player) {
		
		int pX = player.getLocation().getBlockX();
		int pY = player.getLocation().getBlockY();
		int pZ = player.getLocation().getBlockZ();

		if (pX < X1 && pX > X2 && pY < Y1 && pY > Y2 && pZ < Z1 && pZ > Z2) {
			
			return true;
			
		}

		return false;
	}

	@SuppressWarnings("deprecation")
	public void save() {
		
		Config saveFile = new Config(name, "Minigames-Api", "Arena");
		
		saveFile.delete();
		saveFile.create();

		for (int x = X2; x < X1; x++) {
			
			for (int y = Y2; y < Y1; y++) {
				
				for (int z = Z2; z < Z1; z++) {
					
					Block block = world.getBlockAt(x, y, z);

					saveFile.getConfig().set("Blocks." + x + "," + y + "," + z,
							block.getType().toString() + ":" + block.getData());

				}
			}
		}
		
		saveFile.getConfig().options().copyDefaults(true);

		saveFile.save();

	}

	@SuppressWarnings("deprecation")
	public void save(String Filename) {
		
		Config saveFile = new Config(Filename, "Minigames-Api", "Arena");
		
		saveFile.delete();
		saveFile.create();

		for (int x = X2; x < X1; x++) {
			
			for (int y = Y2; y < Y1; y++) {
				
				for (int z = Z2; z < Z1; z++) {
					
					Block block = world.getBlockAt(x, y, z);

					saveFile.getConfig().set("Blocks." + x + "," + y + "," + z,
							block.getType().toString() + ":" + block.getData());

				}
			}
		}
		
		saveFile.getConfig().options().copyDefaults(true);

		saveFile.save();

	}

	@SuppressWarnings("deprecation")
	public void load(String filename) {
		
		Config saveFile = new Config(filename, "Minigames-Api", "Arena");
		ConfigurationSection privateSection = saveFile.getConfig().getConfigurationSection("Blocks");
		
		for (String location : privateSection.getKeys(false)) {
			
			String[] cords = location.split(",");
			int X = Integer.valueOf(cords[0]);
			int Y = Integer.valueOf(cords[1]);
			int Z = Integer.valueOf(cords[2]);
			String[] Blockdata = saveFile.getConfig().getString("Blocks." + location).split(":");
			String material = Blockdata[0];
			byte data = Byte.valueOf(Blockdata[1]);

			world.getBlockAt(X, Y, Z).setType(Material.getMaterial(material));
			world.getBlockAt(X, Y, Z).setData(data);

		}
	}

	@SuppressWarnings("deprecation")
	public void reset() {
		
		Config saveFile = new Config(name, "Minigames-Api", "Arena");
		ConfigurationSection blockSection = saveFile.getConfig().getConfigurationSection("Blocks");
		
		for (String location : blockSection.getKeys(false)) {
			
			String[] cords = location.split(",");
			int X = Integer.valueOf(cords[0]);
			int Y = Integer.valueOf(cords[1]);
			int Z = Integer.valueOf(cords[2]);
			String[] Blockdata = saveFile.getConfig().getString("Blocks." + location).split(":");
			String material = Blockdata[0];
			byte data = Byte.valueOf(Blockdata[1]);

			world.getBlockAt(X, Y, Z).setType(Material.getMaterial(material));
			world.getBlockAt(X, Y, Z).setData(data);

		}

	}

	public void setCanBreak(boolean canBreak) {
	
		this.canBreak = canBreak;
	
	}

	public Boolean getCanBreak() {
	
		return canBreak;
	
	}

	public void setCanPlace(boolean canPlace) {

		this.canPlace = canPlace;
	
	}

	public Boolean getCanPlace() {
	
		return canPlace;
	
	}

	public void setCanInteract(boolean canInteract) {
	
		this.canInteract = canInteract;
	
	}

	public Boolean getCanInteract() {
	
		return canInteract;
	
	}

	public int getX1() {
	
		return X1;
	
	}

	public void setX1(int x1) {
	
		X1 = x1;
	
	}

	public int getY1() {
	
		return Y1;
	
	}

	public void setY1(int y1) {
	
		Y1 = y1;
	
	}

	public int getZ1() {
	
		return Z1;
	
	}

	public void setZ1(int z1) {
	
		Z1 = z1;
	
	}

	public int getX2() {
	
		return X2;
	
	}

	public void setX2(int x2) {
	
		X2 = x2;
	
	}

	public int getY2() {

		return Y2;

	}

	public void setY2(int y2) {

		Y2 = y2;

	}

	public int getZ2() {

		return Z2;

	}

	public void setZ2(int z2) {

		Z2 = z2;

	}

	public String getName() {
	
		return name;
	
	}

	public void setName(String name) {
	
		this.name = name;
	
	}

	public World getWorld() {
	
		return world;
	
	}

	public void setWorld(World world) {
	
		this.world = world;
	
	}

	public Location getSpawn() {
	
		return spawn;
	
	}

	public void setSpawn(Location spawn) {
	
		this.spawn = spawn;
	
	}

	public Location getLocation(String name) {
	
		return locations.get(name);
	
	}

	public void setLocation(String name, Location location) {
	
		locations.put(name, location);
	
	}

	public HashMap<String, Location> getLocations() {
		
		return locations;
	
	}

}
