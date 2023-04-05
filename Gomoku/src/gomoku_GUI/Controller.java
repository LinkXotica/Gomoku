package gomoku_GUI;

import java.util.Scanner;

public class Controller {
	
	private Scanner input = new Scanner(System.in);
	
	public Controller() {
		
	}
	
	public int readMode() {
		int mode = input.nextInt();
		input.nextLine();
		return mode;
	}
	
	public int[] playerMove(Player player) {
		
		
		
		
		int [] place = new int[2];
		
		if (player.playerType() == "Human") {
			
			
			String move = input.nextLine();
		
			if(move == null) {
			//??????????????????????????????????????????
			} else {
				place[0] = Integer.parseInt(move.split(",")[1]); 
				place[1] = Integer.parseInt(move.split(",")[0]); 
				
			}
		} else {
			
			place = player.nextMove();
			
		}
		
		
		
		return place;
	}	
	

}
