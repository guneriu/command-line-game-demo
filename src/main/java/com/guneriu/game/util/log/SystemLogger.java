package com.guneriu.game.util.log;

/**
 * Logger instance which writes to {@link Logger} Hede
 *
 * Created by ugur on 25.06.2016.
 */
public class SystemLogger implements Logger {

    @Override
    public void write(String content) {
        System.out.println(content);
    }
}
