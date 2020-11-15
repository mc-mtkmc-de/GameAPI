package de.tempoo50.gameapi.stats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import de.tempoo50.gameapi.mysql.MySQL;

public class Stats {
	
	private MySQL mysql = null;
	
	private Connection con = null;
	
	private String tablename;
	
	public Stats(MySQL mysql, String tablename) {
		
		this.mysql = mysql;
		con = mysql.getConnection();
		this.tablename = tablename;
		
	}
	
	public void setStat(Player player, String stat, String statvalue) {
		
		if(getStat(player, stat) != null) {
			
			mysql.querryUpdate("UPDATE " + tablename + " SET " + stat + "='" + statvalue + "', playername='" + "' WHERE player='" + player.getUniqueId().toString() + "'" );
			
		}else
			
			mysql.querryUpdate("INSERT INTO " + tablename + " (player, playername, " + stat + ") VALUES ('" + player.getUniqueId().toString() + "', '" + player.getName() + "', '" + statvalue + "')");
		
	}
	
	public String getStat(Player player, String stat) {
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		String statvalue = null;
		
		try {
			
			ps = con.prepareStatement("SELECT * FROM " + tablename + " WHERE player=?");
			ps.setString(1, player.getUniqueId().toString());
			rs = ps.executeQuery();
			if(rs.getRow() != 0) {
				
				rs.first();
				statvalue = rs.getString(stat);
				
			}
			
		}catch(SQLException e) {
			
			Log.error(e.getMessage());
			
		}finally {
			
			mysql.closeRessources(rs, ps);
			
		}
		
		return statvalue;
		
	}

}
