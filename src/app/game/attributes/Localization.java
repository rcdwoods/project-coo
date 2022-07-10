package game.attributes;

import game.GameExecution;
import game.api.GameLib;

public class Localization {
	private double x;
	private double y;

	public Localization(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDistanceFrom(Localization localization) {
		double distanceX = this.getX() - localization.x;
		double distanceY = this.getY() - localization.y;
		return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
	}

	public boolean isOutOfScreen() {
		return this.y > GameLib.HEIGHT || this.y < 0;
	}

	public void moveUp(Velocity velocity) {
		this.y -= GameExecution.millisSinceLastUpdate * velocity.getY();
	}

	public void moveDown(Velocity velocity) {
		this.y += GameExecution.millisSinceLastUpdate * velocity.getY();
	}

	public void moveLeft(Velocity velocity) {
		this.x -= GameExecution.millisSinceLastUpdate * velocity.getX();
	}

	public void moveRight(Velocity velocity) {
		this.x += GameExecution.millisSinceLastUpdate * velocity.getX();
	}
}
