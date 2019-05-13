package pl.edu.pk.mech.connection;

import com.pi4j.io.serial.*;

import java.io.IOException;

public class SerialConnectionCreator {

    public static Serial createConnection() {
        final Serial connection = SerialFactory.createInstance();
        return connection;
    }
}
