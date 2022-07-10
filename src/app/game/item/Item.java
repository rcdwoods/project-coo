package game.item;

import game.api.GameLib;
import game.GameExecution;
import game.attributes.Appearance;
import game.attributes.Localization;
import game.attributes.Velocity;
import game.characters.Player;
import game.state.State;


public abstract class Item {
	protected State state;
	protected Localization localization;
	protected Velocity velocity;
	protected Appearance appearance;
	protected long nextAppear;
	protected long appearDelay;

	public abstract void applyEffect();

	public State getState() {
		return state;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	public Localization getLocalization() {
		return localization;
	}

	public void updateState() {
		if (GameExecution.currentTime > nextAppear) state.activate();
		if (localization.getY() >= GameLib.HEIGHT) {
			state.inactivate();
			nextAppear = GameExecution.currentTime + appearDelay;
			localization.setY(-10);
		}
		updateLocalization();
	}

	private void updateLocalization() {
		if (!state.isActive()) return;
		localization.setY(localization.getY() + velocity.getY() * GameExecution.millisSinceLastUpdate);
	}

	public void verifyColisationWith(Player player) {
		if (!player.getState().isActive() || !state.isActive()) return;
		double distance = this.localization.getDistanceFrom(player.getLocalization());

		if(distance < player.getAppearance().getRadius()){
			applyEffect();
			state.inactivate();
			nextAppear = GameExecution.currentTime + appearDelay;
			localization.setY(-10);
		}
	}
}
