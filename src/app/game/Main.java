package game;

import java.util.stream.Stream;

public class Main {

	public static void main(String [] args){
		Game game = new Game();

		while(GameExecution.isRunning){
			game.activateEnemies();
			game.verifyPlayerBehaviors();
			game.verifyColisions();
			game.updateStates();
			game.drawEntitiesOnScreen();
			game.updateDisplay();
		}

		System.exit(0);
	}
}
