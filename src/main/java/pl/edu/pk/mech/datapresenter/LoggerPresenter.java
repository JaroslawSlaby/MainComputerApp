package pl.edu.pk.mech.datapresenter;


import org.slf4j.Logger;

import java.util.Map;

public class LoggerPresenter implements Presenter {

    private final Logger logger;

    public LoggerPresenter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void update(Map<String, String> data) {
        logger.info(data.toString());
    }
}
