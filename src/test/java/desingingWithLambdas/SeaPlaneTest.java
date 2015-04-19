package desingingWithLambdas;

import org.junit.Test;

public class SeaPlaneTest {

    @Test
    public void testSeaPlane() throws Exception {
        SeaPlane seaPlane = new SeaPlane();
        seaPlane.takeOff();
        seaPlane.turn();
        seaPlane.cruise();
        seaPlane.land();

    }
}