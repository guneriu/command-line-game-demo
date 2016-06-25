package com.guneriu.game.log;

/**
 * Created by ugur on 25.06.2016.
 */
public class LoggerFactory {

    private static LoggingStrategy loggingStrategy;

    private LoggerFactory() {}

    public static void setupLogger(Logger logger) {
        loggingStrategy = new LoggingStrategy(logger);
    }

    public static Logger getLogger() {
        if (loggingStrategy == null) {
            setupLogger(new SystemLogger());
        }

        return loggingStrategy;
    }


}
