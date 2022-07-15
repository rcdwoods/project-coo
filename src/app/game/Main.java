package game;

public class Main {

	public static void main(String [] args){
		Game game = new Game();

		while(GameExecution.isRunning){
			game.verifyPlayerBehaviors();
			game.verifyColisions();
			game.activateEnemies();
			game.activateShots();
			game.activateMovement();
			game.updateStates();
			game.drawEntitiesOnScreen();
			game.updateDisplay();
		}

		System.exit(0);
	}
}
