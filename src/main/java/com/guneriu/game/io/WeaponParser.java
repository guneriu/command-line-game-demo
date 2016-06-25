package com.guneriu.game.io;

import com.guneriu.game.builder.Builder;
import com.guneriu.game.builder.WeaponBuilder;
import com.guneriu.game.model.Weapon;
import com.guneriu.game.model.old.OldWeapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse List of String and creates {@code Order} and {@code Customer} lists
 */
public class WeaponParser implements Parser<Weapon> {
    private List<Weapon> weaponList = new ArrayList<>();
    private Builder<Weapon> weaponBuilder = new WeaponBuilder();
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
            Weapon weapon = weaponBuilder.build(values);
            weaponList.add(weapon);
        }
    }

    @Override
    public List<Weapon> getContent() {
        return weaponList;
    }

}
