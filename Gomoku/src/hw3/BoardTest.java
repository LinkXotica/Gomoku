package hw3;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hw3.Board.Place;

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
	
	@Test
	void testPlayerAt3() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		
		assertEquals(board.playerAt(1, 2), board.getPlayer2());
		
	}
	
	@Test
	void testClear() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		
		board.clear();
		
		assertEquals(board.playerAt(1, 2), null);
		assertEquals(board.playerAt(1, 1), null);
		
	}
	
	@Test
	void testIsFull1() {
		for(int i = 0; i < board.size(); i ++) {
			for(int j = 0; j < board.size(); j ++) {
				if(i%2 == 0) {
					board.placeStone(i, j, board.getPlayer1());
				} else {
					board.placeStone(i, j, board.getPlayer2());
				}
				
			}
		}
		assertTrue(board.isFull());
	}
	
	@Test
	void testIsFull2() {
		for(int i = 0; i < board.size(); i ++) {
			for(int j = 0; j < board.size(); j ++) {
				if(i%2 == 0) {
					board.placeStone(i, j, board.getPlayer1());
				} else {
					board.placeStone(i, j, board.getPlayer2());
				}
				
			}
		}
		board.placeStone(5, 4, null);
		assertFalse(board.isFull());
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(board.isEmpty(0, 0));
		assertTrue(board.isEmpty(9, 3));
	}

	@Test
	void testIsOccupied() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		
		assertTrue(board.isOccupied(1, 2));
		assertFalse(board.isOccupied(8, 9));

		
	}
	
	@Test
	void testIsOccupiedBy() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		
		assertTrue(board.isOccupiedBy(1, 1, board.getPlayer1()));
		assertFalse(board.isOccupiedBy(1, 2, board.getPlayer1()));
		assertFalse(board.isOccupiedBy(1, 0, board.getPlayer1()));
		
	}
	
	@Test
	void testCheckHorizontal() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		board.placeStone(2, 1, board.getPlayer1());
		board.placeStone(3, 1, board.getPlayer1());
		board.placeStone(4, 1, board.getPlayer1());
		board.placeStone(5, 1, board.getPlayer1());
		board.placeStone(14, 1, board.getPlayer1());
		board.placeStone(13, 1, board.getPlayer1());
		board.placeStone(12, 1, board.getPlayer1());
		board.placeStone(11, 1, board.getPlayer1());
		
		assertTrue(board.checkHorizontal(board.getPlace(1, 1)));
		assertFalse(board.checkHorizontal(board.getPlace(0, 0)));
		assertFalse(board.checkHorizontal(board.getPlace(11, 0)));
	}
	
	@Test
	void testCheckVertical() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		board.placeStone(2, 1, board.getPlayer1());
		board.placeStone(3, 1, board.getPlayer1());
		board.placeStone(4, 1, board.getPlayer1());
		board.placeStone(5, 1, board.getPlayer1());
		board.placeStone(14, 1, board.getPlayer1());
		board.placeStone(13, 1, board.getPlayer1());
		board.placeStone(12, 1, board.getPlayer1());
		board.placeStone(11, 1, board.getPlayer1());
		
		assertTrue(board.checkHorizontal(board.getPlace(1, 1)));
		assertFalse(board.checkHorizontal(board.getPlace(0, 0)));
		assertFalse(board.checkHorizontal(board.getPlace(11, 0)));
	}
	
}
