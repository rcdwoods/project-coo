package game.guns;

import game.attributes.Localization;
import game.attributes.Direction;
import game.attributes.Velocity;
import game.projectile.ProjectileFactory;
import game.projectile.Projectile;
import game.GameExecution;

import java.util.List;
import java.util.stream.Collectors;

public class TripleAleatoryGun extends Gun {

	public TripleAleatoryGun(Direction direction, Velocity velocity, ProjectileFactory projectileFactory) {
		this.velocity = velocity;
		this.directedTo = direction;

		for (int i = 0; i < 10; i++) {
			this.projeteis.add(projectileFactory.factory());
		}
	}

	@Override
	public void shotFrom(Localization localization) {
		if (!hasAvailableProjeteis()) return;
		double middle = Math.PI / 2;
		double[] angles = {middle + 0.10, middle, middle - 0.10};
		List<Projectile> inactiveProjeteis = projeteis.stream().filter(it -> it.getState().isInactive()).collect(Collectors.toList()).subList(0, 3);
		for (Projectile projectile : inactiveProjeteis) {
			double angle = angles[inactiveProjeteis.indexOf(projectile)] + Math.random() * Math.PI / 6 - Math.PI / 12;
			projectile.getLocalization().setX(localization.getX());
			projectile.getLocalization().setY(localization.getY() - 2);
			projectile.setVelocity(new Velocity(Math.cos(angle) * velocity.getX(), Math.sin(angle) * velocity.getY() * directedTo.getValue()));
			projectile.getState().activate();
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
