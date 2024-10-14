package org.example.othertrash;

class Racer implements Runnable {
    private String name;

    public Racer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        int a = 1;
        for (int i = 0; i <1000000; i++){a++;}
        long endTime = System.nanoTime();
        System.out.printf("racer %s: %s\n", this.name, (endTime-startTime)/(1000000));
    }
}

