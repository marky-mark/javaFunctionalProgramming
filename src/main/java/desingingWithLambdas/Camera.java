package desingingWithLambdas;

import java.awt.Color;
import java.util.Arrays;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class Camera {
    private Function<Color, Color> filter;

    public Camera() {
        setFilters();
    }

    public Color capture(final Color inputColor) {
        final Color processedColor = filter.apply(inputColor);
        return processedColor;
    }

    public void setFilters(final Function<Color, Color>... filters) {
        filter =
                Arrays.asList(filters).stream()
                        .reduce((filter, next) -> filter.compose(next))
                        .orElse(color -> color);
    }
}