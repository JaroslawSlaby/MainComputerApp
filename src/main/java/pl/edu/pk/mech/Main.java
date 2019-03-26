package pl.edu.pk.mech;

import pl.edu.pk.mech.datawrapper.DisplayPresenter;
import pl.edu.pk.mech.datawrapper.LoggerPresenter;
import pl.edu.pk.mech.datawrapper.Presenter;
import pl.edu.pk.mech.datawrapper.PresentersHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PresentersHandler presentersHandler = new PresentersHandler();
        Presenter presenter = new DisplayPresenter();
        Presenter loggerPresenter = new LoggerPresenter();
        presentersHandler.addPresenter(presenter);
        presentersHandler.addPresenter(loggerPresenter);

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        presentersHandler.update(line);
    }
}
