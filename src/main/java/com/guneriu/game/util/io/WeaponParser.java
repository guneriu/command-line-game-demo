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
    private List<Weapon> weaponList = new ArrayList<>();
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
    public void parseContent(List<String> lines) {
        for (String line : lines) {
            String[] values = line.split(delimiter);
            String id = values[0];
            String name = values[1];
            Integer damage = Integer.parseInt(values[2]);
            Integer level = Integer.parseInt(values[3]);
            weaponList.add(new Weapon(id, name, damage, level));
        }
    }

    @Override
    public List<Weapon> getContent() {
        return weaponList;
    }

}
