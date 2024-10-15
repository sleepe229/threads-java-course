package org.example.custom.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {
        final int VALUE = 10;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(VALUE);
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(VALUE);
        for (int i = 1; i <= VALUE/2; i++) {
            final AtomicInteger finalI = new AtomicInteger(i);
            new Thread(() -> {
                try {
                    startSignal.await();
                    buffer.put(finalI.get());
                    System.out.println(finalI + " in");
                    finishSignal.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        for (AtomicInteger i = new AtomicInteger(1); i.get() <= VALUE/2; i.incrementAndGet()) {
            new Thread(() -> {
                try {
                    startSignal.await();
                    System.out.println(buffer.take() + " out");
                    finishSignal.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        startSignal.countDown();
        finishSignal.await();
        System.out.println("all finished");

    }

}
