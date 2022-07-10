package game.background;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Background {
	private List<Star> stars = new ArrayList<>();

	public Background() {
		for (int i = 0; i < 20; i++) { stars.add(new Star(0.070, 3, 3, Color.GRAY)); }
		for (int i = 0; i < 50; i++) { stars.add(new Star(0.045, 2, 2, Color.DARK_GRAY)); }
	}

	public List<Star> getStars() {
		return stars;
	}
}
