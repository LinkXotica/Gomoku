package gomoku;

public abstract class Player {
	
	private boolean turn = false;
	
	protected Player(Boolean turn) {
		this.setTurn(turn);
	}
	
	protected int[] nextMove() {
		return null;
	}
	
	protected void changeTurn() {
		setTurn(!isTurn());
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

}
