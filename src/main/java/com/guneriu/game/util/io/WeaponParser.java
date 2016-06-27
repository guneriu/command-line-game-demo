package com.guneriu.game.util.io;

import com.guneriu.game.model.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse List of String and creates {@link Weapon} lists
 *
 * Created by ugur on 25.06.2016.
 */
public class WeaponParser implements Parser<Weapon> {
    private String delimiter;

    /**
     * uses given delimiter to split line
     *
     * @param delimiter
     */
    public WeaponParser(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * uses {@link List<String>} and parses {@link Weapon} objects
     */
    @Override
    public List<Weapon> parseContent(List<String> lines) {
        List<Weapon> weaponList = new ArrayList<>();
        for (String line : lines) {
            String[] weaponData = line.split(delimiter);
            String id = weaponData[0];
            String name = weaponData[1];
            Integer damage = Integer.parseInt(weaponData[2]);
            Integer level = Integer.parseInt(weaponData[3]);
            weaponList.add(new Weapon(id, name, damage, level));
        }

        return weaponList;
    }

}
