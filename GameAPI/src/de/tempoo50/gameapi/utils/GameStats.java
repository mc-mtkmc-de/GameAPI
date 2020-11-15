package de.tempoo50.gameapi.utils;

public enum GameStats {
	
	WAITING("Waiting"), SATRING("Starting"), INGAME("Ingame"), ENDING("Ending");
	
	private String GameStatName;
	
	private GameStats(String GameStatName) {
		
		this.GameStatName = GameStatName;
		
	}
	
	public String getGameStatName() {
	
		return GameStatName;
	
	}

}
