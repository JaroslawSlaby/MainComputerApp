package pl.edu.pk.mech.configuration;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class GPIOControllerHandler {

    private static GpioController controller;

    public static synchronized GpioController getController() {
        if (controller == null) {
            controller = GpioFactory.getInstance();
        }

        return controller;
    }

}
