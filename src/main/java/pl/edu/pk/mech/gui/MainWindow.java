package pl.edu.pk.mech.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private static JFrame frame = new JFrame();
    private static TextField speed = new TextField();

    public void start() {
        frame.setTitle("Main app");
        speed.setText("0");
        speed.setEditable(false);
        frame.add(speed);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    public static TextField getSpeed() {
        return speed;
    }
}
