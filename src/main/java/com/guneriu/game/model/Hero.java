package com.guneriu.game.model;

import com.guneriu.game.model.old.OldWeapon;
import com.guneriu.game.log.Logger;
import com.guneriu.game.log.LoggerFactory;

/**
 * Created by ugur on 22.06.2016.
 */
public class Hero implements Description {

    private static Logger logger = LoggerFactory.getLogger();

    private String name;

    private Integer level;

    private Integer experience;

    private Integer health;

    private OldWeapon weapon;

    private Area currentArea;

    public Hero() {
        this.level = 1;
        this.health = 100;
        this.experience = 0;
    }

    public Hero(String name) {
        this();
        this.name = name;
    }

    public Hero(String name, Integer health) {
        this(name);
        this.health = health;
    }

    public Hero(String name, Integer health, OldWeapon weapon) {
        this(name, health);
        this.weapon = weapon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public OldWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(OldWeapon weapon) {
        this.weapon = weapon;
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(Area currentArea) {
        this.currentArea = currentArea;
    }

    public Integer getExperience() {
        return experience;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean isDead() {
        return !isAlive();
    }

    public void experience(Integer experience) {
        this.experience += experience;
        if (this.experience >= 100) {
            this.level = this.experience / 100;
            this.experience = this.experience % 100;
            logger.write("Your level is increased to " + this.level);
        }
    }

    private void damage(Integer damage) {
        this.health = damage > health ? 0 : health - damage;
    }

    public void fight(Hero enemy) {
        logger.write("You are fighting with " + enemy.getDescription());
        attack(enemy);
    }

    private void attack(Hero enemy) {
        logger.write("You used your " + this.weapon.getName());
        logger.write("Damage given to enemy: " + this.weapon.getDamage());
        enemy.damage(this.weapon.getDamage());
        logger.write("Your health: " + this.getHealth() + " Enemy health: " + enemy.getHealth());
        if (enemy.isDead()) {
            logger.write("You killed the enemy");
            return;
        }

        logger.write("Enemy attacked with: " + enemy.getWeapon().getName());
        logger.write("Enemy give damage to you: " + enemy.getWeapon().getDamage());
        this.damage(enemy.getWeapon().getDamage());
        logger.write("Your health: " + this.getHealth() + " Enemy health: " + enemy.getHealth());

        if (this.isDead()) {
            logger.write("Sorry, enemy killed you");
            return;
        }

        if (this.isAlive() && enemy.isAlive()) {
            attack(enemy);
        }
    }

    @Override
    public String getDescription() {
        return name  +
                (weapon != null ? ", weapon=" + weapon.getDescription() : "")
                + ", level=" + level
                + ", health=" + health
                + (currentArea != null ? ", at " + currentArea.getDescription() : "")
                + '}';
    }

}
