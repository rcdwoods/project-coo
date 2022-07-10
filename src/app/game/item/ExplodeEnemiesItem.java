package game.item;

import game.api.GameLib;
import game.Game;
import game.GameExecution;
import game.attributes.Appearance;
import game.attributes.Format;
import game.attributes.Localization;
import game.attributes.Velocity;
import game.characters.Character;
import game.state.State;
import game.state.StateName;

import java.awt.*;

public class ExplodeEnemiesItem extends Item {

	public ExplodeEnemiesItem() {
		state = new State(StateName.INACTIVE);
		localization = new Localization(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10);
		velocity = new Velocity(0.100);
		appearance = new Appearance(Format.CIRCLE, Color.magenta, 5);
		nextAppear = GameExecution.currentTime + 10000;
		appearDelay = 10000;
	}

	@Override
	public void applyEffect() {
		Game.enemyManager.getAllEnemies().forEach(Character::explode);
	}
}
