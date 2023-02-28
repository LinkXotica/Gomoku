package gomoku;

import java.util.Set;

public class Game {
	
	private Board board;
	private Controller controller = new Controller();
	private Player player1;
	private Player player2;	
	private Set<String> directions;
	

	
	public Game(int size) {
		this.setBoard(new Board(size));
	}
	
	public Game() {
		this(15);
	}
	
	//Game
	public int[] nextMove() {
		
		int [] place = new int[2];
		
		if(player1.isTurn()) {
			place = controller.playerMove(player1);
			getBoard().setPlace(place, " X ");
			this.switchTurn();
		} else if(player2.isTurn()) {
			place = controller.playerMove(player2);
			getBoard().setPlace(place, " O ");
			this.switchTurn();
		}
		
		
		
		return place;
		
		
	}
	//mode
	public int mode() {
		
		int mode = controller.readMode();
		
		String playerType1 = null;
		String playerType2 = null;
		
		
		if(mode == 1) {
			playerType1 = "Human";
			playerType2 = "Human";
		} else if(mode == 2) {
			playerType1 = "Human";
			playerType2 = "Computer";
		} else if (mode == 3) {
			playerType1 = "Computer";
			playerType2 = "Computer";
		} else {
			//??????????????????????????????????
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
		
		
		
		
		return mode;
		
		
	}
	//turn?
	public void switchTurn() {
		player1.changeTurn();
		player2.changeTurn();
	}
	//wincondition
	public boolean winConditon() {
		return false;
	}
	//Controller Call
	
	

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
