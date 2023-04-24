package networkGomoku;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstraction of an Omok board, which consists of n x n intersections
 * or places where players can place their stones. The board can be 
 * accessed using a pair of 0-based indices (x, y), where x and y 
 * denote the column and row number, respectively. The top-left 
 * intersection is represented by the indices (0, 0), and the
 * bottom-right intersection is represented by the indices (n-1, n-1).
 */

/** @author Danilo Romero
 * 
 * 
 *
 */
public class Board {
	
	private int size;
	private Place [][] board;
	private Player player1;
	private Player player2;	
	

    /** Create a new board of the default size. */
    public Board() {
    	this(15);
    }

    /** Create a new board of the specified size. */
    public Board(int size) {
    	this.size = size;
    	board = new Place[size][size];
		for(int i = 0; i < size; i ++) {
			for(int j = 0; j < size; j ++) {
				this.board[i][j] = new Place(j,i);
			}
		}
    }

    /** Return the size of this board. */
    public int size() {
		return size;
    }
    
    /** Removes all the stones placed on the board, effectively 
     * resetting the board to its original state. 
     */
    public void clear() {
    	for(int i = 0; i < size; i ++) {
			for(int j = 0; j < size; j ++) {
				this.board[i][j].setStone(null);
			}
		}
    }

    /** Return a boolean value indicating whether all the places
     * on the board are occupied or not.
     */
    public boolean isFull() {
    	for(int i = 0; i < size; i ++) {
			for(int j = 0; j < size; j ++) {
				if(this.board[i][j].checkStone() == null) {
					return false;
				}
			}
		}
    	return true;
    }
    
    /**
     * Place a stone for the specified player at a specified 
     * intersection (x, y) on the board.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     * @param player Player whose stone is to be placed
     */
    public void placeStone(int x, int y, Player player) {
    	board[y][x].setStone(player);
    }
    
    /**
     * Return a boolean value indicating whether the specified 
     * intersection (x, y) on the board is empty or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isEmpty(int x, int y) {
    	if(board[y][x].checkStone() == null) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Is the specified place on the board occupied?
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupied(int x, int y) {
    	if(board[y][x].checkStone() != null) {
    		return true;
    	}
    	return false;
    }

    /**
     * Return a boolean value indicating whether the specified 
     * intersection (x, y) on the board is occupied by the given 
     * player or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupiedBy(int x, int y, Player player) {
    	if(board[y][x].checkStone() == player) {
    		return true;
    	}
    	return false;
    }

    /**
     * Return the player who occupies the specified intersection (x, y) 
     * on the board. If the place is empty, this method returns null.
     * 
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public Player playerAt(int x, int y) {
    	return board[y][x].checkStone();
    }

    /** 
     * Return a boolean value indicating whether the given player 
     * has a winning row on the board. A winning row is a consecutive 
     * sequence of five or more stones placed by the same player in 
     * a horizontal, vertical, or diagonal direction.
     */
    public boolean isWonBy(Player player) {
    	if(this.winningRow().get(0).checkStone() == player) {
    		return true;
    	}
    	return false;
    }

    /** Return the winning row. For those who are not familiar with
     * the Iterable interface, you may return an object of
     * List<Place>. */
    public List<Place> winningRow() {
    	List<Place> line = new LinkedList<Place>();
    	for(int i = 0; i < size; i ++) {
			for(int j = 0; j < size; j ++) {
				if(this.checkDiagonal(board[i][j])) {
					for(int count = 0; count < 5; count++) {
		    			line.add(board[i+count][j+count]);	
		    		}
					return line;
				}
				if(this.checkHorizontal(board[i][j])) {
					for(int count = 0; count < 5; count++) {
		    			line.add(board[i][j+count]);	
		    		}
					return line;
				}
				if(this.checkVertical(board[i][j])) {
					for(int count = 0; count < 5; count++) {
		    			line.add(board[i+count][j]);	
		    		}
					return line;
				}
			}
		}
    	return null;
    }
    /** check if horizontal row has same stone
     *  catches if goes out of bounds
     *  @param Place starting place*/
    public boolean checkHorizontal(Place start) {
    	
    	try {
    		for(int i = 1; i < 5; i++) {
    			if(board[start.y][start.x].checkStone() != board[start.y][start.x + i].checkStone() || board[start.y][start.x + i].checkStone() == null) {
    				return false;
    				
    			}
    			
    		}
    	} catch(ArrayIndexOutOfBoundsException e) {
    		
    		return false;
    	}
    	return true;
    	
    }
    
    /** check if vertical column has same stone
     *  catches if goes out of bounds
     * @param Place starting place*/
    
    public boolean checkVertical(Place start) {
    	
    	try {
    		for(int i = 1; i < 5; i++) {
    			if(board[start.y][start.x].checkStone() != board[start.y + i][start.x].checkStone() || board[start.y + i][start.x].checkStone() == null) {

    				return false;
    			}

    		}
    	} catch(ArrayIndexOutOfBoundsException e) {
    		return false;
    	}
    	return true;
    	
    }
    
    /** check if Diagonal has same stone
     * catches if goes out of bounds
     * @param Place starting place*/
    
    public boolean checkDiagonal(Place start) {
    	
    	try {
    		for(int i = 1; i < 5; i++) {
    			if(board[start.y][start.x].checkStone() != board[start.y + i][start.x + i].checkStone() || board[start.y + i][start.x + i].checkStone() == null) {
    				return false;
    			}
    			
    		}
    	} catch(ArrayIndexOutOfBoundsException e) {
    		return false;
    	}
    	return true;
    	
    }
    
    public void setPlayerType(int p1, int p2) {
    	if(p1 == 1) {
			player1 = new HumanPlayer("1");
		} else {
			player1 = new ComPlayer("1");
		}
		
		if(p2 == 1) {
			player2 = new HumanPlayer("2");
		} else {
			player2 = new ComPlayer("2");
		}
    }

    public Player getPlayer2() {
		return player2;
	}

	public Player getPlayer1() {
		return player1;
	}
	
	public Place getPlace(int x, int y) {
		return board[y][x];
	}
	
	public String toString() {
		
		String b = "";
		
		for(int i = 0; i < size; i++) {
			for( int j = 0; j < size; j++) {
				if(board[i][j].checkStone() == null) {
					b = b + "-";
				} else {
					b = b + board[i][j].checkStone().name();
				}
			}
		}
		return b;
		
	}

	/**
     * An intersection on an Omok board identified by its 0-based column
     * index (x) and row index (y). The indices determine the position 
     * of a place on the board, with (0, 0) denoting the top-left 
     * corner and (n-1, n-1) denoting the bottom-right corner, 
     * where n is the size of the board.
     */
    public static class Place {
        /** 0-based column index of this place. */
        public final int x;

        /** 0-based row index of this place. */
        public final int y;
        
        /** Assigned Player */
        private Player player = null;

        /** Create a new place of the given indices. 
         * 
         * @param x 0-based column (vertical) index
         * @param y 0-based row (horizontal) index
         */
        public Place(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public void setStone(Player player) {
        	
        	this.player = player;
        	
        }
        
        /** Assign a Player */
        public Player checkStone() {
        	return player;
        }

        // other methods if needed ...
    }
}

