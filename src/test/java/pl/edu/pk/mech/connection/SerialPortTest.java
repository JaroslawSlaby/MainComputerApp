package pl.edu.pk.mech.connection;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.edu.pk.mech.datapresenter.PresentersHandler;
import pl.edu.pk.mech.datasplitter.SplitterImpl;

@Test
public class SerialPortTest {

    @Mock
    private SerialConnectionCreator connection;

    @BeforeClass
    public static void setUp() {
        MockitoAnnotations.initMocks(SerialPortTest.class);
    }

    public void okTest() {
//        when(connection)
        SerialPort listener = new SerialPort(new PresentersHandler(), new SplitterImpl());
    }
}
