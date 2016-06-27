package com.guneriu.game.service.impl;

import com.guneriu.game.service.ExitService;

/**
 * Created by ugur on 28.06.2016.
 */
public class ExitServiceImpl implements ExitService {

    @Override
    public void exit() {
        System.exit(0);
    }
}
