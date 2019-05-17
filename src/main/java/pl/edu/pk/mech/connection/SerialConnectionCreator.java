package pl.edu.pk.mech.connection;

import com.pi4j.io.serial.*;

public class SerialConnectionCreator {

    private static Serial connection;

    public static Serial createConnection() {
        if(connection == null) {
            connection = SerialFactory.createInstance();
        }
        return connection;
    }
}
