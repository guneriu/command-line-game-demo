package com.guneriu.game.model.old;

import com.guneriu.game.model.Description;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ugur on 23.06.2016.
 */
public enum OldWeapon implements Description {
    SWORD(1, "Basic Iron Sword", 20, 1, 0),
    BASIC_ARROW(2, "Basic Wooden Arrow", 20, 1, 0),
    SHARP_SWORD(3, "Basic Sharp Iron Sword", 30, 2, 10),
    PLASTIC_ARROW(4, "Feathered Arrow", 30, 2, 10),
    AXE(5, "Sharp Axe", 40, 3, 60),
    HAMMER(6, "Mjolnir", 40, 3, 80);

    private Integer code;
    private String name;
    private Integer damage;
    private Integer level;
    private Integer price;

    OldWeapon(Integer code, String name, Integer damage, Integer level, Integer price) {
        this.code = code;
        this.name = name;
        this.damage = damage;
        this.level = level;
        this.price = price;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static List<OldWeapon> getByLevel(Integer level) {
        return Stream.of(OldWeapon.values()).filter(w -> w.getLevel() <= level).collect(Collectors.toList());
    }

    //TODO make assertion with level
    public static OldWeapon getByCode(Integer code) {
        return Stream.of(OldWeapon.values()).filter(w -> w.getCode().equals(code)).findFirst().get();
    }

    @Override
    public String getDescription() {
        return "{" +
                "(" + code + ")" +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", level=" + level +
                ", price=" + price +
                '}';
    }
}
