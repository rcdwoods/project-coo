package game.characters;

import game.api.GameLib;
import game.attributes.Appearance;
import game.attributes.Format;
import game.attributes.Localization;
import game.GameExecution;
import game.attributes.Velocity;
import game.attributes.Direction;
import game.guns.StraightGun;
import game.projectile.ProjectileFactory;
import game.state.State;
import game.state.StateName;

import java.awt.*;

public class EnemyThree extends Character {
	private double angle;
	private double rotationVelocity;

	public EnemyThree() {
		long gunReloadTime = (long) (200 + Math.random() * 500);
		this.state = new State(StateName.INACTIVE);
		this.appearance = new Appearance(Format.DIAMOND, Color.ORANGE, 20);
		this.localization = new Localization(0, 0);
		this.gun = new StraightGun(Direction.DOWN, new Velocity(0.45), ProjectileFactory.ENEMY, gunReloadTime);
	}

	public void activate() {
		localization.setX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
		localization.setY(-10.0);
		velocity = new Velocity(0.20 + Math.random() * 0.15);
		angle = (3 * Math.PI) / 2;
		rotationVelocity = 0.0;
		state.activate();
	}

	public void updateState() {
		if (state.hasAnExplosionJustEnded() || hasLeaveScreen()) state.inactivate();
		if (!state.isActive()) return;

		double previousY = localization.getY();
		double threshold = GameLib.HEIGHT * 0.30;

		updateLocalization();

		if (previousY < threshold && localization.getY() >= threshold) modifyMovimentDirection();
		if (this.gun.canShot()) gun.shotFrom(localization);
	}

	private void modifyMovimentDirection() {
		this.rotationVelocity = localization.getX() < GameLib.WIDTH / 2F ? 0.0005 : -0.0005;
	}

	private void updateLocalization() {
		localization.setX(localization.getX() + velocity.getX() * Math.cos(angle) * GameExecution.millisSinceLastUpdate);
		localization.setY(localization.getY() + velocity.getY() * Math.sin(angle) * GameExecution.millisSinceLastUpdate * (-1.0));
		angle += rotationVelocity * GameExecution.millisSinceLastUpdate;
	}

	public boolean hasLeaveScreen() {
		return localization.getY() > GameLib.HEIGHT + 10;
	}
}
