package networkGomoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

import networkGomoku.NetworkAdapter.MessageListener;
import networkGomoku.NetworkAdapter.MessageType;

@SuppressWarnings("serial")
public class GomokuGUI extends JFrame {

	private final Dimension dim = new Dimension(600,600);
	
	private JFrame gameFrame;
	private JMenuBar menubar;
	private JMenu menu;
	private BoardPanel board;
	private JMenuItem play; 
	private JMenuItem connect; 
	private JPanel buttonPanel;
	private List<JRadioButton> buttons = new LinkedList<>();
	private JLayeredPane layer;
	private NetworkGUI networkFrame;
	private boolean connected = false;
	
	private NetworkAdapter adapter;
	private Game game = new Game();
	
	public GomokuGUI() {
		
		gameFrame = new JFrame("Gomoku");
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
		
		
		
		
		
		gameFrame.setJMenuBar(menubar);
		
		
		
		layer = layer(board, buttonPanel);
		
		gameFrame.add(layer);

		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		game.getBoard().setPlayerType(1, 1);
		game.getBoard().placeStone(7,7,game.getBoard().getPlayer1());
		game.getBoard().placeStone(6,6,game.getBoard().getPlayer1());
		game.getBoard().placeStone(6,7,game.getBoard().getPlayer2());
		game.getBoard().placeStone(7,6,game.getBoard().getPlayer2());
		
		
		
		
	}
	
	public JPanel buttonLayout() {
		buttons = new LinkedList<JRadioButton>();
		JPanel buttonPanel = new JPanel(new GridLayout(15,15));
		
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				
				createButton(j,i);
				
				
			}
		}
		buttonPanel = setButtons(buttonPanel);
		return buttonPanel;
		
	}
	
	
	
	public void setButtonLayout() {
		
		buttonPanel.setBounds(0,0,300,300);
		layer.add(buttonPanel, JLayeredPane.DEFAULT_LAYER);
	}
	
	public JPanel setButtons(JPanel panel) {
		
		
		
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				
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
			if(connected  == false) {
				buttonPressOffline(button,x,y);
			} else {
				buttonPressOnline(button,x,y);
			}
		});
		button.setEnabled(true);
		buttons.add(button);
		
		
	}
	
	private void buttonPressOnline(JRadioButton button, int x, int y) {

		System.out.println(x + " " + y);
		if(game.getp1Turn() == true) {
			game.getBoard().placeStone(x, y, game.getBoard().getPlayer1());
			adapter.writeMove(x, y);
			networkFrame.status.append("Move: " + x + " " + y + "\n");
			
		} else {
			game.getBoard().placeStone(x, y, game.getBoard().getPlayer2());
		}
		button.setEnabled(false);
		this.board.setEnabled(false);
		this.board.setEnabled(true);
		game.switchTurn();
		
		
		if (game.winConditon()) {
			System.out.println("Game Won");
			this.buttonPanel.setVisible(false);
		}
		
	}

	public void buttonPressOffline(JRadioButton button, int x, int y) {
		System.out.println(x + " " + y);
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
	}
	
	public void playGame() {
		
		if(!connected) {
			layer.remove(buttonPanel);
			game.getBoard().clear();
			buttonPanel = buttonLayout();
			setButtonLayout();
			
			buttonPanel.setVisible(true);
		} else {
			layer.remove(buttonPanel);
			game.getBoard().clear();
			buttonPanel = buttonLayout();
			setButtonLayout();
			
			buttonPanel.setVisible(true);
			adapter.writePlay();
			JOptionPane.showMessageDialog(gameFrame, "Waiting...", "Responding", 1) ;
				
			
				
			
		}
		
		
	}
	
	public void connectGame() {
		
		networkFrame = new NetworkGUI();
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(GomokuGUI::new);
		
	}
	
	

	

	class NetworkGUI {
		
		JFrame networkFrame;
		JTextField hostName;
		JTextField IPNumber;
		JTextField portNumber;
		JTextField hostNameOp;
		JTextField portNumberOp;
		JButton connectButton;
		JButton disconnectButton;
		JButton reserver;
		JTextArea status;
		JPanel player;
		JPanel opponent;
		
		
		public NetworkGUI() {
			
			networkFrame = new JFrame("Network Pair");
			
			try {
				hostName = new JTextField(InetAddress.getLocalHost().toString().split("/")[0]);
				IPNumber = new JTextField(InetAddress.getLocalHost().toString().split("/")[1]);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			portNumber = new JTextField("8000");
			hostNameOp = new JTextField("Enter Text");
			portNumberOp = new JTextField("Enter Text");
			status = new JTextArea();
			connectButton = new JButton("Connect");
			disconnectButton = new JButton("Disconnect");
			reserver = new JButton("Reserver");
			player = new JPanel(new FlowLayout(1));
			opponent = new JPanel(new FlowLayout(1));
			
			
			configureGUI();
			
		}
		
		

			public void messageReceivedOver(MessageType type, int x, int y) {
				
				if(type == MessageType.MOVE) {
					
					status.append("Move: " + x + " " + y + "\n");
					
					buttons.get((y*15) + x).doClick();
					
					adapter.writeMoveAck(x, y);
					
				} else if(type == MessageType.PLAY) {
					
					status.append("Play: " + x + " " + y + "\n");

					if(JOptionPane.showConfirmDialog(networkFrame,"Play Game?","Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						
						boolean turn = false;
						if(Math.random()+.5 > 1) {
							turn = true;
							
							
						}
						
						game.setP1Turn(!turn);
						
						layer.remove(buttonPanel);
						game.getBoard().clear();
						buttonPanel = buttonLayout();
						setButtonLayout();
						
						buttonPanel.setVisible(true);
						
						disconnectButton.setEnabled(true);
						connectButton.setEnabled(false);
						
						connected = true;
						
						
						
						adapter.writePlayAck(true, turn);
						
						
					}
					
				} else if(type == MessageType.PLAY_ACK) {
					
					status.append("Play Ack: " + x + " " + y + "\n");
					
					if(x == 0) {
						disconnectButton.doClick();
						adapter.writeQuit();
						
						
					} 
					
					if(y == 1) {
						game.setP1Turn(true);
					} else {
						game.setP1Turn(false);
					}
					
				} else if(type == MessageType.MOVE_ACK) {
					
					status.append("Move Ack: " + x + " " + y + "\n");
					
				} else if(type == MessageType.QUIT) {
					
					status.append("Quit: " + x + " " + y + "\n");
					
					disconnectButton.doClick();
					
				}
				
			}
		
		
		public void configureGUI() {
			
			
			player.setSize(300, 200);
			player.setBackground(Color.PINK);
			
			player.add(new JLabel("Player -------------------------------------------------------------"));
			
			player.add(new JLabel("Host Name"));
			hostName.setColumns(20);
			player.add(hostName);
			hostName.setEditable(false);
			
			
			player.add(new JLabel("IP Address"));
			IPNumber.setColumns(20);
			player.add(IPNumber);
			IPNumber.setEditable(false);
			
			player.add(new JLabel("Port Number"));
			portNumber.setColumns(20);
			player.add(portNumber);
			portNumber.setEditable(true);
			

			
			reserver.addActionListener(b ->{
				
				new Thread(new Runnable() {
			        public void run() {
			        	Socket socket = null;
			        	ServerSocket s;
						try {
							s = new ServerSocket(Integer.parseInt(portNumber.getText()));
							socket = s.accept();
						} catch (NumberFormatException  | IOException e1) {
							e1.printStackTrace();
						} 
			            
			            System.out.println("Client connected");
			            
			            adapter = new NetworkAdapter(socket);
						adapter.setMessageListener(new MessageListener() {

							@Override
							public void messageReceived(MessageType type, int x, int y) {
								
								messageReceivedOver(type,x,y);
								
							}
						});
						
						adapter.receiveMessagesAsync();
			        }
			    }).start();

				
				
				
			});
			player.add(reserver);
			
			opponent.setSize(300, 200);
			opponent.setBackground(Color.GRAY);
			
			opponent.add(new JLabel("Opponent ----------------------------------------------------------"));
			
			opponent.add(new JLabel("Host Name / IP Address"));
			hostNameOp.setColumns(20);
			opponent.add(hostNameOp);
			portNumber.setEditable(true);
			
			opponent.add(new JLabel("Port Number"));
			portNumberOp.setColumns(20);
			opponent.add(portNumberOp);
			portNumber.setEditable(true);
			
			disconnectButton.setEnabled(false);
			
			connectButton.addActionListener(e ->{
				disconnectButton.setEnabled(true);
				connectButton.setEnabled(false);
				
				connected = true;
				
				Socket socket = null;
				try {
					socket = new Socket(hostName.getText(),Integer.parseInt(portNumberOp.getText()));
					status.append("Socket Connected\n");
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
					status.append("Socket Not Connected\n");
					disconnectButton.doClick();
				}
				
				adapter = new NetworkAdapter(socket);
				adapter.setMessageListener(new MessageListener() {

					@Override
					public void messageReceived(MessageType type, int x, int y) {
						
						messageReceivedOver(type,x,y);
						
					}
				});
				
				adapter.receiveMessagesAsync();
				
			});
			
			disconnectButton.addActionListener(e ->{
				connectButton.setEnabled(true);
				disconnectButton.setEnabled(false);
				
				adapter.close();
				adapter.writeQuit();
				
				connected = false;
				playGame();
			});
			
			
			
			opponent.add(connectButton);
			opponent.add(disconnectButton);
			
			JScrollPane statusScroll = new JScrollPane(status);
			status.setEnabled(false);
			
			networkFrame.setSize(300, 600);
			networkFrame.setResizable(false);
			networkFrame.setLayout(new GridLayout(3,1));
			networkFrame.add(player);
			networkFrame.add(opponent);
			networkFrame.add(statusScroll);
			networkFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
			
			networkFrame.setVisible(true);
			
		}
		
		public JFrame getFrame() {
			return networkFrame;
		}
		
		
	}

	
}


