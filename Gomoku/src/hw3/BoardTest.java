package hw3;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
	
	private Board board;
	
	@BeforeEach
	public void setUp() {
		System.out.println("Setting up board of 15");
		board = new Board(15);
	}
	
	@AfterEach
	public void tearDown() {
		System.out.println("Running: tearDown");
	    board = null;
	    assertNull(board);
	}
	
	@Test
	void testSize() {
		assertEquals(board.size(), 15);
	}

	@Test
	void testPlayerAt1() {
		board.placeStone(1, 1, board.getPlayer1());
		assertEquals(board.playerAt(1, 1), board.getPlayer1());
		
	}
	
	@Test
	void testPlayerAt2() {
		board.placeStone(1, 1, board.getPlayer1());
		assertEquals(board.playerAt(0, 0), null);
		
	}

}
