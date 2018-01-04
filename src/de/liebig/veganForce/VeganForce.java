package de.liebig.veganForce;

import javax.swing.ImageIcon;

/**
 * @author coder
 *
 */
public class VeganForce extends Actor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.liebig.veganForce.Actor#keyPressed(char)
	 */
	@Override
	public boolean keyPressed(char p_keyChar) {
		// TODO Auto-generated method stub
		System.out.println("Key code:\""+p_keyChar+"\"");
		return super.keyPressed(p_keyChar);
	}

	/**
	 * @param w
	 */
	public VeganForce(World w) {
		super(w, new ImageIcon(Schwein.class.getResource("/bilder/TheMicroVeganForce.png")));
		setX(getIcon().getIconWidth()/2);
		setY(getIcon().getIconHeight()/2);
	}

	/**
	 * @param w
	 * @param i
	 */
	public VeganForce(World w, ImageIcon i) {
		super(w, i);
		// TODO Auto-generated constructor stub
	}

}
