package com.guneriu.game.model;

/**
 *
 * Playable story of the game
 *
 * Created by ugur on 25.06.2016.
 */
public class Story {

    private final String id;

    private final String desc;

    private boolean completed;

    private Hero enemy;

    private Integer experience;

    public Story(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
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
