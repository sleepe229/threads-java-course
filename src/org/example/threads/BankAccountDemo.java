package org.example.threads;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BankAccountDemo {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        Random random = new Random();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                var firstDigit = ThreadLocalRandom.current().nextInt(-10, 10);
//                var secondDigit = random.nextInt(10);
                bankAccount.setBalance(firstDigit);
                System.out.println(firstDigit);
            });
            threads[i].start();
            threads[i].join();
        }
        System.out.print("итоговый баланс:" + bankAccount.getBalance());
    }
}
