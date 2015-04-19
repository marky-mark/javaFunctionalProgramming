package resources;

import java.util.List;

public class RodCutter {
    public void setPrices(final List<Integer> prices) {
//        throw new RodCutterException();
    }
    public int maxProfit(final int length) {
        if (length == 0) throw new RodCutterException();
        return 0;
    }

    public class RodCutterException extends RuntimeException {
    }
}