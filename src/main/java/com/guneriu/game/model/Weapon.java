package com.guneriu.game.model;

/**
 * Created by ugur on 26.06.2016.
 */
public class Weapon extends BaseEntity {
    private Integer damage;
    private Integer level;
    private Integer price;

    public Weapon(Integer id, String name, Integer damage, Integer level, Integer price) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.level = level;
        this.price = price;
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

    //FIXME move to the WeaponProvider
//    public static List<Weapon> getByLevel(Integer level) {
//        return Stream.of(Weapon.values()).filter(w -> w.getLevel() <= level).collect(Collectors.toList());
//    }

    //TODO make assertion with level
//    public static Weapon getByCode(Integer code) {
//        return Stream.of(Weapon.values()).filter(w -> w.getCode().equals(code)).findFirst().get();
//    }

    @Override
    public String getDescription() {
        return "{" +
                "(" + this.id + ")" +
                ", name='" + this.getName() + '\'' +
                ", damage=" + damage +
                ", level=" + level +
                ", price=" + price +
                '}';
    }
}
