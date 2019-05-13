package pl.edu.pk.mech.gpio;

import com.pi4j.io.gpio.GpioPinDigital;

public final class LedPinHandler extends PinHandler {

    public LedPinHandler(GpioPinDigital pin) {
        super(pin);
    }

}
