package com.guneriu.game.io;

import com.guneriu.game.model.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse List of String and creates {@code Order} and {@code Customer} lists
 */
public class WeaponParser implements Parser<Weapon> {
    private List<Weapon> weaponList = new ArrayList<>();
    private String delimeter;

    /**
     * uses given delimiter to split line
     *
     * @param delimeter
     */
    public WeaponParser(String delimeter) {
        this.delimeter = delimeter;
    }

    /**
     * uses {@code List<String>} and parses {@code Customer} and {@code Order} objects
     */
    @Override
    public void parseContent(List<String> lines) {
        for (String line : lines) {
            String[] values = line.split(delimeter);
            Integer id = Integer.parseInt(values[0]);
            String name = values[1];
            Integer damage = Integer.parseInt(values[2]);
            Integer level = Integer.parseInt(values[3]);
            Integer price = Integer.parseInt(values[4]);
            weaponList.add(new Weapon(id, name, damage, level, price));
        }
    }

    @Override
    public List<Weapon> getContent() {
        return weaponList;
    }

}
