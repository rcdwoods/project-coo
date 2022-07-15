package game.characters;

import game.api.GameLib;
import game.attributes.Appearance;
import game.attributes.Format;
import game.attributes.Localization;
import game.guns.Gun;
import game.guns.StraightGun;
import game.GameExecution;
import game.attributes.Velocity;
import game.attributes.Direction;
import game.projectile.ProjectileFactory;
import game.state.State;
import game.state.StateName;

import java.awt.*;

public class Player extends Character {

	public Player() {
		this.state = new State(StateName.ACTIVE);
		this.localization = new Localization(GameLib.WIDTH / 2F, GameLib.HEIGHT * 0.90);
		this.velocity = new Velocity(0.25);
		this.appearance = new Appearance(Format.ARROW, Color.BLUE, 12);
		this.gun = new StraightGun(Direction.UP, new Velocity(1), ProjectileFactory.ALLY, 100);
	}

	public void verifyControl() {
		if (!state.isActive()) return;
		if (GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) GameExecution.isRunning = false;
	}

	@Override
	public void shot() {
		if (!state.isActive()) return;
		if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) gun.shotFrom(localization);
	}

	@Override
	public void move() {
		if (!state.isActive()) return;
		if (GameLib.iskeyPressed(GameLib.KEY_UP)) localization.moveUp(velocity);
		if (GameLib.iskeyPressed(GameLib.KEY_DOWN)) localization.moveDown(velocity);
		if (GameLib.iskeyPressed(GameLib.KEY_LEFT)) localization.moveLeft(velocity);
		if (GameLib.iskeyPressed(GameLib.KEY_RIGHT)) localization.moveRight(velocity);
	}

	@Override
	public void updateState() {
		if (state.hasAnExplosionJustEnded()) {
			state.activate();
			resetAttributes();
		}
	}

	public void changeGun(Gun gun) {
		this.gun = gun;
	}

	public void verifyUserIsOutOfScreen() {
		if (localization.getX() < 0.0) localization.setX(0.0);
		if (localization.getX() >= GameLib.WIDTH) localization.setX(GameLib.WIDTH - 1F);
		if (localization.getY() < 25.0) localization.setY(25.0);
		if (localization.getY() >= GameLib.HEIGHT - 20) localization.setY(GameLib.HEIGHT - 20F);
	}

	private void resetAttributes() {
		this.gun = new StraightGun(Direction.UP, new Velocity(1), ProjectileFactory.ALLY, 100);
	}
}
