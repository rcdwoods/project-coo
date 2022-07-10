package game.item;

import game.api.GameLib;
import game.Game;
import game.GameExecution;
import game.attributes.Appearance;
import game.attributes.Direction;
import game.attributes.Format;
import game.attributes.Localization;
import game.attributes.Velocity;
import game.guns.TripleStraightGun;
import game.projectile.ProjectileFactory;
import game.state.State;
import game.state.StateName;

import java.awt.*;

public class ImproveGunItem extends Item {

	public ImproveGunItem() {
		state = new State(StateName.INACTIVE);
		localization = new Localization(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10);
		velocity = new Velocity(0.100);
		appearance = new Appearance(Format.CIRCLE, Color.YELLOW, 5);
		nextAppear = GameExecution.currentTime + 5000;
		appearDelay = 10000;
	}

	@Override
	public void applyEffect() {
		Game.player.changeGun(new TripleStraightGun(Direction.UP, new Velocity(1), ProjectileFactory.ALLY, 100));
	}
}
