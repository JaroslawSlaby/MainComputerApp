package pl.edu.pk.mech.connection;

import com.pi4j.io.serial.*;
import org.slf4j.Logger;
import pl.edu.pk.mech.configuration.Configuration;
import pl.edu.pk.mech.datapresenter.PresentersHandler;
import pl.edu.pk.mech.datasplitter.DataValidationException;
import pl.edu.pk.mech.datasplitter.Splitter;

import java.io.IOException;
import java.util.Map;

import static com.pi4j.io.serial.SerialPort.getDefaultPort;

public class SerialPort {

    private static final Logger logger = Configuration.logger;
    private final PresentersHandler presenters;
    private final Splitter splitter;
    private Serial connection;

    public SerialPort(PresentersHandler presenters, Splitter splitter) {
        this.presenters = presenters;
        this.splitter = splitter;
    }

    public SerialPort openConnection() throws IOException, InterruptedException {
        connection = SerialConnectionCreator.createConnection();
        SerialConfig config = new SerialConfig();
        config.device(getDefaultPort())
                .baud(Baud._9600)
                .dataBits(DataBits._8)
                .parity(Parity.NONE)
                .stopBits(StopBits._1)
                .flowControl(FlowControl.NONE);
        connection.open(config);
        return this;
    }

    public void sendData(String data) throws IOException {
        if (connection.isOpen()) {
            connection.write(data);
            connection.flush();
        }
    }

    public void listenOnSerialPort() {
        createListener();
    }

    private void createListener() {
        connection.addListener(event -> {
            try {
                processData(event.getAsciiString());
            } catch (IOException e) {
                logger.error("Error during reading data from serial port: " + e);
            } catch (DataValidationException e) {
                logger.error("Wrong data frame: " + e);
            }
        });
    }

    private void processData(String data) throws DataValidationException {
        Map<String, String> parametersMap = splitter.splitData(data);
        presenters.update(parametersMap);
    }
}
