package pl.edu.pk.mech.gpio;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public final class ButtonHandler {

    private final Pin pin;
    private final String pinName;
    private final PinPullResistance resistance;

    public ButtonHandler(Pin pin, String pinName, PinPullResistance resistance) {
        this.pin = pin;
        this.pinName = pinName;
        this.resistance = resistance;
    }

    public void setUpEvent(GpioPinListenerDigital event) {
        GpioPinDigitalInput inputPin = PinCreator.createInputPin(pin, pinName, resistance);
        inputPin.addListener(event);
    }
}
