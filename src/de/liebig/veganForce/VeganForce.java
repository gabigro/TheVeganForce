package de.liebig.veganForce;

import java.lang.reflect.GenericArrayType;
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
		viewshift();
		piggieEinsammeln();
		moveCaughPiggies();
		return true;
	}

	private void viewshift() {

		int xViewStep = STEP + getIcon().getIconWidth();

		int myMaxX = getWorld().getxOfset() + getWorld().getViewWidth() - xViewStep;
		if (getX() >= myMaxX && (getX() < getWorld().getWidth() - xViewStep)) {
			getWorld().setxOfset(getWorld().getxOfset() + STEP);
		}
		if (getX() >= getWorld().getWidth() - xViewStep) {
			setX(getWorld().getWidth() - xViewStep);
		}
		int myMinX = getWorld().getxOfset() + xViewStep;
		if (getX() <= myMinX && getX() > xViewStep) {
			getWorld().setxOfset(getWorld().getxOfset() - STEP);
		}
		if (getX() <= xViewStep) {
			setX(xViewStep);
		}

		// YYYY

		int yViewStep = STEP + getIcon().getIconHeight();

		int myMaxY = getWorld().getyOfset() + getWorld().getViewHeight() - yViewStep;
		if (getY() >= myMaxY && (getY() < getWorld().getHeight() - yViewStep)) {
			getWorld().setyOfset(getWorld().getyOfset() + STEP);
		}
		if (getY() >= getWorld().getHeight() - yViewStep) {
			setY(getWorld().getHeight() - yViewStep);
		}
		int myMinY = getWorld().getyOfset() + yViewStep;
		if (getY() <= myMinY && getY() > yViewStep) {
			getWorld().setyOfset(getWorld().getyOfset() - STEP);
		}
		if (getY() <= yViewStep) {
			setY(yViewStep);
		}
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
			if (aWorldActor instanceof Guards) {
				if (isInShip(aWorldActor)) {
					getWorld().setGameState(GameState.GAMEOVER);
					break;				}
			}
		}
	}

	private boolean isInShip(Actor pWorldSpecifiedActor) {
		// TODO Auto-generated method stub
		int xDistance = this.getX() - pWorldSpecifiedActor.getX();
		int yDistance = this.getY() - pWorldSpecifiedActor.getY();

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
		setX(200);
		setY(500);
		setEnabled(false);
	}

	@Override
	public void act() {
		GameState myGameState = getWorld().getGameState();
		if (myGameState == GameState.WHILEGAME) {
			setEnabled(true);
		} else {
			setEnabled(false);
		}
	}

}
