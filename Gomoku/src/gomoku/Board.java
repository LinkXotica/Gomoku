package gomoku;

public class Board {
	
	// Input X,Y = [y][x]
	
	//Attributes *****************
	private Place [][] board;

	
	public Board (int size) {
		board = new Place[size][size];
		for(int i = 0; i < size; i ++) {
			for(int j = 0; j < size; j ++) {
				this.board[i][j] = new Place();
			}
		}
		
	}
	
	public void setPlace(int [] place, String playerStone) {
		board[place[0]][place[1]].setStone(playerStone);
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < board.length; i ++) {
			for(int j = 0; j < board[i].length; j ++) {
				str = str + board[i][j].getStone();
			}
		}
		return str;
		
	}

	public int length() {
		return board.length;
	}
	
	public Place getPlace(int [] place) {
		return board[place[0]][place[1]];
	}
	
	public Place[][] getBoard() {
		return board;
	}
	
}
