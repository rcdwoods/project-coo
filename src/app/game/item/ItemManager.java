package game.item;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
	private List<Item> improveGunItems;

	public ItemManager() {
		improveGunItems = new ArrayList<>();
		improveGunItems.add(new ImproveGunItem());
		improveGunItems.add(new ExplodeEnemiesItem());
	}

	public List<Item> getItems() {
		return improveGunItems;
	}
}
