package pl.edu.pk.mech.datasplitter;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test
public class SplitterImplTest {

    private Splitter splitter;

    @BeforeClass
    public void setUp() {
        splitter = new SplitterImpl();
    }

    public void testSplitData_WithOKInput() throws Exception {
        //given
        String input = "NUM:4;VCB:10;UBT:10;TMR:10;TMP:10;";
        //when
        Map<String, String> splitdata = splitter.splitData(input);
        //then
        assertEquals(splitdata.size(), 4);
        assertTrue(splitdata.containsKey("VCB"));
        assertTrue(splitdata.containsKey("UBT"));
        assertTrue(splitdata.containsKey("TMR"));
        assertTrue(splitdata.containsKey("TMP"));
    }

    public void testSplitData_WithOKInput_ContainingOneElement() throws Exception {
        //given
        String input = "NUM:1;VCB:10;";
        //when
        Map<String, String> splitdata = splitter.splitData(input);
        //then
        assertEquals(splitdata.size(), 1);
        assertTrue(splitdata.containsKey("VCB"));
    }

    public void testSplitData_WithOKInput_AndOneIncorrectParameter_ContainingOneElement() throws Exception {
        //given
        String input = "NUM:2;VCB:10;AAA:10;";
        //when
        Map<String, String> splitdata = splitter.splitData(input);
        //then
        assertEquals(splitdata.size(), 1);
        assertTrue(splitdata.containsKey("VCB"));
    }

    public void testSplitData_WithOKInput_AndAllIncorrectParameters_ReturnsEmptyList() throws Exception {
        //given
        String input = "NUM:2;BBB:10;AAA:10;";
        //when
        Map<String, String> splitdata = splitter.splitData(input);
        //then
        assertEquals(splitdata.size(), 0);
    }

    public void testSplitData_WithZeroLengthInput() throws Exception {
        //given
        String input = "NUM:0;";
        //when
        Map<String, String> splitdata = splitter.splitData(input);
        //then
        assertEquals(splitdata.size(), 0);
        assertEquals(splitdata, Collections.emptyMap());
    }

    @Test(expectedExceptions = DataValidationException.class)
    public void testSplitData_WithNullInput() throws Exception {
        //given - when
        splitter.splitData(null);
        //then throw IAE
    }

    @Test(expectedExceptions = DataValidationException.class)
    public void testSplitData_WithEmptyInput() throws Exception {
        //given - when
        splitter.splitData("");
        //then throw IAE
    }

    @Test(expectedExceptions = DataValidationException.class)
    public void testSplitData_WithOKInput_AndInvalidNumberOfParams() throws Exception {
        //given
        String input = "NUM:2;AA:10;BB:10;CC:10;";
        //when
        splitter.splitData(input);
        //then throw exception
    }
}
