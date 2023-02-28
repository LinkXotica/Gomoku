package gomoku;

import java.util.Scanner;

public class Controller {
	
	private Scanner input = new Scanner(System.in);
	
	public Controller() {
		
	}
	
	public int readMode() {
		int mode = input.nextInt();
		return mode;
	}
	
	public int[] playerMove(Player player) {
		
		input.next();
		
		int [] place = new int[2];
		
		if (player.getClass().getSimpleName() == "HumanPlayer") {
		
			String move = input.nextLine();
		
			if(move == null) {
			//??????????????????????????????????????????
			} else {
				place[0] = Integer.parseInt(move.split(",")[0]); 
				place[1] = Integer.parseInt(move.split(",")[1]); 
				
			}
		} else {
			
			place = player.nextMove();
			
		}
		
		System.out.print(place[0]);
		System.out.print(place[1]);
		
		return place;
	}	
	

}
