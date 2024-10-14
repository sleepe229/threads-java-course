package org.example;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AppleBasket {
    AtomicInteger apples = new AtomicInteger(0);
    final int maxApples = 20;
    // Синхронизированный метод для добавления яблок в корзину
    public synchronized boolean pickApple(Picker picker) {
        if (apples.get() < maxApples) {
            apples.incrementAndGet();
            picker.count++;
        }
        return apples.get() < maxApples;
    }
}
