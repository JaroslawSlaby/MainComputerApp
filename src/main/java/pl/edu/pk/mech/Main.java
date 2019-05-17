package pl.edu.pk.mech;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import pl.edu.pk.mech.configuration.OutputSources;
import pl.edu.pk.mech.connection.SerialPort;
import pl.edu.pk.mech.datapresenter.DisplayPresenter;
import pl.edu.pk.mech.datapresenter.LoggerPresenter;
import pl.edu.pk.mech.datapresenter.Presenter;
import pl.edu.pk.mech.datapresenter.PresentersHandler;
import pl.edu.pk.mech.datasplitter.SplitterImpl;
import pl.edu.pk.mech.gpio.ButtonHandler;
import pl.edu.pk.mech.gpio.PinCreator;
import pl.edu.pk.mech.gui.MainWindow;

import java.io.IOException;

import static pl.edu.pk.mech.configuration.Configuration.logger;

public class Main {

    private static SerialPort listener;

    public static void main(String[] args) {
        prepareSerial();
        new Thread(prepareSerialListener()).start();
        new Thread(prepareButtons()).start();
        new Thread(() ->  new MainWindow().prepareWindow()).start();
    }


    private static Runnable prepareButtons() {
        return () -> {
            //grzyb
            new ButtonHandler(RaspiPin.GPIO_01, "grzyb", PinPullResistance.PULL_UP).setUpEvent(event -> {
                MainWindow.showWarningMessage();
                try {
                    listener.sendData("NUM:1;GRB:0;");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            //swiatla
            GpioPinDigitalOutput lights = PinCreator.createOutputPin(RaspiPin.GPIO_03, "lights_out", PinState.LOW);
            new ButtonHandler(RaspiPin.GPIO_02, "lights_in", PinPullResistance.PULL_UP).setUpEvent(event -> {
                if (event.getState().isHigh()) {
                    if (lights.getState().isLow()) {
                        MainWindow.showLightsOnMessage();
                        lights.setState(PinState.HIGH);
                    } else {
                        MainWindow.showLightsOffMessage();
                        lights.setState(PinState.LOW);
                    }
                }
            });

            //komunikat
            GpioPinDigitalOutput other = PinCreator.createOutputPin(RaspiPin.GPIO_04, "other_out", PinState.LOW);
            new ButtonHandler(RaspiPin.GPIO_05, "other_in", PinPullResistance.PULL_UP).setUpEvent(event -> {
                if (event.getState().isHigh()) {
                    if (other.getState().isLow()) {
                        MainWindow.showInfoMessage();
                        other.setState(PinState.HIGH);
                    } else {
                        other.setState(PinState.LOW);
                    }
                }
            });

            //stacyjka
            GpioPinDigitalOutput power_on = PinCreator.createOutputPin(RaspiPin.GPIO_06, "power_on_out", PinState.LOW);
            new ButtonHandler(RaspiPin.GPIO_07, "power_on_in", PinPullResistance.PULL_UP).setUpEvent(event -> {
                if (event.getState().isHigh()) {
                    if (power_on.getState().isLow()) {
                        MainWindow.switchHUD(true);
                        power_on.setState(PinState.HIGH);
                    } else {
                        MainWindow.switchHUD(false);
                        power_on.setState(PinState.LOW);
                    }
                }
            });

            //3x3
            GpioPinDigitalOutput led1 = PinCreator.createOutputPin(RaspiPin.GPIO_21, "led_panel_out1", PinState.LOW);
            GpioPinDigitalOutput led2 = PinCreator.createOutputPin(RaspiPin.GPIO_22, "led_panel_out2", PinState.LOW);
            GpioPinDigitalOutput led3 = PinCreator.createOutputPin(RaspiPin.GPIO_23, "led_panel_out3", PinState.LOW);
            GpioPinDigitalOutput led4 = PinCreator.createOutputPin(RaspiPin.GPIO_24, "led_panel_out4", PinState.LOW);
            GpioPinDigitalOutput led5 = PinCreator.createOutputPin(RaspiPin.GPIO_25, "led_panel_out5", PinState.LOW);
            GpioPinDigitalOutput led6 = PinCreator.createOutputPin(RaspiPin.GPIO_26, "led_panel_out6", PinState.LOW);
            GpioPinDigitalOutput led7 = PinCreator.createOutputPin(RaspiPin.GPIO_27, "led_panel_out7", PinState.LOW);
            GpioPinDigitalOutput led8 = PinCreator.createOutputPin(RaspiPin.GPIO_28, "led_panel_out8", PinState.LOW);
            GpioPinDigitalOutput led9 = PinCreator.createOutputPin(RaspiPin.GPIO_29, "led_panel_out9", PinState.LOW);
            new ButtonHandler(RaspiPin.GPIO_00, "led_panel_in", PinPullResistance.PULL_UP).setUpEvent(event -> {
                if (event.getState().isHigh()) {
                    try {
                        led1.toggle();
                        Thread.sleep(1000);
                        led2.toggle();
                        Thread.sleep(1000);
                        led3.toggle();
                        Thread.sleep(1000);
                        led4.toggle();
                        Thread.sleep(1000);
                        led5.toggle();
                        Thread.sleep(1000);
                        led6.toggle();
                        Thread.sleep(1000);
                        led7.toggle();
                        Thread.sleep(1000);
                        led8.toggle();
                        Thread.sleep(1000);
                        led9.toggle();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        };
    }

    private static void prepareSerial() {
        Presenter displayPresenter = new DisplayPresenter(OutputSources.getGuiElements());
        Presenter presenter = new LoggerPresenter(logger);
        PresentersHandler presenters = new PresentersHandler();
        presenters.addPresenter(displayPresenter);
        presenters.addPresenter(presenter);

        try {
            listener = new SerialPort(presenters, new SplitterImpl()).openConnection();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable prepareSerialListener() {
        return () -> listener.listenOnSerialPort();
    }
}
