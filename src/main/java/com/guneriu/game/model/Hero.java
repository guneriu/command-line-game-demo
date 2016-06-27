package com.guneriu.game.model;

import com.guneriu.game.util.log.Logger;
import com.guneriu.game.util.log.LoggerFactory;

/**
 *
 * Playable character in the game
 *
 * Created by ugur on 22.06.2016.
 */
public class Hero implements Description {

    private static final int MAX_EXPERIENCE = 100;
    private static Logger logger = LoggerFactory.getLogger();

    private String name;

    private Integer level;

    private Integer experience;

    private Integer health;

    private Weapon weapon;

    private Area currentArea;

    public Hero(String name) {
        this.level = 1;
        this.health = 100;
        this.experience = 0;
        this.name = name;
    }

    public Hero(String name, Integer health) {
        this(name);
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
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
        if (this.experience >= MAX_EXPERIENCE) {
            this.level += this.experience / MAX_EXPERIENCE;
            this.experience = this.experience % MAX_EXPERIENCE;
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
                + ", experience=" + experience
                + (currentArea != null ? ", at " + currentArea.getDescription() : "")
                + '}';
    }

}
