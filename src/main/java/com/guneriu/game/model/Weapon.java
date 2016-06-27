package com.guneriu.game.model;

/**
 * Weapons can be usable for fight
 *
 * Created by ugur on 26.06.2016.
 */
public class Weapon implements Description {

    protected String id;

    protected String name;

    private Integer damage;

    private Integer level;

    public Weapon(String id, String name, Integer damage, Integer level) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDamage() {
        return damage;
    }

    public Integer getLevel() {
        return level;
    }

    @Override
    public String getDescription() {
        return "{" +
                "(" + this.id + ")" +
                ", name='" + this.name + '\'' +
                ", damage=" + damage +
                ", level=" + level +
                '}';
    }
}
