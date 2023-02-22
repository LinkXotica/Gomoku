package gomoku;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	private Scanner input = new Scanner(System.in);
	
	public HumanPlayer(Boolean turn) {
		super(turn);
	}
	
	
	@Override
	public int[] nextMove() {
		String move = input.next();
		int [] place = new int[2];
		if(move == null) {
			//??????????????????????????????????????????
		} else {
			place[0] = Integer.parseInt(move.split(",")[0]); 
			place[1] = Integer.parseInt(move.split(",")[1]); 
			
		}
		
		return place;
	}
	
	public void changeTurn() {
		setTurn(!isTurn());
	}

}
