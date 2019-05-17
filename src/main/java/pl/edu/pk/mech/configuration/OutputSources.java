package pl.edu.pk.mech.configuration;

import pl.edu.pk.mech.gui.MainWindow;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OutputSources {

    public static Map<String, TextField> getGuiElements() {
        Map<String, TextField> elements = new HashMap<>();
        elements.put("VCB", MainWindow.getSpeed());
        elements.put("UBT", MainWindow.getBatteriesVoltage());
        elements.put("TMP", MainWindow.getRightEngineTemp());
        elements.put("TMR", MainWindow.getLeftEngineTemp());
        return elements;
    }
}
