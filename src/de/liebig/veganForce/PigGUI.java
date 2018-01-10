package de.liebig.veganForce;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Draws the user interface (window, buttons, etc.) and connects it to World.
 * 
 * @author mailto://coder.gabriela.grobarcikova@gmail.com
 *
 */
public class PigGUI {

	private static final int MAX_PIG = 4;
	private static final int GAME_TIME = 1000;
	private int m_level = 1;
	private JFrame m_frame;
	private World m_pigWorld;
	private Timer m_timer;
	private FlyingPiggie m_pig;
	private JPanel m_content = new JPanel(null);
	private VeganForce m_veganForce;
	private ArrayList<Planet> m_planets;
	private ArrayList<Piggie> m_Piggies;
	private ArrayList<Guards> m_guards;
	private JTextField m_TimerLabel = new JTextField("Game reaady!");
	private int m_Gametime;
	private GameOver m_gameOver;
	private BeforeGame m_beforeGame;
	private MissionAcc m_MissionAcc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PigGUI window = new PigGUI();
					window.m_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PigGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		m_frame = new JFrame();
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_timer = new Timer(0, null);
		resetWorld();

		m_frame.setContentPane(new JPanel(null) {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				m_pigWorld.draw(g);

			}

		});

		m_frame.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				setViewSize();
			}

			@Override
			public void componentHidden(ComponentEvent pArg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent pArg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent pArg0) {
				// TODO Auto-generated method stub

			}
		});

		m_frame.getContentPane().setPreferredSize(new Dimension(600, 450));
		m_frame.pack();

		m_TimerLabel.setBounds(new Rectangle(16, 64, 138 + 96 - 16, 26));
		m_frame.add(m_TimerLabel);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetWorld();
				m_pigWorld.setGameState(GameState.BEFOREGAME);
				m_timer.restart();
			}
		});
		btnReset.setBounds(16, 16, 96, 26);
		m_frame.getContentPane().add(btnReset);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetWorld();
				m_pigWorld.setGameState(GameState.WHILEGAME);
				m_timer = new Timer(50, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						m_pigWorld.update();
						checkGameOver();
						m_frame.repaint();
					}

				});
				m_timer.start();
			}
		});

		btnStart.setBounds(138, 16, 96, 26);
		m_frame.getContentPane().add(btnStart);

		btnStart.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyChar() == 'm') {
					m_pigWorld.removeActor(m_pig);
					System.out.println(0);
				}
				m_pigWorld.keyPressed(arg0.getKeyChar());
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		m_frame.getContentPane().addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				System.out.print(1);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void resetWorld() {

		m_planets = new ArrayList<Planet>();
		m_Piggies = new ArrayList<Piggie>();
		m_guards = new ArrayList<Guards>();

		m_pigWorld = new World("/bilder/weltraum.jpg");

		m_pigWorld.setGameState(GameState.BEFOREGAME);

		for (int i = 0; i < m_level; i++) {
			Planet myPlanet = new Planet(m_pigWorld);
			m_pigWorld.addActor(myPlanet);
			m_planets.add(myPlanet);
		}

		for (Planet myPlanet : m_planets) {
			for (int i = 0; i < 3; i++) {
				Guards myGuard = new Guards(m_pigWorld, myPlanet);
				m_pigWorld.addActor(myGuard);
				m_guards.add(myGuard);
			}
		}

		m_veganForce = new VeganForce(m_pigWorld);
		m_pigWorld.addActor(m_veganForce);

		// Add piggies
		int planetcounter = 1;

		for (Planet myPlanet : m_planets) {
			for (int i = 0; i < MAX_PIG; i++) {
				Piggie myPiggie = new Piggie(planetcounter, m_pigWorld, myPlanet);
				m_pigWorld.addActor(myPiggie);
				m_Piggies.add(myPiggie);
				planetcounter++;
			}
		}

		m_pig = new FlyingPiggie(m_pigWorld);
		m_pigWorld.addActor(m_pig);

		m_gameOver = new GameOver(m_pigWorld);
		m_pigWorld.addActor(m_gameOver);

		m_MissionAcc = new MissionAcc(m_pigWorld);
		m_pigWorld.addActor(m_MissionAcc);

		m_beforeGame = new BeforeGame(m_pigWorld);
		m_pigWorld.addActor(m_beforeGame);

		m_Gametime = (2 * m_level * GAME_TIME) / 3;

		setViewSize();

		if (m_timer != null)
			m_timer.stop();
		m_frame.repaint();
		System.out.println(m_frame.getFocusOwner());
	}

	/**
	 * checking the status and giving the timer of the game
	 */
	private void checkGameOver() {

		if (m_pigWorld.getGameState() == GameState.WHILEGAME) {
			if (m_Gametime > 0) {
				m_Gametime--;
				m_TimerLabel.setText(m_Gametime + " Level: " + m_level);
				if (m_pigWorld.isMissionAcc() == true) {
					m_pigWorld.setGameState(GameState.MISSIONACC);
					m_level++;
					m_TimerLabel.setText("press START for Level: " + m_level);

				}
			} else {
				if (m_pigWorld.isMissionAcc() == false) {
					m_pigWorld.setGameState(GameState.GAMEOVER);
					m_TimerLabel.setText("Failed at Level: " + m_level);
					m_level = 1;
				}
			}
		}
	}

	private void setViewSize() {
		m_pigWorld.setViewSize(m_frame.getWidth(), m_frame.getHeight());
	}

}
