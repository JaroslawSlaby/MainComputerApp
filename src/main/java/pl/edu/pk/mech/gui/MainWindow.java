package pl.edu.pk.mech.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    private static TextField speed = new TextField();
    private static TextField rightEngineTemp = new TextField();
    private static TextField leftEngineTemp = new TextField();
    private static TextField batteriesVoltage = new TextField();
    private static final JOptionPane WARNING_MESSAGE = new JOptionPane("GRZYB WCISNIETY", JOptionPane.ERROR_MESSAGE);
    private static final JOptionPane INFO_MESSAGE = new JOptionPane("INFORMACJA", JOptionPane.INFORMATION_MESSAGE);
    private static final JOptionPane LIGHTS_ON_MESSAGE = new JOptionPane("SWIATLA WLACZONE", JOptionPane.INFORMATION_MESSAGE);
    private static final JOptionPane LIGHTS_OFF_MESSAGE = new JOptionPane("SWIATLA WYLACZONE", JOptionPane.INFORMATION_MESSAGE);

    public void prepareWindow() {
        frame.setTitle("Main app");
        speed.setText("0");
        speed.setEditable(false);
        speed.setPreferredSize(new Dimension(150, 30));
        rightEngineTemp.setText("0");
        rightEngineTemp.setEditable(false);
        rightEngineTemp.setPreferredSize(new Dimension(50, 30));
        leftEngineTemp.setText("0");
        leftEngineTemp.setEditable(false);
        leftEngineTemp.setPreferredSize(new Dimension(50, 30));
        batteriesVoltage.setText("0");
        batteriesVoltage.setEditable(false);
        batteriesVoltage.setPreferredSize(new Dimension(150, 30));
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(600, 600));
        panel.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
        panel.add(new Label("Speed [km/h]: "));
        panel.add(speed);
        panel.add(new Label("Battery voltage [V]: "));
        panel.add(batteriesVoltage);
        panel.add(new Label("Right engine temperature: "));
        panel.add(rightEngineTemp);
        panel.add(new Label("Left engine temperature: "));
        panel.add(leftEngineTemp);
        panel.setVisible(false);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    public static void switchHUD(boolean poweredOn) {
        panel.setVisible(poweredOn);
    }

    public static void showWarningMessage() {
        JDialog dialog = WARNING_MESSAGE.createDialog(panel, "UWAGA!");
        dialog.setModal(false);
        dialog.setVisible(true);
        new Timer(5000, e -> dialog.setVisible(false)).start();
    }

    public static void showLightsOnMessage() {
        JDialog dialog = LIGHTS_ON_MESSAGE.createDialog(panel, "UWAGA!");
        dialog.setModal(false);
        dialog.setVisible(true);
        new Timer(5000, e -> dialog.setVisible(false)).start();
    }

    public static void showLightsOffMessage() {
        JDialog dialog = LIGHTS_OFF_MESSAGE.createDialog(panel, "UWAGA!");
        dialog.setModal(false);
        dialog.setVisible(true);
        new Timer(5000, e -> dialog.setVisible(false)).start();
    }

    public static void showInfoMessage() {
        JDialog dialog = INFO_MESSAGE.createDialog(panel, "INFO!");
        dialog.setModal(false);
        dialog.setVisible(true);
        new Timer(5000, e -> dialog.setVisible(false)).start();
    }

    public static TextField getSpeed() {
        return speed;
    }

    public static TextField getRightEngineTemp() {
        return rightEngineTemp;
    }

    public static TextField getLeftEngineTemp() {
        return leftEngineTemp;
    }

    public static TextField getBatteriesVoltage() {
        return batteriesVoltage;
    }
}
