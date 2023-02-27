package gomoku;

import java.util.Scanner;

public class Controller {
	
	private Scanner input = new Scanner(System.in);
	
	public int[] playerMove(Player player) {
		
		int [] place = new int[2];
		
		if (player.getClass().getSimpleName() == "HumanPlayer") {
		
			String move = input.next();
		
			if(move == null) {
			//??????????????????????????????????????????
			} else {
				place[0] = Integer.parseInt(move.split(",")[0]); 
				place[1] = Integer.parseInt(move.split(",")[1]); 
			
			}
		} else {
			
			place = player.nextMove();
			
		}
		
		return place;
	}
	
	

}
