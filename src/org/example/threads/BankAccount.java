package org.example.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    private int balance = 1000;

    public synchronized int getBalance() {
        return balance;
    }

    public void setBalance(int amount) {
        if (this.balance - amount < 0 ) throw new RuntimeException();
        balance = this.getBalance() + amount;
    }
}
