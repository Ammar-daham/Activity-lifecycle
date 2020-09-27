package com.example.tehtava5_1;

public class Counter {
    private int counter;
    private int minmum;
    private int maximun;

    public Counter(int minmum, int maximum) {
        this.maximun = maximum;
        this.minmum = minmum;
        this.counter = 0;
    }

    public int getValue() {
        return this.counter;
    }

    public void initCounter() {
        this.counter = 0;
    }

    public void plusCounter() {
        if (counter < maximun) {
            this.counter = this.counter + 1;
        }
    }
}
