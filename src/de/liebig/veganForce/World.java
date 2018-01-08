package de.liebig.veganForce;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;

/**
 * paint our world
 * 
 * @author mailto://coder.gabriela.grobarcikova@gmail.com
 *
 */
public class World {
	private int m_width;
	private int m_height;
	private String m_bgURL;
	private ArrayList<Actor> m_actors;
	private GameState m_gameState = GameState.BEFOREGAME;
	private int m_viewWidth = 0;
	private int m_viewHeight = 0;
	private int m_xOfset = 0;
	private int m_yOfset = 0;

	/**
	 * @return the xOfset
	 */
	public int getxOfset() {
		return m_xOfset;
	}

	/**
	 * @param pXOfset
	 *            the xOfset to set
	 */
	public void setxOfset(int pXOfset) {
		m_xOfset = pXOfset;
	}

	/**
	 * @return the yOfset
	 */
	public int getyOfset() {
		return m_yOfset;
	}

	/**
	 * @param pYOfset
	 *            the yOfset to set
	 */
	public void setyOfset(int pYOfset) {
		m_yOfset = pYOfset;
	}

	public GameState getGameState() {
		return m_gameState;
	}

	public void setGameState(GameState p_NewState) {
		m_gameState = p_NewState;
	}

	/**
	 * @return the actors
	 */
	public ArrayList<Actor> getActors() {
		return m_actors;
	}

	Image backgroundImage;

	public World(String url) {
		try {
			backgroundImage = javax.imageio.ImageIO.read(getClass().getResource(url));
			m_width = backgroundImage.getWidth(null);
			m_height = backgroundImage.getHeight(null);
			m_bgURL = url;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_actors = new ArrayList<Actor>();
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		g.drawImage(backgroundImage, -getxOfset(), -getyOfset(), null);

		for (int i = 0; i < m_actors.size(); i++) {
			Actor a = m_actors.get(i);
			if (a.isEnabled() == true) {
				AffineTransform at = new AffineTransform();
				int w = a.getIcon().getIconWidth();
				int h = a.getIcon().getIconHeight();
				at.setToRotation(a.getRotation() / 180.0 * 3.1415926, -getxOfset()+a.getX(),-getyOfset()+ a.getY());
				at.translate(-getxOfset()+a.getX() - w / 2, -getyOfset()+a.getY() - h / 2);
				g2d.setTransform(at);
				g2d.drawImage(a.getIcon().getImage(), 0, 0, null);
			}

		}

	}

	/**
	 * 
	 */
	public boolean isMissionAcc() {
		// First I get my actors
		ArrayList<Actor> myWorldActors = this.getActors();
		for (int i = 0; i < myWorldActors.size(); i++) {
			Actor aWorldActor = myWorldActors.get(i);
			// Then I test if they are piggies
			if (aWorldActor instanceof Piggie) {
				Piggie aWorldPiggie = (Piggie) aWorldActor;
				if (aWorldPiggie.isCaught() != true) {
					return false;
					
				}

			}

		}
		return true;
	}

	public int getWidth() {
		return m_width;
	}

	public void setWidth(int z) {
		m_width = z;

	}

	public int getHeight() {
		return m_height;
	}

	public void setHeight(int z) {
		m_height = z;

	}

	public String getBackground() {
		return m_bgURL;
	}

	public void setBackground(String url) {
		m_bgURL = url;

	}

	public void addActor(Actor a) {
		m_actors.add(a);
	}

	public void removeActor(Actor a) {
		m_actors.remove(a);
	}

	public void start() {

	}

	public void update() {
		for (int i = 0; i < m_actors.size(); i++)
			m_actors.get(i).act();

	}

	/**
	 * Processing keyPressed events comming from Gui and informing the actors about
	 * it.
	 * 
	 * @param p_keyChar
	 *            character of passed from GUI
	 */
	public void keyPressed(char p_keyChar) {
		// TODO Auto-generated method stub
		for (int i = 0; i < m_actors.size(); i++) {
			Actor myActor = m_actors.get(i);
			boolean processed = myActor.keyPressed(p_keyChar);

		}
	}

	public void setViewSize(int pWidth, int pHeight) {
		m_viewHeight = pHeight;
		m_viewWidth = pWidth;
	}

	/**
	 * @return the viewWidth
	 */
	public int getViewWidth() {
		return m_viewWidth;
	}

	/**
	 * @return the viewHeight
	 */
	public int getViewHeight() {
		return m_viewHeight;
	}

}
