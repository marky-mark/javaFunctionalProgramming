package resources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//This is J7 non lambda way
public class Locking {
    Lock lock = new ReentrantLock(); //or mock
    protected void setLock(final Lock mock) {
        lock = mock;
    }
    public void doOp1() {
        lock.lock();
        try {
        } finally {
            lock.unlock();
        }
    }
}
