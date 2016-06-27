package com.guneriu.game.util.log;

/**
 * Uses Strategy pattern to hide and change the implementation at real time
 *
 * Created by ugur on 25.06.2016.
 */
public class LoggingStrategy implements Logger {
    private Logger logger;

    public LoggingStrategy(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void write(String content) {
        this.logger.write(content);
    }
}
