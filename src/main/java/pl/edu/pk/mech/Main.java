package pl.edu.pk.mech;

import pl.edu.pk.mech.configuration.OutputSources;
import pl.edu.pk.mech.datahandler.Handler;
import pl.edu.pk.mech.datahandler.HandlerImpl;

import java.util.Collections;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, String> dupa = Collections.singletonMap("ER", "123");

        Handler handler = new HandlerImpl(OutputSources.getDisplayParameters());
        handler.presentData(dupa);
    }
}
