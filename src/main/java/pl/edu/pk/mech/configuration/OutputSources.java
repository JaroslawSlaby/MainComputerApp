package pl.edu.pk.mech.configuration;

import pl.edu.pk.mech.gui.MainWindow;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OutputSources {

    public static Map<String, TextField> getGuiElements() {
        Map<String, TextField> elements = new HashMap<>();
        elements.put("VCB", MainWindow.getSpeed());
//        elements.put("ASW", MainWindow.getSpeed());
//        elements.put("UST", MainWindow.getSpeed());
        return elements;
    }
}
