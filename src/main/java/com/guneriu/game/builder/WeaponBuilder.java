package com.guneriu.game.builder;

import com.guneriu.game.model.Weapon;

/**
 * 
 * Builder class for {@code Customer} 
 *
 */
public class WeaponBuilder implements Builder<Weapon> {

	/**
	 * creates {@code Weapon} from string array
	 * 
	 * @param values string array containing values
	 * @return {@code Weapon}
	 */
	@Override
	public Weapon build(String[] values) {
		Integer id = Integer.parseInt(values[0]);
		String name = values[1];
		Integer damage = Integer.parseInt(values[2]);
		Integer level = Integer.parseInt(values[3]);
		Integer price = Integer.parseInt(values[4]);
		return new Weapon(id, name, damage, level, price);
	}
	
}
