package game.background;

import game.api.GameLib;

import java.awt.*;

public class Star {
	public double x;
	public double y;
	public double speed;
	public double count;
	public double width;
	public double height;
	public Color color;

	public Star(double speed, double width, double height, Color color) {
		this.speed = speed;
		this.x = Math.random() * GameLib.WIDTH;
		this.y = Math.random() * GameLib.HEIGHT;
		this.count = 0;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public void move(long delta) {
		this.count += this.speed * delta;
	}
}
