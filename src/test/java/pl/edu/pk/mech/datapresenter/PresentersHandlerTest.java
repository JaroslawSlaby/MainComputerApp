package pl.edu.pk.mech.datapresenter;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.powermock.reflect.Whitebox.setInternalState;
import static org.testng.Assert.assertEquals;
import static pl.edu.pk.mech.datapresenter.LoggerPresenterTest.prepareDataMap;

@Test
public class PresentersHandlerTest {

    @Mock
    private Logger logger;

    private Presenter presenter;

    private PresentersHandler handler;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        handler = new PresentersHandler();
    }

    public void okTestAddingPresenter() {
        //given
        List<Presenter> spy = createSpyList();
        //when
        handler.addPresenter(presenter);
        //then
        verify(spy).add(presenter);
        assertEquals(spy.size(), 1);
    }

    public void nullTestAddingPresenter() {
        //given
        List<Presenter> spy = createSpyList();
        //when
        handler.addPresenter(null);
        //then
        verify(spy).add(null);
        assertEquals(spy.size(),1);
    }

    public void nullTwiceTestAddingPresenter() {
        //given
        List<Presenter> spy = createSpyList();
        //when
        handler.addPresenter(null);
        handler.addPresenter(null);
        //then
        verify(spy, times(2)).add(null);
        assertEquals(spy.size(), 2);
    }

    public void okTestRemovingPresenter() {
        //given
        List<Presenter> spy = createSpyList();
        presenter = new LoggerPresenter(logger);
        //when
        handler.addPresenter(presenter);
        handler.removePresenter(presenter);
        //then
        verify(spy).add(presenter);
        verify(spy).remove(presenter);
        assertEquals(spy.size(), 0);
    }

    public void nullTestRemovingPresenter() {
        //given
        List<Presenter> spy = createSpyList();
        //when
        handler.removePresenter(null);
        //then
        verify(spy).remove(null);
        assertEquals(spy.size(), 0);
    }

    private List<Presenter> createSpyList() {
        List<Presenter> presenters = new ArrayList<>();
        List<Presenter> spy = spy(presenters);
        setInternalState(handler, "presenters", spy);
        return spy;
    }

    public void nullTwiceTestRemovingPresenter() {
        //given
        List<Presenter> spy = createSpyList();
        //when
        handler.removePresenter(null);
        handler.removePresenter(null);
        //then
        verify(spy, times(2)).remove(null);
        assertEquals(spy.size(), 0);
    }

    public void okTestUpdatingPresenters() {
        //given
        presenter = new LoggerPresenter(logger);
        ArrayList<Presenter> presenters = new ArrayList<>();
        setInternalState(handler, "presenters", presenters);
        Presenter spy = spy(presenter);
        handler.addPresenter(spy);
        handler.addPresenter(spy);
        Map<String, String> dataMap = prepareDataMap();
        //when
        handler.update(dataMap);
        //then
        verify(spy, times(2)).update(dataMap);
        verify(logger, times(2)).info(dataMap.toString());
        assertEquals(presenters.size(), 2);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void nullTestUpdatingPresenters() {
        //given
        presenter = new LoggerPresenter(logger);
        ArrayList<Presenter> presenters = new ArrayList<>();
        setInternalState(handler, "presenters", presenters);
        Presenter spy = spy(presenter);
        handler.addPresenter(spy);
        handler.addPresenter(spy);
        //when
        handler.update(null);
    }
}
