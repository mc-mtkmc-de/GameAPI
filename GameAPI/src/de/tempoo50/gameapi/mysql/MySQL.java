package de.tempoo50.gameapi.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.craftbukkit.libs.jline.internal.Log;

public class MySQL {

	private String host;
	private String user;
	private String passwort;
	private String database;
	
	private int port;
	
	private Connection con = null;
	
	public MySQL(String host, int port, String user, String passwort, String database) {
		
		this.host = host;
		this.port = port;
		this.user = user;
		this.passwort = passwort;
		this.database = database;
		
		openConnection();
		
	}
	
	public void openConnection() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jsbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.passwort);
			this.con = conn;
		
		}catch(SQLException e) {
			
			Log.error(e.getMessage());
			
		}catch(ClassNotFoundException e) {
			
			Log.error(e.getMessage());
			
		}
		
	}
	
	public Connection getConnection() {
	
		return con;
	
	}
	
	public boolean hasConnection() {
		
		try {
			
			return this.con != null || this.con.isValid(1);
			
		}catch(SQLException e) {
			
			return false;
			
		}
		
	}

	public void querryUpdate(String querry) {
		
		Connection conn = this.con;
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement(querry);
			ps.execute();
			
		}catch(SQLException e) {
		
			Log.error(e.getMessage());
			
		}finally {
			
			this.closeRessources(null, ps);
			
		}
		
	}
	
	public void closeRessources(ResultSet rs, PreparedStatement ps) {
		
		if(rs != null) {
			
			try {
				
				rs.close();
				
			}catch(SQLException e) {
				
				Log.error(e.getMessage());
				
			}
			
		}
		
		if(ps != null) {
			
			try {
				
				ps.close();
				
			}catch(SQLException e) {
				
				Log.error(e.getMessage());
				
			}
			
		}
		
	}
	
	public void closeConnection() {
		
		try {
			
			this.con.close();
			
		}catch(SQLException e ) {
			
			Log.error(e.getMessage());
			
		}
		
		this.con = null;
		
	}
	
}
