package de.liebig.veganForce;

import javax.swing.ImageIcon;

public class Planet extends Actor {

	private static final int RAND_STEP = 300;

	public Planet(World w) {
		super(w, new ImageIcon(Planet.class.getResource("/bilder/planet101.png")));
		setX((int) (((w.getWidth() - 2 * RAND_STEP) * Math.random()) + RAND_STEP));
		setY((int) (((w.getHeight() - 2 * RAND_STEP) * Math.random()) + RAND_STEP));
	}

}
