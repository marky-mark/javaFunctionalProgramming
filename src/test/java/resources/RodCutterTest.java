package resources;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static resources.TestHelper.assertThrows;

public class RodCutterTest {

    @Test
    public void ConciseExceptionTest() {
        RodCutter rodCutter = new RodCutter();
        rodCutter.setPrices(new ArrayList<>());

        //this proves that only this method throws the exception
        assertThrows(RodCutter.RodCutterException.class, () -> rodCutter.maxProfit(0));
    }

}