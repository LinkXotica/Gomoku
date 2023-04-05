package gomoku_GUI;

import java.util.Set;

public class Place {
	
	private Set<String> goneDirection;
	private Set<String> cameDirection;
	private String stone;
	
	public Place(String stone) {
		this.setStone(stone);
	}
	
	public Place() {
		this(" - ");
	}
	
	//Getters and Setters *****************

	public String getStone() {
		return stone;
	}

	public void setStone(String stone) {
		this.stone = stone;
	}

	public Set<String> getCameDirection() {
		return cameDirection;
	}

	public Set<String> getGoneDirection() {
		return goneDirection;
	}
	
	// Add and Clear Direction Set *****************

	public void addGoneDirection(String d) {
		goneDirection.add(d);
	}
	
	public void addCameDirection(String d) {
		cameDirection.add(d);
	}
	
	public void clearCameDirection() {
		cameDirection.clear();
	}
	
	public void clearGoneDirection() {
		goneDirection.clear();
	}
	

}
