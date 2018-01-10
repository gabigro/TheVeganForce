package de.liebig.veganForce;

import javax.swing.ImageIcon;

public class Guards extends Actor {

	private static final int RADIUS = 300;
	private Actor m_Planet;
	private double m_alfa = Math.random()*360;

	public Guards(World pW, Planet pPlanet) {
		super(pW, new ImageIcon(Planet.class.getResource("/bilder/Guardian.png")));
		m_Planet = pPlanet;
		act();
	}

	public void act() {
		double winkel = Math.toRadians(m_alfa);
		setX((int) Math.round(m_Planet.getX() + RADIUS * Math.cos(winkel)));
		setY((int) Math.round(m_Planet.getY() + RADIUS * Math.sin(winkel)));
		if (m_alfa < 360) {
			m_alfa+=2;
		} else {
			m_alfa = 0;
		}
	}

}
