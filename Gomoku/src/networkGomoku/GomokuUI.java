package networkGomoku;

public class GomokuUI {
	
	private Game game = new Game();
	
	//calls for start of game
	public static void main(String [] args) {
		new GomokuUI().play();
	}
	

	
	public void play() {
		System.out.println("Welcome to Gomoku");
		System.out.println("Please select a mode you would like to play");
		System.out.println("1. Player VS. Player");
		System.out.println("2. Player VS. Computer");
		System.out.println("3. Computer VS. Computer");
		System.out.print("(Input number only) -> ");
		game.mode();
		
		
		while(!game.winConditon()) {
			displayBoard();	
			System.out.print("Enter coordinates for your move (X,Y) -> ");
			game.nextMove();
		}
		
		
	}
	
	public void displayBoard() {
		String boardString = game.getBoard().toString();
		int boardSize = game.getBoard().size() * 3;
		
		System.out.println("X 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14" );
		for(int i = 0; i < boardSize/3; i++) {
			System.out.print(i);
			for(int j = 0; j < boardSize/3; j++) {
				System.out.print(" " + boardString.substring((i * game.getBoard().size()) + j , (i * game.getBoard().size()) + j+1) + " ");
			}
			System.out.println();
		}
	}
}
