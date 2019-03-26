package pl.edu.pk.mech.datawrapper;

import java.util.logging.Logger;

public class LoggerPresenter implements Presenter {

    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void update(String data) {
        logger.info(data);
    }
}
