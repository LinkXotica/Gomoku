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
		
		return player.nextMove();
	}	
	

}
