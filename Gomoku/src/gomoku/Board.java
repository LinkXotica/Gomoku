package gomoku;

public class Board {
	
	// Input X,Y = [y][x]
	
	//Attributes *****************
	private Place [][] board = new Place[15][15];
	private Player player1;
	private Player player2;
	
	public Board (int size, String playerType1, String playerType2) {
		int [] x = new int[2];
		for(int i = 0; i < size; i ++) {
			for(int j = 0; j < size; j ++) {
				x[0] = i;
				x[1] = j;
				this.board[i][j] = new Place(x);
			}
		}
		if(playerType1 == "Human") {
			this.player1 = new HumanPlayer();
		} else {
			this.player1 = new ComPlayer();
		}
	}
	
	public Board() {
		this(15);
	}
	
	
	
}
