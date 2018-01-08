package de.liebig.veganForce;

import javax.swing.ImageIcon;

public class BeforeGame extends Actor {

	public BeforeGame(World pW) {
		super(pW, new ImageIcon(BeforeGame.class.getResource("/bilder/TheVeganHelp.jpeg")));
		setX(1000);
		setY(500);
		setEnabled(true);
	}

	public BeforeGame(World pW, ImageIcon pI) {
		super(pW, pI);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void act() {
		GameState myGameState = getWorld().getGameState();
		if (myGameState == GameState.BEFOREGAME) {
			setEnabled(true);
		} else {
			setEnabled(false);
		}
	}

}
