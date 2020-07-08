package framework;

import org.apache.logging.log4j.LogManager;

public class Logger {
    private static Logger instance;
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger();

    private Logger() {

    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void info(Object message) {
        log.info(message);
    }

    public void error(Object message) {
        log.error(message);
    }

    public void error(Object message, Throwable error) {
        log.error(message, error);
    }

    public void debug(String message) {
        log.debug(message);
    }
}
