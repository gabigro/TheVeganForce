package de.liebig.veganForce;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class PigGUI {

	private JFrame m_frame;
	private World pigWorld;
	private Timer m_timer;
	private Schwein pig;
	private JPanel content = new JPanel(null);

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
				pigWorld.draw(g);

			}

		});

		m_frame.getContentPane().setPreferredSize(new Dimension(600, 450));
		m_frame.pack();

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
				m_timer = new Timer(50, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pigWorld.update();
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
					pigWorld.removeActor(pig);
					System.out.println(0);
				}
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
		pigWorld = new World("/bilder/weltraum.jpg");
		pig = new Schwein(pigWorld);
		pigWorld.addActor(pig);

		if (m_timer != null)
			m_timer.stop();
		m_frame.repaint();
		System.out.println(m_frame.getFocusOwner());
	}

}
