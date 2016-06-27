package com.guneriu.game.service;

import com.guneriu.game.model.Weapon;

import java.util.List;

/**
 * Created by ugur on 27.06.2016.
 */
public interface WeaponService {
    void load(List<Weapon> weapons);

    Weapon get(String id);

    List<Weapon> getAll();

    List<Weapon> getByLevel(Integer level);
}
