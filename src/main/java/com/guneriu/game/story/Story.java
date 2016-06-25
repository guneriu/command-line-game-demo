package com.guneriu.game.story;

import com.guneriu.game.model.Hero;

import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Created by ugur on 25.06.2016.
 */
public class Story {

    private Integer id;

    private String desc;

    private boolean completed;

    private BiFunction<Scanner, Hero, Hero> function;

    private Hero enemy;

    private Integer experience;

    public Story(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Story(String desc, BiFunction<Scanner, Hero, Hero> function, Integer experience) {
        this.desc = desc;
        this.function = function;
        this.experience = experience;
    }

    public Story(String desc, BiFunction<Scanner, Hero, Hero> function, Integer experience, Hero enemy) {
        this(desc, function, experience);
        this.enemy = enemy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BiFunction<Scanner, Hero, Hero> getFunction() {
        return function;
    }

    public void setFunction(BiFunction<Scanner, Hero, Hero> function) {
        this.function = function;
    }

    public Hero getEnemy() {
        return enemy;
    }

    public void setEnemy(Hero enemy) {
        this.enemy = enemy;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public boolean hasEnemy() {
        return this.enemy != null;
    }
}
