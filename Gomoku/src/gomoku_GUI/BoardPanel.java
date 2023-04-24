package gomoku_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	Board board;
	
	private final Dimension dim = new Dimension(300,300);
	
	public BoardPanel(Board board) {
		super();
		this.board = board;
		
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		int line = dim.width/15;
		
		g.setColor(Color.orange);
		g.fillRect(0, 0, dim.width, dim.height);
		
		g.setColor(Color.black);
		for(int i = 1; i <= 15; i++) {
			g.drawLine(i*line, 0, i*line, dim.height);
			g.drawLine(0, line*i, dim.width, line*i);
		}
		
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				if(board.getPlace(i, j).checkStone() == null) {
					continue;
				} else if(board.getPlace(i, j).checkStone().name() == "2") {
					g.setColor(Color.black);
				} else if(board.getPlace(i, j).checkStone().name() == "1"){
					g.setColor(Color.white);
				}
				g.fillOval(i*line, j*line, line, line);
					
			}
		}
		
		
		
	}
	
	public void updateBoard(Board board) {
		
		this.board = board;
		
	}
	
	

}
