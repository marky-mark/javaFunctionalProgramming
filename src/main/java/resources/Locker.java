package resources;

import java.util.concurrent.locks.Lock;

//Using Lambda instead of Locking.java
public class Locker {
    public static void runLocked(Lock lock, Runnable block) {
        lock.lock();
        try {
            block.run();
        } finally {
            lock.unlock();
        }
    }
}