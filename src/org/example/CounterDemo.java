//package org.example;
//
//public class CounterDemo {
//    static final int NUM_THREADS = 10;
//    static final int INCREMENTS_PER_THREAD = 5000000;
//    static Thread[] threads = new Thread[NUM_THREADS];
//    static Counter counter = new Counter();
//
//    public static void main(String[] args) throws InterruptedException {
//        oneThreadCheck(counter, NUM_THREADS, "increment", INCREMENTS_PER_THREAD, threads);
//        oneThreadCheck(counter, NUM_THREADS, "decrement", INCREMENTS_PER_THREAD, threads);
//        doubleThreadCheck(counter, INCREMENTS_PER_THREAD);
//    }
//
//    static void oneThreadCheck(Counter counter, int NUM_THREADS, String value, int INCREMENTS_PER_THREAD, Thread[] threads) throws InterruptedException {
//        for (int i = 0; i < NUM_THREADS; i++) {
//            final int threadNumber = i + 1;
//            threads[i] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    if (value.equals("increment")) {
//                        for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
//                            counter.increment();
//                        }
//                    } else {
//                        for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
//                            counter.decrement();
//                        }
//                    }
//                }
//            });
//            threads[i].start();
//            threads[i].join();
//        }
//        System.out.println("Фактическое значение: " + counter.getCount());
//    }
//    static void doubleThreadCheck(Counter counter, int INCREMENTS_PER_THREAD) throws InterruptedException {
//        Thread incrementThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
//                    counter.increment();
//                }
//            }
//        });
//
//        Thread decrementThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
//                    counter.decrement();
//                }
//            }
//        });
//        incrementThread.start();
//        decrementThread.start();
//        incrementThread.join();
//        decrementThread.join();
//        System.out.println(counter.getCount());
//    }
//}
