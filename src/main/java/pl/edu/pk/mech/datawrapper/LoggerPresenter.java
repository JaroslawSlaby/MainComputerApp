package pl.edu.pk.mech.datawrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerPresenter implements Presenter {

    private static Logger logger = LoggerFactory.getLogger(LoggerPresenter.class);

    @Override
    public void update(String data) {
        logger.info(data);
    }
}
