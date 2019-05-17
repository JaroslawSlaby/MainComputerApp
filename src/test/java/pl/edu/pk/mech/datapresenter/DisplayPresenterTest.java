package pl.edu.pk.mech.datapresenter;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static pl.edu.pk.mech.datapresenter.LoggerPresenterTest.prepareDataMap;

@Test
public class DisplayPresenterTest {

    @Mock
    private TextField textField;

    private Presenter presenter;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new DisplayPresenter(prepareDisplayMap());
    }

    public void okTest() {
        //when
        presenter.update(prepareDataMap());
        //then
        verify(textField).setText("value1");
        verify(textField).setText("value2");
        verify(textField).setText("value3");
        verify(textField).setText("value4");
        verify(textField).setText("value5");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void nullTest() {
        //when
        presenter.update(null);
    }

    public void emptyMapTest() {
        //when
        presenter.update(emptyMap());
        //then
        verify(textField, times(0)).setText("");
    }

    private Map<String, TextField> prepareDisplayMap() {
        Map<String, TextField> displayMap = new HashMap<>();
        displayMap.put("param1", textField);
        displayMap.put("param2", textField);
        displayMap.put("param3", textField);
        displayMap.put("param4", textField);
        displayMap.put("param5", textField);
        return displayMap;
    }
}
