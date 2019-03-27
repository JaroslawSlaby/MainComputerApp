package pl.edu.pk.mech.datahandler;

import java.util.Map;
import java.util.function.Consumer;

public class HandlerImpl implements Handler {

    private Map<String, Consumer<String>> displayParameters;

    public HandlerImpl(Map<String, Consumer<String>> displayParameters) {
        this.displayParameters = displayParameters;
    }

    @Override
    public void presentData(Map<String, String> parameters) {
        if(parameters != null && !parameters.isEmpty())
            parameters.forEach((s, s2) -> displayParameters.get(s).accept(s2));
    }
}
