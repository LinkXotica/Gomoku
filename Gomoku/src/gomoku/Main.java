package gomoku;

public class Main {
	public static void main(String [] args) {
		
		test();
		
	}
	
	
	public static void test()  {
		Player player = new HumanPlayer(false);
		int num = 0;
		
		System.out.print(player.getClass().getSimpleName());
	}
}
