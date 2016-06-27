package com.guneriu.game.service;

import com.guneriu.game.model.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for accessing {@link Weapon} objects
 *
 * Created by ugur on 26.06.2016.
 */
public class WeaponService {

    private static List<Weapon> weaponList = new ArrayList<>();

    private WeaponService() {
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
