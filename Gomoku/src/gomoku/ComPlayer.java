package gomoku;

public class ComPlayer extends Player {

	protected ComPlayer(Boolean turn) {
		super(turn);
	}
	
	public int[] nextMove() {
		int[] place = {0,0};
		
		return place;
	}
	
	public String playerType() {
		return "Com";
	}

}
