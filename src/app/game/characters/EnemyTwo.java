package game.characters;

import game.api.GameLib;
import game.attributes.Appearance;
import game.attributes.Format;
import game.attributes.Localization;
import game.attributes.Velocity;
import game.attributes.Direction;
import game.projectile.ProjectileFactory;
import game.GameExecution;
import game.guns.TripleAleatoryGun;
import game.state.State;
import game.state.StateName;

import java.awt.*;

public class EnemyTwo extends Character {
	private double angle;
	private double rotationVelocity;

	public EnemyTwo() {
		this.state = new State(StateName.INACTIVE);
		this.localization = new Localization(0, 0);
		this.appearance = new Appearance(Format.DIAMOND, Color.MAGENTA, 12);
		this.gun = new TripleAleatoryGun(Direction.DOWN, new Velocity(0.30), ProjectileFactory.ENEMY);
	}

	public void activateOnX(double x) {
		localization.setX(x);
		localization.setY(-10.0);
		velocity = new Velocity(0.42);
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
		if (mustShotNow()) gun.shotFrom(localization);
	}

	private void updateLocalization() {
		localization.setX(localization.getX() + velocity.getX() * Math.cos(angle) * GameExecution.millisSinceLastUpdate);
		localization.setY(localization.getY() + velocity.getY() * Math.sin(angle) * GameExecution.millisSinceLastUpdate * (-1.0));
		angle += rotationVelocity * GameExecution.millisSinceLastUpdate;
	}

	private void modifyMovimentDirection() {
		rotationVelocity = localization.getX() < GameLib.WIDTH / 2F ? 0.003 : -0.003;
	}

	private boolean hasLeaveScreen() {
		return localization.getX() < -10 || localization.getX() > GameLib.WIDTH + 10;
	}

	private boolean mustShotNow() {
		if (rotationVelocity > 0 && Math.abs(angle - 3 * Math.PI) < 0.05) {
			rotationVelocity = 0.0;
			angle = 3 * Math.PI;
			return true;
		}

		if (rotationVelocity < 0 && Math.abs(angle) < 0.05) {
			rotationVelocity = 0.0;
			angle = 0.0;
			return true;
		}
		return false;
	}
}
