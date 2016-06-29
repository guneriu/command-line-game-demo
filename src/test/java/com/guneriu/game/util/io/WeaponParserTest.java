package com.guneriu.game.util.io;

import com.guneriu.game.model.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ugur on 29.06.2016.
 */
public class WeaponParserTest {

    private WeaponParser weaponParser;

    private String delimiter = "#";

    private String sampleWeaponData = "4#Hidden Blade#20#3";

    @Before
    public void setUp() throws Exception {
        weaponParser = new WeaponParser(delimiter);
    }

    @Test
    public void givenOneWeapon_whenParseContent_thenReturnWeapon() throws Exception {
        List<Weapon> weapons = weaponParser.parseContent(Arrays.asList(sampleWeaponData));
        Assert.assertEquals(1, weapons.size());
    }

    @Test
    public void givenTwoWeapons_whenParseContent_thenReturn2Weapon() throws Exception {
        String sampleArrowWeaponData = "3#Arrow#10#2";
        List<Weapon> weapons = weaponParser.parseContent(Arrays.asList(sampleWeaponData, sampleArrowWeaponData));
        Assert.assertEquals(2, weapons.size());
    }

    @Test
    public void givenOneWeapon_whenParseContent_thenCreateWeaponAsGiven() throws Exception {
        List<Weapon> weapons = weaponParser.parseContent(Arrays.asList(sampleWeaponData));
        Weapon weapon = weapons.get(0);
        String[] weaponData = sampleWeaponData.split(delimiter);
        Assert.assertEquals(weaponData[0], weapon.getId());
        Assert.assertEquals(weaponData[1], weapon.getName());
        Assert.assertEquals(Integer.valueOf(weaponData[2]), weapon.getDamage());
        Assert.assertEquals(Integer.valueOf(weaponData[3]), weapon.getLevel());
    }

}