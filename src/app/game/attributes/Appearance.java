package game.attributes;

import java.awt.*;

public class Appearance {
	private final Format format;
	private final Color color;
	private final int radius;

	public Appearance(Format format, Color color, int radius) {
		this.format = format;
		this.color = color;
		this.radius = radius;
	}

	public Format getFormat() {
		return format;
	}

	public Color getColor() {
		return color;
	}

	public int getRadius() {
		return radius;
	}
}
