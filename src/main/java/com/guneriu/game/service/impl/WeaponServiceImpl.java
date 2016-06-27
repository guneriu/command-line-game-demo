package com.guneriu.game.service.impl;

import com.guneriu.game.model.Weapon;
import com.guneriu.game.service.WeaponService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for accessing {@link Weapon} objects
 *
 * Created by ugur on 26.06.2016.
 */
public class WeaponServiceImpl implements WeaponService {

    private static List<Weapon> weaponList = new ArrayList<>();

    @Override
    public void load(List<Weapon> weapons) {
        weaponList.addAll(weapons);

    }

    @Override
    public Weapon get(String id) {
        return weaponList.stream().filter(weapon -> weapon.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<Weapon> getAll() {
        return weaponList;
    }

    @Override
    public List<Weapon> getByLevel(Integer level) {
        return weaponList.stream().filter(w -> w.getLevel() <= level).collect(Collectors.toList());
    }
}
