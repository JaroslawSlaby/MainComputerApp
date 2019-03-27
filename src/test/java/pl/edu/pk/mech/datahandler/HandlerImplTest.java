package pl.edu.pk.mech.datahandler;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.atLeast;


@Test
public class HandlerImplTest {

    private static final String DEFAULT_KEY = "defaultKey";
    private static final String DEFAULT_VALUE = "defaultValue";

    private Handler handler;

    @Mock
    private Map<String, Consumer<String>> outputSources;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        handler = new HandlerImpl(outputSources);
    }

    public void okTest() {
        when(outputSources.get(DEFAULT_KEY)).thenReturn(s -> {});
        Map<String, String> parameters = Collections.singletonMap(DEFAULT_KEY, DEFAULT_VALUE);
        handler.presentData(parameters);
        verify(outputSources).get(DEFAULT_KEY);
    }

    public void okTestWithInputMapSize3() {
        when(outputSources.get(anyString())).thenReturn(s -> {});
        Map<String, String> parameters = new HashMap<>();
        parameters.put("1", "1");
        parameters.put("2", "2");
        parameters.put("3", "3");
        handler.presentData(parameters);
        verify(outputSources, atLeast(3)).get(anyString());
    }

    public void emptyInputTest() {
        Map<String, String> parameters = Collections.emptyMap();
        handler.presentData(parameters);
        verifyZeroInteractions(outputSources);
    }

    public void nullInputTest() {
        handler.presentData(null);
        verifyZeroInteractions(outputSources);
    }
}
