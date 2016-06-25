package com.guneriu.game.story;

import com.guneriu.game.model.Hero;
import com.guneriu.game.model.old.OldWeapon;

import java.util.Scanner;
import java.util.function.BiFunction;


/**
 * Created by ugur on 24.06.2016.
 */
public enum StoryEnum {
    START_GAME("Welcome. \n What is your Name.", (scanner, hero) -> {
        hero.setName(scanner.next());
        return hero;
    }),
    CHOOSE_A_WEAPON("Choose a OldWeapon", (scanner, hero) -> {
        OldWeapon.getByLevel(hero.getLevel()).forEach(w -> System.out.println((w.getDescription())));
        OldWeapon weapon = OldWeapon.getByCode(scanner.nextInt());
        hero.setWeapon(weapon);
        System.out.println(hero.getDescription());
        return hero;
    }),
    WELCOME_TO_AREA("Welcome to area. Place is damaged, plagued." +
            " You need to face a Troll.", (scanner, hero) -> {
        OldWeapon.getByLevel(hero.getLevel()).forEach(w -> System.out.println((w.getDescription())));
        OldWeapon weapon = OldWeapon.getByCode(scanner.nextInt());
        hero.setWeapon(weapon);
        System.out.println(hero.getDescription());
        return hero;
    });

    private String desc;

    private BiFunction<Scanner, Hero, Hero> function;

    StoryEnum(String desc, BiFunction<Scanner, Hero, Hero> function) {
        this.desc = desc;
        this.function = function;
    }

    public String getDesc() {
        return desc;
    }

    public BiFunction<Scanner, Hero, Hero> getFunction() {
        return function;
    }
}



