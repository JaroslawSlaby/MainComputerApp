package pl.edu.pk.mech.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class OutputSources {

    public static Map<String, Consumer<String>> getDisplayParameters() {
        Map<String, Consumer<String>> displays = new HashMap<>();
        displays.put("CN", System.out::println);
        displays.put("LG", GlobalLogger.logger::info);
        displays.put("ER", GlobalLogger.logger::error);
        return displays;
    }
}
