package game.characters;

import game.attributes.Appearance;
import game.attributes.Localization;
import game.attributes.Velocity;
import game.projectile.Projectile;
import game.guns.Gun;
import game.state.State;

import java.util.List;


public abstract class Character {
	protected State state;
	protected Localization localization;
	protected Velocity velocity;
	protected Appearance appearance;
	protected Gun gun;

	public abstract void updateState();

	public void verifyColisionWith(Localization localization) {
		if (!state.isActive()) return;
		if (this.localization.getDistanceFrom(localization) < appearance.getRadius() * 2) explode();
	}

	public void explode() {
		state.explodeFor(getExplosionDelayByInstanceType());
	}

	private double getExplosionDelayByInstanceType() {
		if (this instanceof Player) return 2000;
		return 500;
	}

	public List<Projectile> getGunProjeteis() {
		return this.gun.getProjeteis();
	}

	public Localization getLocalization() {
		return this.localization;
	}

	public State getState() {
		return this.state;
	}

	public Appearance getAppearance() {
		return appearance;
	}
}
