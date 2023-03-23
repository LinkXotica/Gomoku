package hw3;


import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hw3.Board.Place;

class BoardTest {
	
	private Board board;
	
	@BeforeEach
	public void setUp() {
		board = new Board(15);
	}
	
	@AfterEach
	public void tearDown() {
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
		assertFalse(board.checkHorizontal(board.getPlace(1, 2)));
		assertFalse(board.checkHorizontal(board.getPlace(11, 0)));
	}
	
	@Test
	void testCheckVertical() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		board.placeStone(1, 3, board.getPlayer1());
		board.placeStone(1, 4, board.getPlayer1());
		board.placeStone(1, 5, board.getPlayer1());
		board.placeStone(1, 6, board.getPlayer1());
		board.placeStone(1, 7, board.getPlayer1());

		assertTrue(board.checkVertical(board.getPlace(1, 3)));
		assertFalse(board.checkVertical(board.getPlace(1, 1)));
		assertFalse(board.checkVertical(board.getPlace(0, 0)));
		assertFalse(board.checkVertical(board.getPlace(11, 0)));
	}
	
	@Test
	void testCheckDiagonal() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		board.placeStone(2, 2, board.getPlayer1());
		board.placeStone(3, 3, board.getPlayer1());
		board.placeStone(4, 4, board.getPlayer1());
		board.placeStone(5, 5, board.getPlayer1());
		board.placeStone(6, 6, board.getPlayer1());
		board.placeStone(7, 7, board.getPlayer1());

		assertTrue(board.checkDiagonal(board.getPlace(1, 1)));
		assertFalse(board.checkDiagonal(board.getPlace(1, 2)));
		assertFalse(board.checkDiagonal(board.getPlace(0, 0)));
		assertFalse(board.checkDiagonal(board.getPlace(11, 0)));
	}
	
	@Test
	void testWinningRow1() {
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
		
		List<Place> line = new LinkedList<Place>();
		line.add(board.getPlace(1, 1));
		line.add(board.getPlace(2, 1));
		line.add(board.getPlace(3, 1));
		line.add(board.getPlace(4, 1));
		line.add(board.getPlace(5, 1));
		
		assertEquals(board.winningRow(), line);
	}
	
	@Test
	void testWinningRow2() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		board.placeStone(1, 3, board.getPlayer1());
		board.placeStone(1, 4, board.getPlayer1());
		board.placeStone(1, 5, board.getPlayer1());
		board.placeStone(1, 6, board.getPlayer1());
		board.placeStone(1, 7, board.getPlayer1());

		List<Place> line = new LinkedList<Place>();
		line.add(board.getPlace(1, 3));
		line.add(board.getPlace(1, 4));
		line.add(board.getPlace(1, 5));
		line.add(board.getPlace(1, 6));
		line.add(board.getPlace(1, 7));
		
		assertEquals(board.winningRow(), line);
	}
	
	@Test
	void testWinningRow3() {
		board.placeStone(1, 1, board.getPlayer1());
		board.placeStone(1, 2, board.getPlayer2());
		board.placeStone(2, 2, board.getPlayer1());
		board.placeStone(3, 3, board.getPlayer1());
		board.placeStone(4, 4, board.getPlayer1());
		board.placeStone(5, 5, board.getPlayer1());
		board.placeStone(6, 6, board.getPlayer1());
		board.placeStone(7, 7, board.getPlayer1());

		List<Place> line = new LinkedList<Place>();
		line.add(board.getPlace(1, 1));
		line.add(board.getPlace(2, 2));
		line.add(board.getPlace(3, 3));
		line.add(board.getPlace(4, 4));
		line.add(board.getPlace(5, 5));
		
		assertEquals(board.winningRow(), line);
	}
	
	@Test
	void testIsWonBy() {
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
		
		
		assertTrue(board.isWonBy(board.getPlayer1()));
		assertFalse(board.isWonBy(board.getPlayer2()));
	}
	
}
