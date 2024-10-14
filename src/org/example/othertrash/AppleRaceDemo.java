package org.example.othertrash;

public class AppleRaceDemo {
    public static void main(String[] args) {
        AppleBasket basket = new AppleBasket();
        // Создайте двух сборщиков (потоки) и подождите завершения
        Thread picker1 = new Thread(new Picker(basket, "Tom"));
        Thread picker2 = new Thread(new Picker(basket, "Bob"));

        while(basket.apples.get() < basket.maxApples) {
            if (!picker1.isAlive() || !picker2.isAlive()) {picker1.start();
                picker2.start();}

        }

        System.out.println("Гонка завершена! Всего собрано яблок: " +
                basket.apples.get());
    }
}
