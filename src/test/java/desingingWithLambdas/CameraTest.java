package desingingWithLambdas;

import desingingWithLambdas.Camera;
import org.junit.Test;

import java.awt.*;
import java.util.function.Consumer;

public class CameraTest {

    @Test
    public void testCamera() throws Exception {
        final Camera camera = new Camera();
        final Consumer<String> printCaptured = (filterInfo) ->
                System.out.println(String.format("with %s: %s", filterInfo, camera.capture(new Color(200, 100, 200))));

        System.out.println("//" + "START:NOFILTER_OUTPUT");
        printCaptured.accept("no filter");
        System.out.println("//" + "END:NOFILTER_OUTPUT");

        System.out.println("//" + "START:BRIGHT_OUTPUT");
        camera.setFilters(Color::brighter);
        printCaptured.accept("brighter filter");
        System.out.println("//" + "END:BRIGHT_OUTPUT");

        System.out.println("//" + "START:DARK_OUTPUT");
        camera.setFilters(Color::darker);
        printCaptured.accept("darker filter");
        System.out.println("//" + "END:DARK_OUTPUT");

        System.out.println("//" + "START:BOTH_OUTPUT");
        camera.setFilters(Color::brighter, Color::darker);
        printCaptured.accept("brighter & darker filter");
        System.out.println("//" + "END:BOTH_OUTPUT");

    }
}