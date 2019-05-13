package pl.edu.pk.mech.connection;

import com.pi4j.io.serial.impl.SerialImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.edu.pk.mech.datapresenter.PresentersHandler;
import pl.edu.pk.mech.datasplitter.SplitterImpl;

import static org.mockito.Mockito.when;

@Test
public class SerialListenerTest {

    @Mock
    private SerialConnectionCreator connection;

    @BeforeClass
    public static void setUp() {
        MockitoAnnotations.initMocks(SerialListenerTest.class);
    }

    public void okTest() {
//        when(connection)
        SerialListener listener = new SerialListener(new PresentersHandler(), new SplitterImpl());
    }
}
