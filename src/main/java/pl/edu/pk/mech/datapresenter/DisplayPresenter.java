package pl.edu.pk.mech.datapresenter;

import java.awt.*;
import java.util.Map;

public class DisplayPresenter implements Presenter {

    private final Map<String, TextField> guiElements;

    public DisplayPresenter(Map<String, TextField> guiElements) {
        this.guiElements = guiElements;
    }

    @Override
    public void update(Map<String, String> data) {
        data.forEach((key, value) -> guiElements.get(key).setText(value));
    }
}
