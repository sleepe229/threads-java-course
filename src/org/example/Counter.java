package org.example;

import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private AtomicInteger count = new AtomicInteger(0);
    public void increment() {
        count.incrementAndGet();
    }
    public void decrement() {
        count.decrementAndGet();
    }


    public int getCount() {
        return count.get();
    }
}
