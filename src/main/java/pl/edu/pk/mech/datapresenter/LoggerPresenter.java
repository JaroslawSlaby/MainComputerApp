package pl.edu.pk.mech.datapresenter;


import pl.edu.pk.mech.configuration.Configuration;

import java.util.Map;

public class LoggerPresenter implements Presenter {

    @Override
    public void update(Map<String, String> data) {
        Configuration.logger.info(data.toString());
    }
}
