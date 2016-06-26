package com.guneriu.game.util.provider;

import com.guneriu.game.model.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ugur on 26.06.2016.
 */
public class WeaponProvider {

    private static List<Weapon> weaponList = new ArrayList<>();

    private WeaponProvider() {
    }


    public static void load(List<Weapon> weapons) {
        weaponList.addAll(weapons);

    }

    public static Weapon get(String id) {
        return weaponList.stream().filter(weapon -> weapon.getId().equals(id)).findFirst().get();
    }

    public static List<Weapon> getAll() {
        return weaponList;
    }

    public static List<Weapon> getByLevel(Integer level) {
        return weaponList.stream().filter(w -> w.getLevel() <= level).collect(Collectors.toList());
    }
}
