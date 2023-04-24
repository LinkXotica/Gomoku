package networkGomoku;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

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
	JPanel button;
	JLayeredPane layer;
	
	
	private Game game = new Game();
	
	public GomokuGUI() {
		
		frame = new JFrame("Gomoku");
		menubar = new JMenuBar();
		menu = new JMenu("Game");
		board = new BoardPanel(game.getBoard());
		play = new JMenuItem("Play", KeyEvent.VK_P);
		button = buttonLayout();
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
		
		menu.add(play);
		
		menubar.add(menu);
		
		board.setPreferredSize(new Dimension(300,300));
		
		
		
		
		
		frame.setJMenuBar(menubar);
		
		layer = layer(board, button);
		
		frame.add(layer);

		frame.pack();
		frame.setVisible(true);
		
		game.getBoard().setPlayerType(1, 1);
		
		
	}
	
	public JPanel buttonLayout() {
		JPanel buttonPanel = new JPanel(new GridLayout(15,15));
		
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				
				createButton(buttonPanel,j,i);
				
			}
		}
		
		
		
		
		
		return buttonPanel;
		
	}
	
	public void setButtonLayout() {
		
		button.setBounds(0,0,300,300);
		layer.add(button, JLayeredPane.DEFAULT_LAYER);
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
	
	public void createButton(JPanel panel, int x, int y) {
		
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
				this.button.setVisible(false);
			}
		});
		button.setEnabled(true);
		panel.add(button);
		
	}
	
	public void playGame() {
		layer.remove(button);
		game.getBoard().clear();
		button = buttonLayout();
		setButtonLayout();
		
		button.setVisible(true);
		
		
		
	}
	
	public static void main(String[] args) {
		GomokuGUI board = new GomokuGUI();
	}
	
}
