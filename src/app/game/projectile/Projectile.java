package game.projectile;

import game.attributes.Appearance;
import game.attributes.Localization;
import game.GameExecution;
import game.attributes.Velocity;
import game.state.State;
import game.state.StateName;

public class Projectile {
	private State state;
	private Localization localization;
	private Velocity velocity;
	private Appearance appearance;

	public Projectile(Localization localization, Appearance appearance) {
		this.state = new State(StateName.INACTIVE);
		this.velocity = new Velocity(0);
		this.localization = localization;
		this.appearance = appearance;
	}

	public Projectile() {
		this.velocity = new Velocity(0);
		this.localization = new Localization(0, 0);
	}

	public void updateState() {
		if (!state.isActive()) return;
		if (hasLeaveScreen()) {
			state.inactivate();
			localization.setY(0);
		} else {
			localization.setX(localization.getX() + velocity.getX() * GameExecution.millisSinceLastUpdate);
			localization.setY(localization.getY() + velocity.getY() * GameExecution.millisSinceLastUpdate);
		}
	}

	public State getState() {
		return state;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	public Localization getLocalization() {
		return localization;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	private boolean hasLeaveScreen() {
		return localization.isOutOfScreen();
	}
}
