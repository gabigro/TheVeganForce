import javax.swing.ImageIcon;

public class Actor {
	private int rotation;
	private int x;
	private int y;
	private ImageIcon icon;
	private World world;

	public Actor(World w) {
		rotation = 0;
		x = 0;
		y = 0;
		icon = new ImageIcon(getClass().getResource("/bilder/default.png"));
		;
		world = w;
	}

	public Actor(World w, ImageIcon i) {
		rotation = 0;
		x = 0;
		y = 0;
		icon = i;
		world = w;
	}

	/**
	 * Act - empty method.
	 */
	public void act() {
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int z) {
		rotation = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int xx) {
		x = xx;
	}

	public int getY() {
		return y;
	}

	public void setY(int yy) {
		y = yy;
	}

	/**
	 * Drehe 'winkel' Grad nach rechts.
	 */
	public void turn(int winkel) {
		setRotation(getRotation() + winkel);
	}

	/**
	 * Prüfe ob am Rand der Welt. Gebe true zurück, wenn ja.
	 */
	public boolean atWorldEdge(int xx, int yy) {
		if (xx < 20 || xx > getWorld().getWidth() - 20)
			return true;
		if (yy < 20 || yy > getWorld().getHeight() - 20)
			return true;
		return false;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(String url) {
		icon = new ImageIcon(getClass().getResource(url));
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World w) {
		world = w;
	}

}