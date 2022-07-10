package game.guns;

import game.attributes.Localization;
import game.attributes.Direction;
import game.attributes.Velocity;
import game.projectile.Projectile;
import game.GameExecution;
import game.projectile.ProjectileFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TripleStraightGun extends Gun {

	public TripleStraightGun(Direction direction, Velocity velocity, ProjectileFactory projectileFactory, long reloadTime) {
		this.directedTo = direction;
		this.velocity = velocity;
		this.reloadTime = reloadTime;

		for (int i = 0; i < 50; i++) { this.projeteis.add(projectileFactory.factory()); }
	}
	@Override
	public void shotFrom(Localization localization) {
		if (!hasAvailableProjeteis() || !canShot()) return;
		double meio = Math.PI / 2;
		double[] angles = {meio + 0.10, meio, meio - 0.10};
		List<Projectile> inactiveProjeteis = projeteis.stream().filter(it -> it.getState().isInactive()).collect(Collectors.toList()).subList(0, 3);
		for (Projectile projectile : inactiveProjeteis) {
			double angle = angles[inactiveProjeteis.indexOf(projectile)];

			projectile.getLocalization().setX(localization.getX());
			projectile.getLocalization().setY(localization.getY());
			projectile.setVelocity(new Velocity(Math.cos(angle) * velocity.getX(), Math.sin(angle) * velocity.getY() * this.directedTo.getValue()));
			projectile.getState().activate();
			nextShot = GameExecution.currentTime + reloadTime;
		}
	}

	@Override
	public boolean canShot() {
		return GameExecution.currentTime > this.nextShot;
	}

	private boolean hasAvailableProjeteis() {
		return this.projeteis.stream().filter(it -> it.getState().isInactive()).count() >= 3;
	}
}
