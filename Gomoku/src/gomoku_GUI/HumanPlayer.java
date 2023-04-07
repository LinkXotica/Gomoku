package gomoku_GUI;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	private Scanner input = new Scanner(System.in);
	
	public HumanPlayer(String name) {
		super(name);
	}
	
	
	
	public int playerType() {
		return 1;
	}

	protected int[] nextMove() {
		
		int [] place = new int[2];
		
		String move = input.nextLine();
		
		if(move == null) {
		//??????????????????????????????????????????
		} else {
			place[0] = Integer.parseInt(move.split(",")[1]); 
			place[1] = Integer.parseInt(move.split(",")[0]); 
			
		}
		
		return place;
	}

}
