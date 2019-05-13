package pl.edu.pk.mech;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import pl.edu.pk.mech.configuration.Configuration;
import pl.edu.pk.mech.configuration.OutputSources;
import pl.edu.pk.mech.connection.SerialListener;
import pl.edu.pk.mech.datapresenter.DisplayPresenter;
import pl.edu.pk.mech.datapresenter.LoggerPresenter;
import pl.edu.pk.mech.datapresenter.Presenter;
import pl.edu.pk.mech.datapresenter.PresentersHandler;
import pl.edu.pk.mech.datasplitter.SplitterImpl;
import pl.edu.pk.mech.gpio.ButtonHandler;
import pl.edu.pk.mech.gpio.PinCreator;
import pl.edu.pk.mech.gui.MainWindow;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        new Thread(Main::prepareSerialLisener).start();
        new Thread(Main::turnOnLed).start();
        new Thread(() ->  new MainWindow().prepareWindow()).start();
    }


    private static void turnOnLed() {
        GpioPinDigitalOutput led = PinCreator.createOutputPin(RaspiPin.GPIO_01, "led", PinState.LOW);
        GpioPinDigitalOutput led2 = PinCreator.createOutputPin(RaspiPin.GPIO_02, "led2", PinState.LOW);

        new ButtonHandler(RaspiPin.GPIO_03, "button", PinPullResistance.PULL_UP, event -> {
            System.out.println(event);
            led.pulse(1000);
        }).setUp();

        new ButtonHandler(RaspiPin.GPIO_04, "button2", PinPullResistance.PULL_UP, event -> {
            System.out.println(event);
            led2.pulse(1000);
        }).setUp();

    }

    private static void prepareSerialLisener() {
        Presenter displayPresenter = new DisplayPresenter(OutputSources.getGuiElements());
        Presenter presenter = new LoggerPresenter();
        PresentersHandler presenters = new PresentersHandler();
        presenters.addPresenter(displayPresenter);
        presenters.addPresenter(presenter);

        try {
            new SerialListener(presenters, new SplitterImpl()).openConnection().listenOnSerialPort();
        } catch (IOException | InterruptedException e) {
            Configuration.logger.error("Error during opening serial port: " + e.toString());
        }
    }
}
