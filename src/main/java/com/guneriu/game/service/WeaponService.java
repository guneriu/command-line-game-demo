package com.guneriu.game.service;

import com.guneriu.game.model.Weapon;

import java.util.List;
import java.util.Optional;

/**
 * Created by ugur on 27.06.2016.
 */
public interface WeaponService {

    void add(List<Weapon> weapons);

    Optional<Weapon> get(String id);

    List<Weapon> getByLevel(Integer level);
}
