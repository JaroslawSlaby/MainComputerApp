package pl.edu.pk.mech.datasplitter;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

import static org.testng.Assert.*;

public class SplitterImplTest {

    private Splitter splitter;

    @BeforeClass
    public void setUp() {
        splitter = new SplitterImpl();
    }

    @Test
    public void testSplitData_WithOKInput() {
        //given
        String input = "L:3;AA:10;BB:10;CC:10";
        //when
        Map<String, String> splitdata = splitter.splitData(input);
        //then
        assertEquals(splitdata.size(), 3);
        assertTrue(splitdata.containsKey("AA"));
        assertTrue(splitdata.containsKey("BB"));
        assertTrue(splitdata.containsKey("CC"));
    }

    @Test
    public void testSplitData_WithOKInput_ContainingOneElement() {
        //given
        String input = "L:1;AA:10";
        //when
        Map<String, String> splitdata = splitter.splitData(input);
        //then
        assertEquals(splitdata.size(), 1);
        assertTrue(splitdata.containsKey("AA"));
    }

    @Test
    public void testSplitData_WithZeroLengthInput() {
        //given
        String input = "L:0";
        //when
        Map<String, String> splitdata = splitter.splitData(input);
        //then
        assertEquals(splitdata.size(), 0);
        assertEquals(splitdata, Collections.emptyMap());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSplitData_WithNullInput() {
        //given - when
        splitter.splitData(null);
        //then throw IAE
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSplitData_WithEmptyInput() {
        //given - when
        splitter.splitData("");
        //then throw IAE
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSplitData_WithOKInput_AndInvalidNumberOfParams() {
        //given
        String input = "L:2;AA:10;BB:10;CC:10";
        //when
        splitter.splitData(input);
        //then throw exception
    }
}