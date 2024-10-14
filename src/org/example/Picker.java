package org.example;

public class Picker implements Runnable {
    private final AppleBasket basket;
    private final String name;
    int count = 0;

    public Picker(AppleBasket basket, String name) {
        this.basket = basket;
        this.name = name;
    }
    @Override
    public void run() {
        while (basket.pickApple(this)) {
            try {
                Thread.sleep(100);
                System.out.println(this.name + ": picked apple");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(this.name + ": picked apples " +this.count);
    }
}

