package gomoku;

import java.util.Scanner;

public class Main {
	public static void main(String [] args) {
		
		test();
		
	}
	
	
	public static void test()  {
		Scanner input = new Scanner(System.in);
		String move = input.nextLine();
		int [] place = new int[2];
		
		place[0] = Integer.parseInt(move.split(",")[0]); 
		place[1] = Integer.parseInt(move.split(",")[1]); 
		System.out.println(move.split(",")[0]);
		System.out.println(move.split(",")[1]);
		System.out.print(place[0]);
		System.out.print(place[1]);
	}
}
