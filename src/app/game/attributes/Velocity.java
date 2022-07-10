package game.attributes;

public class Velocity {
	private double x;
	private double y;

	public Velocity(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Velocity(double value) {
		this.x = value;
		this.y = value;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
