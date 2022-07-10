package game.projectile;

import game.attributes.Appearance;
import game.attributes.Format;
import game.attributes.Localization;

import java.awt.*;

public enum ProjectileFactory {
	ALLY {
		@Override
		public Projectile factory() {
			return new Projectile(new Localization(0, 0), new Appearance(Format.LINE, Color.GREEN, 2));
		}
	},
	ENEMY {
		@Override
		public Projectile factory() {
			return new Projectile(new Localization(0, 0), new Appearance(Format.CIRCLE, Color.RED, 2));
		}
	};

	public abstract Projectile factory();
}
