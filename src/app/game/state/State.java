package game.state;

import game.GameExecution;

public class State {
	private StateName name;
	private double startAt;
	private double endAt;

	public State(StateName name) {
		this.name = name;
	}

	public double getStartAt() {
		return this.startAt;
	}

	public double getEndAt() {
		return this.endAt;
	}

	public boolean isActive() {
		return this.name == StateName.ACTIVE;
	}

	public boolean isInactive() {
		return this.name == StateName.INACTIVE;
	}

	public boolean isExploding() {
		return this.name == StateName.EXPLODING;
	}

	public boolean hasAnExplosionJustEnded() {
		return this.name == StateName.EXPLODING && GameExecution.currentTime > this.endAt;
	}

	public void explodeFor(double millisSeconds) {
		this.name = StateName.EXPLODING;
		this.startAt = GameExecution.currentTime;
		this.endAt = GameExecution.currentTime + millisSeconds;
	}

	public void activate() {
		this.name = StateName.ACTIVE;
	}

	public void inactivate() {
		this.name = StateName.INACTIVE;
	}
}
