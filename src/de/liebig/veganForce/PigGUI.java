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

import javax.swing.*;

/**
 * Draws the user interface (window, buttons, etc.) and connects it to World.
 * 
 * @author mailto://coder.gabriela.grobarcikova@gmail.com
 *
 */
public class PigGUI {

	private JFrame m_frame;
	private World m_pigWorld;
	private Timer m_timer;
	private FlyingPiggie m_pig;
	private JPanel m_content = new JPanel(null);
	private VeganForce m_veganForce;
	private Planet m_planet;
	private Piggie m_Piggie;
	private JTextField m_TimerLabel = new JTextField("Game reaady!");
	private int m_Gametime;

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

		m_frame.getContentPane().setPreferredSize(new Dimension(600, 450));
		m_frame.pack();

		m_TimerLabel.setBounds(new Rectangle(16, 64, 138 + 96 - 16, 26));
		m_frame.add(m_TimerLabel);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetWorld();
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
		m_Gametime = 10000;
		m_pigWorld = new World("/bilder/weltraum.jpg");
		
		m_pigWorld.setGameState(GameState.BEFOREGAME);
		
		m_planet = new Planet(m_pigWorld);
		m_pigWorld.addActor(m_planet);

		m_veganForce = new VeganForce(m_pigWorld);
		m_pigWorld.addActor(m_veganForce);

		// Add piggie no 1
		m_Piggie = new Piggie(1, m_pigWorld);
		m_pigWorld.addActor(m_Piggie);

		m_pig = new FlyingPiggie(m_pigWorld);
		m_pigWorld.addActor(m_pig);

		if (m_timer != null)
			m_timer.stop();
		m_frame.repaint();
		System.out.println(m_frame.getFocusOwner());
	}

	/**
	 * checking the status and giving the timer of the game
	 */
	private void checkGameOver() {

		if (m_Gametime > 0) {
			m_Gametime--;
			m_TimerLabel.setText(Integer.toString(m_Gametime));
		} else {
			m_pigWorld.setGameState(GameState.GAMEOVER);
		}
	}
}
