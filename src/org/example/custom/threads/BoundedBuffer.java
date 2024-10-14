package org.example.custom.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class BoundedBuffer<T> {
    private final T[] buffer;
    private final AtomicInteger count = new AtomicInteger(0);
    private final AtomicInteger in = new AtomicInteger(0);
    private final AtomicInteger out = new AtomicInteger(0);



    @SuppressWarnings("unchecked")
    public BoundedBuffer(int size) {
        buffer = (T[]) new Object[size];
    }

    public synchronized void put(T element) throws InterruptedException {
        if (count.get() == buffer.length) {
            wait();
        }
        buffer[in.get()] = element;
        count.getAndIncrement();
        in.set((in.get() + 1) % buffer.length);
        notifyAll();
    }
    public synchronized T take() throws InterruptedException {
        if (count.get() == 0) {
            wait();
        }
        T element = buffer[out.get()];
        buffer[out.get()] = null;
        count.getAndDecrement();
        out.set((out.get() + 1) % buffer.length);
        notifyAll();
        return element;
    }

}
