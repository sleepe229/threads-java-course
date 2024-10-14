//package org.example;
//
//import java.util.concurrent.CountDownLatch;
//
//class SynchronizedRacer implements Runnable {
//    private String name;
//    private CountDownLatch startSignal;
//    private CountDownLatch finishSignal;
//
//    public SynchronizedRacer(String name, CountDownLatch startSignal, CountDownLatch finishSignal) {
//        this.name = name;
//        this.startSignal = startSignal;
//        this.finishSignal = finishSignal;
//    }
//
//    @Override
//    public void run() {
//        try {
//            startSignal.await(); // Ждем сигнала старта
//            System.out.println(name + " начал гонку");
//            Thread[] threads = new Thread[10];
//            for (int i = 0; i < 10; i++) {
//                threads[i] = new Thread(new SynchronizedRacer(name + "-" + i, startSignal, finishSignal));
//            }
//            finishSignal.countDown(); // Сигнализируем о завершении
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}