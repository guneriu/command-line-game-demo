package com.guneriu.game.service.impl;

import com.guneriu.game.model.Area;
import com.guneriu.game.model.Direction;
import com.guneriu.game.model.Weapon;
import com.guneriu.game.service.WeaponService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by ugur on 29.06.2016.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class WeaponServiceImplTest {

    private WeaponService weaponService;
    private Weapon sampleWeapon;

    @Before
    public void setUp() throws Exception {
        this.weaponService = new WeaponServiceImpl();
        sampleWeapon = new Weapon("1", "test weapon", 20, 5);
    }

    @Test
    public void givenWeapons_whenGet_thenReturnWeaponWithGivenId() {
        Weapon expectedWeapon = new Weapon("2", "sample Dagger", 20, 1);
        weaponService.add(Arrays.asList(sampleWeapon, expectedWeapon));

        Optional<Weapon> actualWeapon = weaponService.get(expectedWeapon.getId());
        Assert.assertTrue(actualWeapon.isPresent());
        Assert.assertEquals(expectedWeapon.getId(), actualWeapon.get().getId());
    }

    @Test
    public void givenTwoWeaponWithDifferentLevel_whenGetByLevel_thenReturnProperOne() {
        int level = 3;
        Weapon expectedWeapon = new Weapon("2", "sample Dagger", 20, level);
        weaponService.add(Arrays.asList(sampleWeapon, expectedWeapon));

        List<Weapon> weaponList = weaponService.getByLevel(level);
        Assert.assertEquals(1, weaponList.size());
        Assert.assertEquals(expectedWeapon, weaponList.get(0));


    }
}