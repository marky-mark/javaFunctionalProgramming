package desingingWithLambdas;

import java.math.BigDecimal;
import java.util.function.Function;

public class CalcNav {

    private Function<String, BigDecimal> priceFinder;

    public CalcNav(Function<String, BigDecimal> priceFinder) {
        this.priceFinder = priceFinder;
    }

    public BigDecimal computeStockWorth(final String ticker, final int shares) {
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }
//... other methods that use the priceFinder ...
}