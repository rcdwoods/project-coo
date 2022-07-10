package game.characters;

import game.projectile.Projectile;
import game.GameExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnemyManager {
	private List<EnemyOne> enemiesOne;
	private List<EnemyTwo> enemiesTwo;
	private List<EnemyThree> enemiesThree;
	private long nextEnemyOneMustAppearAt;
	private long nextEnemyTwoMustAppearAt;
	private long nextEnemyThreeMustAppearAt;
	private int enemiesTwoOnScreen;

	public EnemyManager() {
		this.enemiesTwo = new ArrayList<>();
		this.enemiesOne = new ArrayList<>();
		this.enemiesThree = new ArrayList<>();
		this.nextEnemyOneMustAppearAt = System.currentTimeMillis() + 2000;
		this.nextEnemyTwoMustAppearAt = System.currentTimeMillis() + 7000;
		this.nextEnemyThreeMustAppearAt = System.currentTimeMillis() + 14000;
		this.enemiesTwoOnScreen = 0;
		createEnemies();
	}

	public void activateAvailableEnemies() {
		activateEnemyOne();
		activateEnemyTwo();
		activateEnemyThree();
	}

	private void activateEnemyOne() {
		Optional<EnemyOne> firstInactiveEnemy = getFirstInactiveEnemyOne();
		if (GameExecution.currentTime > nextEnemyOneMustAppearAt && firstInactiveEnemy.isPresent()) {
			firstInactiveEnemy.get().activate();
			nextEnemyOneMustAppearAt = GameExecution.currentTime + 500;
		}
	}

	private void activateEnemyThree() {
		Optional<EnemyThree> firstInactiveEnemy = enemiesThree.stream().filter(it -> it.state.isInactive()).findFirst();
		if (GameExecution.currentTime > nextEnemyThreeMustAppearAt && firstInactiveEnemy.isPresent()) {
			firstInactiveEnemy.get().activate();
			nextEnemyThreeMustAppearAt = GameExecution.currentTime + 500;
		}
	}

	private void activateEnemyTwo() {
		Optional<EnemyTwo> firstInactiveEnemy = getFirstInactiveEnemyTwo();
		if (GameExecution.currentTime > nextEnemyTwoMustAppearAt && firstInactiveEnemy.isPresent()) {
			firstInactiveEnemy.get().activate();
			enemiesTwoOnScreen++;

			if (enemiesTwoOnScreen < 10) {
				nextEnemyTwoMustAppearAt = GameExecution.currentTime + 120;
			} else {
				enemiesTwoOnScreen = 0;
				nextEnemyTwoMustAppearAt = (long) (GameExecution.currentTime + 3000 + Math.random() * 3000);
			}
		}
	}

	private Optional<EnemyOne> getFirstInactiveEnemyOne() {
		return enemiesOne.stream().filter(it -> it.state.isInactive()).findFirst();
	}

	private Optional<EnemyTwo> getFirstInactiveEnemyTwo() {
		return enemiesTwo.stream().filter(it -> it.state.isInactive()).findFirst();
	}

	public List<Character> getAllEnemies() {
		Stream<Character> enemiesOneAndTwo = Stream.concat(enemiesOne.stream(), enemiesTwo.stream());
		return Stream.concat(enemiesOneAndTwo, enemiesThree.stream()).collect(Collectors.toList());
	}

	public List<Character> getActiveEnemies() {
		return getAllEnemies().stream()
			.filter(it -> it.state.isActive())
			.collect(Collectors.toList());
	}

	public List<Projectile> getEnemyProjeteis() {
		return getAllEnemies().stream()
			.flatMap(it -> it.gun.getProjeteis().stream())
			.collect(Collectors.toList());
	}

	private void createEnemies() {
		for (int i = 0; i < 10; i++) {
			enemiesOne.add(new EnemyOne());
			enemiesTwo.add(new EnemyTwo());
		}
		for (int i = 0; i < 3; i++) { enemiesThree.add(new EnemyThree()); }
	}
}
