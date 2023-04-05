package gomoku_GUI;

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
		
		int [] place = null;
		boolean again = true;
		
		if(player1.isTurn()) {
			while(place == null || again)  {
				place = controller.playerMove(player1);
				again = this.isOccupied(place);
				if(!again) {
					getBoard().setPlace(place, " X ");
					this.switchTurn();
				} 
			}
			
		} else if(player2.isTurn()) {
			place = controller.playerMove(player2);
			getBoard().setPlace(place, " O ");
			this.switchTurn();
		}
		
		
		
		return place;
		
		
	}
	
	public boolean isOccupied(int[] place) {
		if(board.getPlace(place).getStone() == " - ") {
			return false;
		} else {
			return true;
		}
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
	public boolean winConditonp1() {
		boolean win = true;
		
		
		
		int [] place = new int[2];
		for(int i = 0; i < board.length(); i++) {
			for( int j = 0; j < board.length(); j++) {
				place[0] = i;
				place[1] = j;
				if (!this.isOccupied(place) && board.getPlace(place).getStone() == " X ") {
					this.checkAroundP1(place);
					if(directions.contains("N")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]--;
							if(board.getPlace(place).getStone() != " X ") {
								win = false;
							}
						}
					}
					if(directions.contains("NE")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]--;
							place[0]++;
							if(board.getPlace(place).getStone() != " X ") {
								win = false;
							}
						}
					}
					if(directions.contains("NW")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]--;
							place[0]--;
							if(board.getPlace(place).getStone() != " X ") {
								win = false;
							}
						}
					}
					if(directions.contains("W")){
						for(int ii = 0; ii < 5; ii++) {
							place[0]--;
							if(board.getPlace(place).getStone() != " X ") {
								win = false;
							}
						}
					}
					if(directions.contains("S")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]++;
							if(board.getPlace(place).getStone() != " X ") {
								win = false;
							}
						}
					}
					if(directions.contains("SE")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]++;
							place[0]++;
							if(board.getPlace(place).getStone() != " X ") {
								win = false;
							}
						}
					}
					if(directions.contains("SW")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]++;
							place[0]--;
							if(board.getPlace(place).getStone() != " X ") {
								win = false;
							}
						}
					}
					if(directions.contains("E")){
						for(int ii = 0; ii < 5; ii++) {
							place[0]++;
							if(board.getPlace(place).getStone() != " X ") {
								win = false;
							}
						}
					}
				}
			}
		}
		
		
		return win;
	}
	
	public void checkAroundP1(int [] place) {
		int [] tempPlace = place;
		
		directions.add("N");
		directions.add("NE");
		directions.add("E");
		directions.add("SE");
		directions.add("S");
		directions.add("SW");
		directions.add("W");
		directions.add("NW");
		
		if(place[0] == 0) {
			directions.remove("W");
		} else {
			tempPlace[0] = tempPlace[0]-1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " O ") {
				directions.remove("W");
			}
		}
		if(place[1] == 0) {
			directions.remove("N");
		} else {
			tempPlace[1] = tempPlace[1]-1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " O ") {
				directions.remove("N");
			}
		}
		if(place[1] == 0 && place[0] == 0) {
			directions.remove("NW");
		}else {
			tempPlace[0] = tempPlace[0]-1;
			tempPlace[1] = tempPlace[1]-1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " O ") {
				directions.remove("NW");
			}
		}
		if(place[1] == board.length() - 1) {
			directions.remove("S");
		}else {
			tempPlace[1] = tempPlace[1]+1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " O ") {
				directions.remove("S");
			}
		}
		if(place[1] == board.length() - 1 && place[0] == 0) {
			directions.remove("SW");
		}else {
			tempPlace[1] = tempPlace[1]+1;
			tempPlace[0] = tempPlace[0]-1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " O ") {
				directions.remove("SW");
			}
		}
		if(place[0] == board.length() - 1) {
			directions.remove("E");
		} else {
			tempPlace[0] = tempPlace[0]+1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " O ") {
				directions.remove("E");
			}
		}
		if(place[0] == board.length() - 1 && place[1] == board.length() - 1) {
			directions.remove("SE");
		} else {
			tempPlace[0] = tempPlace[0]+1;
			tempPlace[1] = tempPlace[1]+1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " O ") {
				directions.remove("SE");
			}
		}
		if(place[1] == 0 && place[0] == board.length() - 1) {
			directions.remove("NE");
		}else {
			tempPlace[1] = tempPlace[1]-1;
			tempPlace[0] = tempPlace[0]+1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " O ") {
				directions.remove("NE");
			}
		}
		
		
		

		
		
	}
	public boolean winConditonp2() {
		boolean win = true;
		
		
		
		int [] place = new int[2];
		for(int i = 0; i < board.length(); i++) {
			for( int j = 0; j < board.length(); j++) {
				place[0] = i;
				place[1] = j;
				if (!this.isOccupied(place) && board.getPlace(place).getStone() == " X ") {
					this.checkAroundP1(place);
					if(directions.contains("N")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]--;
							if(board.getPlace(place).getStone() != " O ") {
								win = false;
							}
						}
					}
					if(directions.contains("NE")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]--;
							place[0]++;
							if(board.getPlace(place).getStone() != " O ") {
								win = false;
							}
						}
					}
					if(directions.contains("NW")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]--;
							place[0]--;
							if(board.getPlace(place).getStone() != " O ") {
								win = false;
							}
						}
					}
					if(directions.contains("W")){
						for(int ii = 0; ii < 5; ii++) {
							place[0]--;
							if(board.getPlace(place).getStone() != " O ") {
								win = false;
							}
						}
					}
					if(directions.contains("S")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]++;
							if(board.getPlace(place).getStone() != " O ") {
								win = false;
							}
						}
					}
					if(directions.contains("SE")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]++;
							place[0]++;
							if(board.getPlace(place).getStone() != " O ") {
								win = false;
							}
						}
					}
					if(directions.contains("SW")){
						for(int ii = 0; ii < 5; ii++) {
							place[1]++;
							place[0]--;
							if(board.getPlace(place).getStone() != " O ") {
								win = false;
							}
						}
					}
					if(directions.contains("E")){
						for(int ii = 0; ii < 5; ii++) {
							place[0]++;
							if(board.getPlace(place).getStone() != " O ") {
								win = false;
							}
						}
					}
				}
			}
		}
		
		
		return win;
	}
	
	public void checkAroundP2(int [] place) {
		int [] tempPlace = place;
		
		directions.add("N");
		directions.add("NE");
		directions.add("E");
		directions.add("SE");
		directions.add("S");
		directions.add("SW");
		directions.add("W");
		directions.add("NW");
		
		if(place[0] == 0) {
			directions.remove("W");
		} else {
			tempPlace[0] = tempPlace[0]-1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " X ") {
				directions.remove("W");
			}
		}
		if(place[1] == 0) {
			directions.remove("N");
		} else {
			tempPlace[1] = tempPlace[1]-1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " X ") {
				directions.remove("N");
			}
		}
		if(place[1] == 0 && place[0] == 0) {
			directions.remove("NW");
		}else {
			tempPlace[0] = tempPlace[0]-1;
			tempPlace[1] = tempPlace[1]-1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " X ") {
				directions.remove("NW");
			}
		}
		if(place[1] == board.length() - 1) {
			directions.remove("S");
		}else {
			tempPlace[1] = tempPlace[1]+1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " X ") {
				directions.remove("S");
			}
		}
		if(place[1] == board.length() - 1 && place[0] == 0) {
			directions.remove("SW");
		}else {
			tempPlace[1] = tempPlace[1]+1;
			tempPlace[0] = tempPlace[0]-1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " X ") {
				directions.remove("SW");
			}
		}
		if(place[0] == board.length() - 1) {
			directions.remove("E");
		} else {
			tempPlace[0] = tempPlace[0]+1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " X ") {
				directions.remove("E");
			}
		}
		if(place[0] == board.length() - 1 && place[1] == board.length() - 1) {
			directions.remove("SE");
		} else {
			tempPlace[0] = tempPlace[0]+1;
			tempPlace[1] = tempPlace[1]+1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " X ") {
				directions.remove("SE");
			}
		}
		if(place[1] == 0 && place[0] == board.length() - 1) {
			directions.remove("NE");
		}else {
			tempPlace[1] = tempPlace[1]-1;
			tempPlace[0] = tempPlace[0]+1;
			if(board.getPlace(place).getStone() == " - " || board.getPlace(place).getStone() == " X ") {
				directions.remove("NE");
			}
		}
		
		
		

		
		
	}
	//Controller Call
	
	

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
