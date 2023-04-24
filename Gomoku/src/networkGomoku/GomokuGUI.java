package networkGomoku;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import noApplet.NoApplet;

@SuppressWarnings("serial")
public class GomokuGUI extends NoApplet {

	private final Dimension dim = new Dimension(600,600);
	
	JFrame frame;
	JMenuBar menubar;
	JMenu menu;
	BoardPanel board;
	JMenuItem play; 
	JMenuItem connect; 
	JPanel buttonPanel;
	List<JRadioButton> buttons = new LinkedList<JRadioButton>();
	JLayeredPane layer;
	
	
	private Game game = new Game();
	
	public GomokuGUI() {
		
		frame = new JFrame("Gomoku");
		menubar = new JMenuBar();
		menu = new JMenu("Game");
		board = new BoardPanel(game.getBoard());
		play = new JMenuItem("Play", KeyEvent.VK_P);
		connect = new JMenuItem("Connect", KeyEvent.VK_C);
		buttonPanel = buttonLayout();
		
		configureGUI(dim);
		
	}
	
	public void configureGUI(Dimension dim) {
		
		menu.setMnemonic(KeyEvent.VK_G);
		menu.getAccessibleContext().setAccessibleDescription("Game menu");
		
		
		
		play.setIcon(null);
		play.setAccelerator(KeyStroke.getKeyStroke(
		   KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK));
		play.getAccessibleContext().setAccessibleDescription(
		   "Play a new game");
		
		play.addActionListener(e ->{
			this.playGame();
		});
		
		connect.setIcon(null);
		connect.setAccelerator(KeyStroke.getKeyStroke(
		   KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		connect.getAccessibleContext().setAccessibleDescription(
		   "Play a new game");
		
		connect.addActionListener(e ->{
			this.connectGame();
		});
		
		menu.add(play);
		menu.add(connect);
		
		menubar.add(menu);
		
		board.setPreferredSize(new Dimension(300,300));
		
		
		
		
		
		frame.setJMenuBar(menubar);
		
		layer = layer(board, buttonPanel);
		
		frame.add(layer);

		frame.pack();
		frame.setVisible(true);
		
		game.getBoard().setPlayerType(1, 1);
		
		
	}
	
	public JPanel buttonLayout() {
		JPanel buttonPanel = new JPanel(new GridLayout(15,15));
		
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				
				createButton(j,i);
				buttonPanel = setButtons(buttonPanel);
				
			}
		}
		
		return buttonPanel;
		
	}
	//stinky stink
	public void setButtonLayout() {
		
		buttonPanel.setBounds(0,0,300,300);
		layer.add(buttonPanel, JLayeredPane.DEFAULT_LAYER);
	}
	
	public JPanel setButtons(JPanel panel) {
		
		
		
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				
				System.out.print(buttons.size());
				
				panel.add(buttons.get((i*15)+ j));
				
			}
		}
		
		return panel;
		
	}
	
	public static JLayeredPane layer(JPanel board, JPanel button) {
		
		JLayeredPane layer = new JLayeredPane();
		layer.setPreferredSize(new Dimension(300,300));
		
		board.setBounds(0,0,300,300);
		button.setBounds(0,0,300,300);
		
		button.setVisible(false);
		
		
		layer.add(board, JLayeredPane.PALETTE_LAYER);
		layer.add(button, JLayeredPane.DEFAULT_LAYER);
		
		
		
		return layer;
		
	}
	
	public void createButton(int x, int y) {
		
		JRadioButton button = new JRadioButton();
		
		
		
		button.addActionListener(e -> {
			System.out.print(x + " " + y);
			if(game.getp1Turn() == true) {
				game.getBoard().placeStone(x, y, game.getBoard().getPlayer1());
			} else {
				game.getBoard().placeStone(x, y, game.getBoard().getPlayer2());
			}
			button.setEnabled(false);
			this.board.setEnabled(false);
			this.board.setEnabled(true);
			game.switchTurn();
			
			if(game.winConditon()) {
				System.out.println("Game Won");
				this.buttonPanel.setVisible(false);
			}
		});
		button.setEnabled(true);
		buttons.add(button);
		
		
	}
	
	public void playGame() {
		layer.remove(buttonPanel);
		game.getBoard().clear();
		buttonPanel = buttonLayout();
		setButtonLayout();
		
		buttonPanel.setVisible(true);
		
		
		
	}
	
	public void connectGame() {
		
	}
	
	public static void main(String[] args) {
		GomokuGUI board = new GomokuGUI();
	}
	
}
