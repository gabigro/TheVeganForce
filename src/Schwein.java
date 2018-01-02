import javax.swing.ImageIcon;

public class Schwein extends Actor {
	private static int SCHRITTE = 100;
	private static int SCHRITTWEITE = 16;
	private int s = SCHRITTE;

	public Schwein(World w) {
		super(w, new ImageIcon(Schwein.class.getResource("/bilder/piggie.png")));
		setX(w.getWidth() / 2);
		setY(w.getHeight() / 2);
	}

	public void act() {
		int r = 0; // Hilfsvariable für die neue Rotation Hier immer langsamer werden
		if (s > 0) {
			s--;
		} 
		else {
			s = SCHRITTE;
			r = (int) Math.floor(Math.random() * 360);
			setRotation(r);
		}
		double winkel = Math.toRadians(getRotation());
		int x = (int) Math.round(getX() + Math.cos(winkel) * SCHRITTWEITE);
		int y = (int) Math.round(getY() + Math.sin(winkel) * SCHRITTWEITE);

		if (atWorldEdge(x, y) == true) {
			r = (int) Math.floor(Math.random() * 360);
			setRotation(r);
		} 
		else {
			setX(x);
			setY(y);
		}

	}

}
