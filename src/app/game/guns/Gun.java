package game.guns;

import game.attributes.Localization;
import game.attributes.Direction;
import game.attributes.Velocity;
import game.projectile.Projectile;

import java.util.ArrayList;
import java.util.List;

public abstract class Gun {
	protected Velocity velocity;
	protected Direction directedTo;
	protected List<Projectile> projeteis = new ArrayList<>();
	protected double angle;
	protected long nextShot;
	protected long reloadTime;
	public abstract void shotFrom(Localization localization);
	public abstract boolean canShot();

	public List<Projectile> getProjeteis() {
		return this.projeteis;
	}
}
