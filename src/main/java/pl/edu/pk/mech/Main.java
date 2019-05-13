package pl.edu.pk.mech;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import pl.edu.pk.mech.configuration.OutputSources;
import pl.edu.pk.mech.datapresenter.DisplayPresenter;
import pl.edu.pk.mech.datapresenter.LoggerPresenter;
import pl.edu.pk.mech.datapresenter.Presenter;
import pl.edu.pk.mech.datapresenter.PresentersHandler;
import pl.edu.pk.mech.gpio.ButtonHandler;
import pl.edu.pk.mech.gpio.PinCreator;
import pl.edu.pk.mech.gui.MainWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        new Thread(Main::prepareBackground).start();
        new MainWindow().start();
    }


    private static void turnOnLed() {
        GpioPinDigitalOutput led = PinCreator.createOutputPin(RaspiPin.GPIO_01, "led", PinState.LOW);
        GpioPinDigitalOutput led2 = PinCreator.createOutputPin(RaspiPin.GPIO_03, "led2", PinState.LOW);

        new Thread(new ButtonHandler(RaspiPin.GPIO_02, "button", PinPullResistance.PULL_UP, event -> {
            System.out.println(event);
            led.pulse(1000);
        })).start();

        new Thread(new ButtonHandler(RaspiPin.GPIO_04, "button2", PinPullResistance.PULL_UP, event -> {
            System.out.println(event);
            led2.pulse(1000);
        })).start();

    }

    private static void prepareBackground() {
        Presenter displayPresenter = new DisplayPresenter(OutputSources.getGuiElements());
        Presenter presenter = new LoggerPresenter();
        PresentersHandler handler = new PresentersHandler();
        handler.addPresenter(displayPresenter);
        handler.addPresenter(presenter);

        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        while (!value.equals("exit")) {
            Map<String, String> data = new HashMap<>();
            data.put("VCB", value);
            handler.update(data);
            value = scanner.nextLine();
        }
    }
}
