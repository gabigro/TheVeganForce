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
	private int width;
	private int height;
	private String bgURL;
	private ArrayList<Actor> actors;

	/**
	 * @return the actors
	 */
	public ArrayList<Actor> getActors() {
		return actors;
	}

	Image backgroundImage;

	public World(String url) {
		try {
			backgroundImage = javax.imageio.ImageIO.read(getClass().getResource(url));
			width = backgroundImage.getWidth(null);
			height = backgroundImage.getHeight(null);
			bgURL = url;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actors = new ArrayList<Actor>();
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		g.drawImage(backgroundImage, 0, 0, null);
		
		for (int i = 0; i < actors.size(); i++) {
			Actor a = actors.get(i);
			AffineTransform at = new AffineTransform();
			int w = a.getIcon().getIconWidth();
			int h = a.getIcon().getIconHeight();
			at.setToRotation(a.getRotation() / 180.0 * 3.1415926, a.getX(), a.getY());
			at.translate(a.getX() - w / 2, a.getY() - h / 2);
			g2d.setTransform(at);
			g2d.drawImage(a.getIcon().getImage(), 0, 0, null);
		}
		
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int z) {
		width = z;

	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int z) {
		height = z;

	}

	public String getBackground() {
		return bgURL;
	}

	public void setBackground(String url) {
		bgURL = url;

	}

	public void addActor(Actor a) {
		actors.add(a);
	}

	public void removeActor(Actor a) {
		actors.remove(a);
	}

	public void start() {

	}

	public void update() {
		for (int i = 0; i < actors.size(); i++)
			actors.get(i).act();
		
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
		for (int i = 0; i < actors.size(); i++) {
			Actor myActor = actors.get(i);
			boolean processed = myActor.keyPressed(p_keyChar);

		}
	}

}
