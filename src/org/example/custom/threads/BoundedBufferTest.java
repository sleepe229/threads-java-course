package org.example.custom.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class BoundedBufferTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(10);
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5, startSignal, finishSignal);
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    startSignal.await();
                    buffer.put(finalI);
                    System.out.println(finalI + " in");
                    finishSignal.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        for (int i = 1; i <= 5; i++) {
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
