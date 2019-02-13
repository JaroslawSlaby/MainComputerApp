package pl.edu.pk.mech.datawrapper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerPresenter implements Presenter {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void update(String data) {
        logger.log(Level.INFO, data);
    }
}
