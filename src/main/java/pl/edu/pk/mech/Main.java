package pl.edu.pk.mech;

import pl.edu.pk.mech.datawrapper.DisplayPresenter;
import pl.edu.pk.mech.datawrapper.LoggerPresenter;
import pl.edu.pk.mech.datawrapper.Presenter;
import pl.edu.pk.mech.datawrapper.Wrapper;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Wrapper wrapper = new Wrapper();
        Presenter presenter = new DisplayPresenter();
        Presenter loggerPresenter = new LoggerPresenter();
        wrapper.addPresenter(presenter);
        wrapper.addPresenter(loggerPresenter);

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        wrapper.update(line);
    }
}
