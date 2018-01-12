package de.liebig.veganForce;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class GameOver extends Actor {

	public GameOver(World w) {

		super(w, new ImageIcon(Planet.class.getResource("/bilder/Gameover.jpg")));
		setX(1000);
		setY(500);
		setEnabled(false);
	}

	
	@Override
	public void act() {
		GameState myGameState = getWorld().getGameState();
		if (myGameState == GameState.GAMEOVER) {
			setEnabled(true);
			getWorld().setxOfset(0);
			getWorld().setyOfset(0);
		} else {
			setEnabled(false);
		}
	}

}
