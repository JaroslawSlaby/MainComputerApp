package pl.edu.pk.mech.gpio;

import com.pi4j.io.gpio.GpioPinDigital;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class PinListenerCreator {

    public static void addEventListenerToPin(GpioPinDigital pin, GpioPinListenerDigital event) {
        pin.addListener(event);
    }
}
