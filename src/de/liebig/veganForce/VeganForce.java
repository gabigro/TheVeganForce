package de.liebig.veganForce;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

/**
 * @author coder
 *
 */
public class VeganForce extends Actor {

	private static final int STEP = 20;
	ArrayList<Piggie> m_CaughtPiggies = new ArrayList<Piggie>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.liebig.veganForce.Actor#keyPressed(char)
	 */
	@Override
	public boolean keyPressed(char p_keyChar) {
		System.out.println("Key code:\"" + p_keyChar + "\"");
		if (p_keyChar == 'd') {
			int mynewX = getX() + STEP;
			setX(mynewX);
			setRotation(0);
		}
		if (p_keyChar == 'a') {
			int mynewX = getX() - STEP;
			setX(mynewX);
			setRotation(180);
		}
		if (p_keyChar == 'w') {
			int mynewY = getY() - STEP;
			setY(mynewY);
			setRotation(270);
		}
		if (p_keyChar == 's') {
			int mynewY = getY() + STEP;
			setY(mynewY);
			setRotation(90);
		}
		piggieEinsammeln();
		moveCaughPiggies();
		return true;
	}

	private void moveCaughPiggies() {
		int myStep = 0;
		for (Piggie myCaughtPiggie : m_CaughtPiggies) {
			myCaughtPiggie.setX(getX() + myStep);
			myCaughtPiggie.setY(getY());
		}
		// TODO Auto-generated method stub

	}

	void piggieEinsammeln() {

		ArrayList<Actor> myWorldActors = getWorld().getActors();
		for (int i = 0; i < myWorldActors.size(); i++) {
			Actor aWorldActor = myWorldActors.get(i);
			if (aWorldActor instanceof Piggie) {
				Piggie aWorldPiggie = (Piggie) aWorldActor;
				for (Piggie aCaughtPiggie : m_CaughtPiggies) {
					if (aWorldPiggie.getId() == aCaughtPiggie.getId()) {
						aWorldPiggie = null;
						break;
					}
				}
				if (aWorldPiggie != null) {
					if (isInShip(aWorldPiggie)) {
						aWorldPiggie.caught();
						m_CaughtPiggies.add(aWorldPiggie);
					}
				}
			}
		}
	}

	private boolean isInShip(Piggie pWorldPiggie) {
		// TODO Auto-generated method stub
		int xDistance = this.getX() - pWorldPiggie.getX();
		int yDistance = this.getY() - pWorldPiggie.getY();

		int maxDistance = 50;
		int maxMinusDistance = -50;

		if (xDistance > maxDistance || xDistance < maxMinusDistance) {
			return false;
		}
		if (xDistance < maxDistance || xDistance > maxMinusDistance) {
			if (yDistance > maxDistance || yDistance < maxMinusDistance) {
				return false;
			}
			if (yDistance < maxDistance || yDistance > maxMinusDistance) {
				return true;
			}
		}
		return true;
	}

	/**
	 * @param w
	 */
	public VeganForce(World w) {
		super(w, new ImageIcon(FlyingPiggie.class.getResource("/bilder/TheMicroVeganForce.png")));
		setX(getIcon().getIconWidth() / 2);
		setY(500);
		setEnabled(false);
	}

	@Override
	public void act() {
		GameState myGameState = getWorld().getGameState();
		if (myGameState == GameState.WHILEGAME ) {
			setEnabled(true);
		}
		else {
			setEnabled(false);
		}
	}

}
