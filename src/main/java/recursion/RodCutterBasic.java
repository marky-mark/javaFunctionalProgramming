package recursion;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static recursion.Memorizer.callMemorized;

public class RodCutterBasic {

    final List<Integer> prices;

    public RodCutterBasic(final List<Integer> pricesForLength) {
        prices = pricesForLength;
    }

    public int maxProfitBasic(final int length) {
        int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
        for (int i = 1; i < length; i++) {
            int priceWhenCut = maxProfit(i) + maxProfit(length - i);
            if (profit < priceWhenCut) profit = priceWhenCut;
        }

        return profit;
    }

    public int maxProfit(final int rodLenth) {
        return callMemorized(
                (final Function<Integer, Integer> func, final Integer length) -> {
                    int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
                    for (int i = 1; i < length; i++) {
                        int priceWhenCut = func.apply(i) + func.apply(length - i);
                        if (profit < priceWhenCut) profit = priceWhenCut;
                    }
                    return profit;
                }, rodLenth);
    }

    static final List<Integer> priceValues = Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);

    public static void run(final RodCutterBasic rodCutter) {
        System.out.println(rodCutter.maxProfit(5));
        System.out.println(rodCutter.maxProfit(22));
        System.out.println(rodCutter.maxProfit(200));
    }

    public static void main(final String[] args) {
        final RodCutterBasic rodCutter = new RodCutterBasic(priceValues);
        run(rodCutter);
    }
}
