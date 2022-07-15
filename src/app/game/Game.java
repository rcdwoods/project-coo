package game;

import game.api.GameLib;
import game.background.Background;
import game.characters.Character;
import game.characters.EnemyManager;
import game.characters.Player;
import game.graficinterface.GraphicInterface;
import game.item.Item;
import game.item.ItemManager;
import game.projectile.Projectile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
	private final Background background;
	private final GraphicInterface graphicInterface;
	private final ItemManager itemManager;
	public static final Player player = new Player();
	public static final EnemyManager enemyManager = new EnemyManager();

	public Game() {
		this.background = new Background();
		this.graphicInterface = new GraphicInterface();
		this.itemManager = new ItemManager();

		start();
	}

	public void updateDisplay() {
		GameExecution.millisSinceLastUpdate = System.currentTimeMillis() - GameExecution.currentTime;
		GameExecution.currentTime = System.currentTimeMillis();
		GameLib.display();
		busyWait(GameExecution.currentTime + 3);
	}

	public void verifyColisions() {
		for (Item item : itemManager.getItems()) { item.verifyColisationWith(player); }
		for (Projectile projectile : enemyManager.getEnemyProjeteis()) { player.verifyColisionWith(projectile.getLocalization()); }
		for (Character enemy : enemyManager.getActiveEnemies()) { player.verifyColisionWith(enemy.getLocalization()); }
		for (Projectile projectile : player.getGunProjeteis()) {
			for (Character enemy : enemyManager.getActiveEnemies()) { enemy.verifyColisionWith(projectile.getLocalization()); }
		}
	}

	public void updateStates() {
		player.updateState();
		for (Item item : itemManager.getItems()) { item.updateState(); }
		for (Projectile projectile : player.getGunProjeteis()) { projectile.updateState(); }
		for (Projectile projectile : enemyManager.getEnemyProjeteis()) { projectile.updateState(); }
		for (Character enemy : enemyManager.getAllEnemies()) { enemy.updateState(); }
	}

	public void activateMovement() {
		player.move();
		for (Character enemy : enemyManager.getActiveEnemies()) { enemy.move(); }
		for (Item item : itemManager.getItems()) { item.move(); }
	}

	public void activateShots() {
		player.shot();
		for (Character enemy : enemyManager.getActiveEnemies()) { enemy.shot(); }
	}

	public void activateEnemies() {
		enemyManager.activateAvailableEnemies();
	}

	public void verifyPlayerBehaviors() {
		player.verifyUserIsOutOfScreen();
		player.verifyControl();
	}

	public void drawEntitiesOnScreen() {
		graphicInterface.drawItems(itemManager.getItems());
		graphicInterface.drawBackground(background);
		graphicInterface.drawCharacterAndBehaviors(player);
		graphicInterface.drawCharactersAndBehaviors(enemyManager.getAllEnemies());
		graphicInterface.drawProjeteis(getAllProjeteis());
	}

	private List<Projectile> getAllProjeteis() {
		Stream<Projectile> playerProjeteis = player.getGunProjeteis().stream();
		Stream<Projectile> enemyProjeteis = enemyManager.getEnemyProjeteis().stream();
		return Stream.concat(playerProjeteis, enemyProjeteis).collect(Collectors.toList());
	}

	private void start() {
		GameExecution.currentTime = System.currentTimeMillis();
		GameLib.initGraphics();
	}

	private void busyWait(long time){
		while(System.currentTimeMillis() < time) Thread.yield();
	}
}
