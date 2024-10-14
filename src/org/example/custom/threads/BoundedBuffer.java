package org.example.custom.threads;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class BoundedBuffer<T> {
    private final T[] buffer;
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger in = new AtomicInteger(0);
    private AtomicInteger out = new AtomicInteger(-1);
    private CountDownLatch startSignal;
    private CountDownLatch finishSignal;


    @SuppressWarnings("unchecked")
    public BoundedBuffer(int size, CountDownLatch startSignal,
                         CountDownLatch finishSignal) {
        buffer = (T[]) new Object[size];
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    public synchronized void put(T element) throws InterruptedException {
        if (count.get() == buffer.length) {
            wait();
        }
        synchronized (buffer) {
            buffer[in.get()] = element;
            count.getAndIncrement();
            in.getAndIncrement();
            out.incrementAndGet();
            notifyAll();
        }
    }

    public synchronized T take() throws InterruptedException {
        if (count.get() == 0) {
            wait();
        }
        synchronized (buffer) {
            T element = buffer[out.get()];
            buffer[out.get()] = null;
            count.getAndDecrement();
            out.getAndDecrement();
            notifyAll();
            return element;
        }
    }
}
