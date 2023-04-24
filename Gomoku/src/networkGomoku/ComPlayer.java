package networkGomoku;

public class ComPlayer extends Player {

	protected ComPlayer(String name) {
		super(name);
	}
	
	public int[] nextMove() {
		int[] place = {0,0};
		
		return place;
	}
	
	public int playerType() {
		return 2;
	}

}
