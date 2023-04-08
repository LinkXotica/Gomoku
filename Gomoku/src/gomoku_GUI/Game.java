package gomoku_GUI;

public class Game {
	
	private Board board;
	private Controller controller = new Controller();
	private boolean p1Turn = true;

	

	
	public Game(int size) {
		this.setBoard(new Board(size));
	}
	
	public Game() {
		this(15);
	}
	
	//Game
	public int[] nextMove() {
		
		int [] place = null;
		boolean again = true;
		
		if(p1Turn) {
			while(place == null || again)  {
				place = controller.playerMove(board.getPlayer1());
				again = board.isOccupied(place[0],place[1]);
				if(!again) {
					board.placeStone(place[0],place[1], board.getPlayer1());
					this.switchTurn();
				} 
			}
			
		} else  {
			while(place == null || again)  {
				place = controller.playerMove(board.getPlayer2());
				again = board.isOccupied(place[0],place[1]);
				if(!again) {
					board.placeStone(place[0],place[1], board.getPlayer2());
					this.switchTurn();
				} 
			}
		}
		
		
		
		return place;
		
		
	}
	
	//mode
	public int mode() {
		
		int mode = controller.readMode();
		
		if(mode == 1) {
			board.setPlayerType(1, 1);
		} else if (mode == 2) {
			board.setPlayerType(1, 2);
		} else {
			board.setPlayerType(2, 2);
		}
		
		return mode;
		
	}
	//turn?
	public void switchTurn() {
		p1Turn = !p1Turn;
	}
	//wincondition
	public boolean winConditon() {
		
		if(board.winningRow() != null) {
			return true;
		}
		return false;
		
	}
	
	public Player wonBy() {
		
		if(board.isWonBy(board.getPlayer1())) {
			return board.getPlayer1();
		} 
		return board.getPlayer2();
		
	}
	//Controller Call
	
	

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
