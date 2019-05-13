package pl.edu.pk.mech.gpio;

import com.pi4j.io.gpio.GpioPinDigital;

abstract class PinHandler {

    GpioPinDigital pin;

    public PinHandler(GpioPinDigital pin) {
        this.pin = pin;
    }
}
