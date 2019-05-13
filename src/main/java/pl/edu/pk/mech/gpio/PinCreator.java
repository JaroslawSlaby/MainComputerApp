package pl.edu.pk.mech.gpio;

import com.pi4j.io.gpio.*;
import pl.edu.pk.mech.configuration.GPIOControllerHandler;

public class PinCreator {

    private static final GpioController controller = GPIOControllerHandler.getController();

    public static GpioPinDigitalOutput createOutputPin(Pin pin, String name, PinState state) {
        return controller.provisionDigitalOutputPin(pin, name, state);
    }

    public static GpioPinDigitalInput createInputPin(Pin pin, String name, PinPullResistance resistance) {
        return controller.provisionDigitalInputPin(pin, name, resistance);
    }
}
