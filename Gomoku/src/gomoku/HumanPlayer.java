package gomoku;

public class HumanPlayer extends Player {
	
	public HumanPlayer(Boolean turn) {
		super(turn);
	}
	
	public void changeTurn() {
		setTurn(!isTurn());
	}

}
