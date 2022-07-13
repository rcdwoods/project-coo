package game.graficinterface;

import game.api.GameLib;
import game.GameExecution;
import game.attributes.Format;
import game.background.Background;
import game.background.Star;
import game.characters.Character;
import game.item.Item;
import game.projectile.Projectile;

import java.util.List;

public class GraphicInterface {
	public void drawCharactersAndBehaviors(List<Character> characters) {
		for (Character character : characters){ drawCharacterAndBehaviors(character); }
	}

	public void drawCharacterAndBehaviors(Character character) {
		drawExplosion(character);
		drawCharacter(character);
	}

	public void drawBackground(Background background) {
		for(Star star : background.getStars()){
			star.move();
			drawStar(star);
		}
	}

	public void drawItems(List<Item> items) {
		for(Item item : items){ drawItem(item);}
	}

	public void drawProjeteis(List<Projectile> projeteis) {
		for (Projectile projectile : projeteis) { drawProjetil(projectile); }
	}

	private void drawItem(Item item) {
		if (!item.getState().isActive()) return;
		GameLib.setColor(item.getAppearance().getColor());
		GameLib.drawCircle(item.getLocalization().getX(), item.getLocalization().getY(), item.getAppearance().getRadius());
	}
	private void drawProjetil(Projectile projectile) {
		if (projectile.getAppearance().getFormat() == Format.CIRCLE) drawCircleProjetil(projectile);
		if (projectile.getAppearance().getFormat() == Format.LINE) drawLineProjetil(projectile);
	}

	private void drawCharacter(Character character) {
		if (!character.getState().isActive()) return;
		if (character.getAppearance().getFormat() == Format.DIAMOND) drawDiamondCharacter(character);
		if (character.getAppearance().getFormat() == Format.CIRCLE) drawCircleCharacter(character);
		if (character.getAppearance().getFormat() == Format.ARROW) drawArrowCharacter(character);
	}

	private void drawArrowCharacter(Character character) {
		GameLib.setColor(character.getAppearance().getColor());
		GameLib.drawPlayer(character.getLocalization().getX(), character.getLocalization().getY(), character.getAppearance().getRadius());
	}

	private void drawCircleCharacter(Character character) {
		GameLib.setColor(character.getAppearance().getColor());
		GameLib.drawCircle(character.getLocalization().getX(), character.getLocalization().getY(), character.getAppearance().getRadius());
	}

	private void drawDiamondCharacter(Character character) {
		GameLib.setColor(character.getAppearance().getColor());
		GameLib.drawDiamond(character.getLocalization().getX(), character.getLocalization().getY(), character.getAppearance().getRadius());
	}

	private void drawLineProjetil(Projectile projectile) {
		GameLib.setColor(projectile.getAppearance().getColor());
		GameLib.drawLine(projectile.getLocalization().getX(), projectile.getLocalization().getY() - 5, projectile.getLocalization().getX(), projectile.getLocalization().getY() + 5);
		GameLib.drawLine(projectile.getLocalization().getX() - 1, projectile.getLocalization().getY() - 3, projectile.getLocalization().getX() - 1, projectile.getLocalization().getY() + 3);
		GameLib.drawLine(projectile.getLocalization().getX() + 1, projectile.getLocalization().getY() - 3, projectile.getLocalization().getX() + 1, projectile.getLocalization().getY() + 3);
	}

	private void drawCircleProjetil(Projectile projectile) {
		GameLib.setColor(projectile.getAppearance().getColor());
		GameLib.drawCircle(projectile.getLocalization().getX(), projectile.getLocalization().getY(), projectile.getAppearance().getRadius());
	}

	private void drawExplosion(Character character) {
		if(!character.getState().isExploding()) return;
		double alpha = (GameExecution.currentTime - character.getState().getStartAt()) / (character.getState().getEndAt() - character.getState().getStartAt());
		GameLib.drawExplosion(character.getLocalization().getX(), character.getLocalization().getY(), alpha);
	}

	private void drawStar(Star star) {
		GameLib.setColor(star.getAppearance().getColor());
		GameLib.fillRect(star.getLocalization().getX(), (star.getLocalization().getY()) % GameLib.HEIGHT, star.getAppearance().getRadius(), star.getAppearance().getRadius());
	}
}
