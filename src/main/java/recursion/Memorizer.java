package recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Memorizer {

    public static <T, R> R callMemorized(final BiFunction<Function<T, R>, T, R> function, final T input) {
        
        Function<T, R> memorized = new Function<T, R>() {

            private final Map<T, R> store = new HashMap<>();

            public R apply(final T input) {
                return store.computeIfAbsent(input, key -> function.apply(this, key));
            }
        };
        
        return memorized.apply(input);
    }
}
