package pl.edu.pk.mech.datapresenter;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

@Test
public class LoggerPresenterTest {

    private Presenter presenter;

    @Mock
    private Logger logger;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new LoggerPresenter(logger);
    }

    public void okTest() {
        //given
        Map<String, String> dataMap = prepareDataMap();
        //when
        presenter.update(dataMap);
        //then
        verify(logger).info(dataMap.toString());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void nullTest() {
        //when
        presenter.update(null);
    }

    public void emptyMapTest() {
        //given
        Map<String, String> dataMap = Collections.emptyMap();
        //when
        presenter.update(dataMap);
        //then
        verify(logger).info(dataMap.toString());
    }

    static Map<String, String> prepareDataMap() {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("param1", "value1");
        dataMap.put("param2", "value2");
        dataMap.put("param3", "value3");
        dataMap.put("param4", "value4");
        dataMap.put("param5", "value5");
        return dataMap;
    }
}
