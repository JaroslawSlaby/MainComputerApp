package pl.edu.pk.mech.gpio;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public final class ButtonHandler {

    private final Pin pin;
    private final String pinName;
    private final PinPullResistance resistance;
    private final GpioPinListenerDigital event;


    public ButtonHandler(Pin pin, String pinName, PinPullResistance resistance, GpioPinListenerDigital event) {
        this.pin = pin;
        this.pinName = pinName;
        this.resistance = resistance;
        this.event = event;
    }

    public void setUp() {
        GpioPinDigitalInput inputPin = PinCreator.createInputPin(pin, pinName, resistance);
        inputPin.addListener(event);
    }
}
