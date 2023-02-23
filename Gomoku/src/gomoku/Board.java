package gomoku;

public class Board {
	
	// Input X,Y = [y][x]
	
	//Attributes *****************
	private Place [][] board;
	private Player player1;
	private Player player2;
	
	public Board (int size, String playerType1, String playerType2) {
		int [] x = new int[2];
		board = new Place[size][size];
		for(int i = 0; i < size; i ++) {
			for(int j = 0; j < size; j ++) {
				x[0] = i;
				x[1] = j;
				this.board[i][j] = new Place(x);
			}
		}
		if(playerType1 == "Human") {
			this.player1 = new HumanPlayer(true);
		} else {
			this.player1 = new ComPlayer(true);
		}
		
		if(playerType2 == "Human") {
			this.player2 = new HumanPlayer(false);
		} else {
			this.player2 = new ComPlayer(false);
		}
	}
	
	public void switchTurn() {
		player1.changeTurn();
		player2.changeTurn();
	}
	
	public Board(String playerType1, String playerType2) {
		this(15, playerType1, playerType2);
	}
	
	
	public String toString() {
		String str = null;
		for(int i = 0; i < board.length; i ++) {
			for(int j = 0; j < board[i].length; j ++) {
				str = str + board[i][j].getStone();
			}
		}
		return str;
		
	}
	
	
}
