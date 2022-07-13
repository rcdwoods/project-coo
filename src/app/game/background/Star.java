package game.background;

import game.GameExecution;
import game.api.GameLib;
import game.attributes.Appearance;
import game.attributes.Format;
import game.attributes.Localization;
import game.attributes.Velocity;

import java.awt.*;

public class Star {
	private final Localization localization;
	private final Velocity velocity;
	private final Appearance appearance;

	public Star(double speed, int radius, Color color) {
		this.velocity = new Velocity(speed);
		this.localization = new Localization(Math.random() * GameLib.WIDTH, Math.random() * GameLib.HEIGHT);
		this.appearance = new Appearance(Format.CIRCLE, color, radius);
	}

	public Localization getLocalization() {
		return localization;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	public void move() {
		localization.setY(localization.getY() + this.velocity.getY() * GameExecution.millisSinceLastUpdate);
	}
}
