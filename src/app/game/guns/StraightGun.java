package game.guns;

import game.attributes.Localization;
import game.attributes.Direction;
import game.projectile.Projectile;
import game.GameExecution;
import game.attributes.Velocity;
import game.projectile.ProjectileFactory;

public class StraightGun extends Gun {

	public StraightGun(Direction direction, Velocity velocity, ProjectileFactory projectileFactory, long reloadTime) {
		this.reloadTime = reloadTime;
		this.directedTo = direction;
		this.velocity = velocity;
		this.angle = (3 * Math.PI) / 2;

		for (int i = 0; i < 10; i++) { projeteis.add(projectileFactory.factory()); }
	}

	@Override
	public void shotFrom(Localization localization) {
		if (!hasAvailableProjetil() || !canShot()) return;
		Projectile inactiveProjectile = projeteis.stream().filter(it -> it.getState().isInactive()).findFirst().get();
		inactiveProjectile.getLocalization().setX(localization.getX());
		inactiveProjectile.getLocalization().setY(localization.getY());
		inactiveProjectile.setVelocity(new Velocity(Math.cos(angle) * velocity.getX(), -Math.sin(angle) * velocity.getY() * directedTo.getValue()));
		inactiveProjectile.getState().activate();
		this.nextShot = GameExecution.currentTime + this.reloadTime;
	}

	private boolean hasAvailableProjetil() {
		return projeteis.stream().anyMatch(it -> it.getState().isInactive());
	}

	@Override
	public boolean canShot() {
		return GameExecution.currentTime > this.nextShot;
	}
}
