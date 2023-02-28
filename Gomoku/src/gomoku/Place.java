package gomoku;

import java.util.Set;

public class Place {
	
	private Set<String> goneDirection;
	private Set<String> cameDirection;
	private int [] place;
	private String stone;
	
	public Place(int [] place, String stone) {
		this.setPlace(place);
		this.setStone(stone);
	}
	
	public Place(int [] place) {
		this(place, " - ");
	}
	
	
	
	//Getters and Setters *****************
	
	public int [] getPlace() {
		return place;
	}

	public void setPlace(int [] place) {
		this.place = place;
	}

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
