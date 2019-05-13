package pl.edu.pk.mech.datapresenter;


import pl.edu.pk.mech.configuration.GlobalLogger;

import java.util.Map;

public class LoggerPresenter implements Presenter {

    @Override
    public void update(Map<String, String> data) {
        GlobalLogger.logger.info(data.toString());
    }
}
