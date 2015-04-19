package desingingWithLambdas;

import desingingWithLambdas.CalcNav;
import desingingWithLambdas.YahooFinance;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CalcNavTest {
    @Test
    public void computeStockWorth() {
        final CalcNav calculateNAV = new CalcNav(ticker -> new BigDecimal("6.01"));
        BigDecimal expected = new BigDecimal("6010.00");
        Assert.assertEquals(0, calculateNAV.computeStockWorth("GOOG", 1000).compareTo(expected));
    }

    @Test(expected = RuntimeException.class)
    public void computeStockWorthException() {
        final CalcNav calculateNAV = new CalcNav(ticker -> {throw new RuntimeException();});
        BigDecimal expected = new BigDecimal("6010.00");
        Assert.assertEquals(0, calculateNAV.computeStockWorth("GOOG", 1000).compareTo(expected));
    }

    @Test
    public void computeYahoo() throws Exception {
        final CalcNav calculateNav = new CalcNav(YahooFinance::getPrice);
        System.out.println(String.format("100 shares of Google worth: $%.2f",
                calculateNav.computeStockWorth("GOOG", 100)));

    }
}