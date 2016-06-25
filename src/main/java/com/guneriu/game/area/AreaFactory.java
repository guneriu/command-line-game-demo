package com.guneriu.game.area;

import com.guneriu.game.model.Area;

/**
 * Created by ugur on 25.06.2016.
 */
public class AreaFactory {

    private static AreaFactory instance;

    private AreaFactory() {

    }

    public static AreaFactory getInstance() {
        if (instance == null) {
            instance = new AreaFactory();
        }

        return instance;
    }



}
