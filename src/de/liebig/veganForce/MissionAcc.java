package de.liebig.veganForce;

import javax.swing.ImageIcon;

public class MissionAcc extends Actor {

	public MissionAcc(World pW) {
		super(pW, new ImageIcon(Planet.class.getResource("/bilder/MissionAcc.jpeg")));
		setX(1000);
		setY(500);
		setEnabled(false);
	}

	@Override
	public void act() {
		GameState myGameState = getWorld().getGameState();
		if (myGameState == GameState.MISSIONACC) {
			setEnabled(true);
			getWorld().setxOfset(0);
			getWorld().setyOfset(0);
		} else {
			setEnabled(false);
		}
	}
}
