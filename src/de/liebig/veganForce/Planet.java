package de.liebig.veganForce;

import javax.swing.ImageIcon;

public class Planet extends Actor {

	public Planet(World w) {
		super(w, new ImageIcon(Planet.class.getResource("/bilder/planet101.png")));
		setX(1000);
		setY(500);
	}

}
