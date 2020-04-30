package ru.job4j.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class CASCount {
    private AtomicInteger count = new AtomicInteger();

    public int getCount() {
        return count.intValue();
    }

    public void increment() {
        count.incrementAndGet();
    }
}
