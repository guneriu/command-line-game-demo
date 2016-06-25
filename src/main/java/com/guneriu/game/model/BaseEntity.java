package com.guneriu.game.model;

/**
 * Created by ugur on 26.06.2016.
 */
public abstract class BaseEntity implements Description {
    protected Integer id;
    protected String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
