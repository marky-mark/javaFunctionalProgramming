package resources;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;
import static resources.Locker.runLocked;

public class LockerTest {

    @Test
    public void testLock() throws Exception {
        Lock lock = new ReentrantLock();
        runLocked(lock, () -> System.out.print("here"));
    }
}