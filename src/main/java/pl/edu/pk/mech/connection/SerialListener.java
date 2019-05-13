package pl.edu.pk.mech.connection;

import com.pi4j.io.serial.*;
import org.slf4j.Logger;
import pl.edu.pk.mech.configuration.GlobalLogger;
import pl.edu.pk.mech.datapresenter.PresentersHandler;
import pl.edu.pk.mech.datasplitter.DataValidationException;
import pl.edu.pk.mech.datasplitter.Splitter;

import java.io.IOException;
import java.util.Map;

public class SerialListener {

    private static final Logger logger = GlobalLogger.logger;
    private final PresentersHandler presenters;
    private final Splitter splitter;
    private Serial connection;

    public SerialListener(PresentersHandler presenters, Splitter splitter) {
        this.presenters = presenters;
        this.splitter = splitter;
    }

    public SerialListener openConnection() {
        connection = SerialConnectionCreator.createConnection();
        return this;
    }

    public void listenOnSerialPort() throws IOException, InterruptedException {
        createListener();
        SerialConfig config = new SerialConfig();
        config.device(SerialPort.getDefaultPort())
                .baud(Baud._38400)
                .dataBits(DataBits._8)
                .parity(Parity.NONE)
                .stopBits(StopBits._1)
                .flowControl(FlowControl.NONE);
        connection.open(config);
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
