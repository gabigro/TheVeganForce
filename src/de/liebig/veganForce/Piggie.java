package de.liebig.veganForce;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import sun.audio.*;

public class Piggie extends Actor {

	private static final double POS_STEP = 100;
	private int m_id;
	private boolean m_isCaught = false;
	private Clip clip;

	/**
	 * @return the id
	 */
	public int getId() {
		return m_id;
	}

	public Piggie(int pId, World w, Planet pPlanet) {
		super(w, new ImageIcon(Piggie.class.getResource("/bilder/traurig.png")));
		setX(pPlanet.getX() + (int) Math.floor((2 * Math.random() - 1) * POS_STEP));
		setY(pPlanet.getY() + (int) Math.floor((2 * Math.random() - 1) * POS_STEP));
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException except) {
			// TODO Auto-generated catch block
			except.printStackTrace();
		}
		this.m_id= pId ;
	}

	void caught() {
		m_isCaught = true;
		setIcon("/bilder/happy.png");
		try {
			File aResource = new File(
					"/C:/Informatik Q-Phase/Q1 Informatik/eclipseWorkspace/schweinImWeltall_littlemisssunshine/src/sounds/1_person_cheering-Jett_Rifkin-1851518140.wav");
			AudioInputStream aAudioInputStream = AudioSystem.getAudioInputStream(aResource);
			clip.open(aAudioInputStream);
			// Play the audio clip with the audio player class
			clip.start();
		} catch (LineUnavailableException except) {
			// TODO Auto-generated catch block
			except.printStackTrace();
		} catch (IOException except) {
			// TODO Auto-generated catch block
			except.printStackTrace();
		} catch (UnsupportedAudioFileException except) {
			// TODO Auto-generated catch block
			except.printStackTrace();
		}

	}

	boolean isCaught() {
		return m_isCaught;
	}
}
