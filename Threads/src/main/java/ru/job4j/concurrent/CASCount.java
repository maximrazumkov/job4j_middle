package ru.job4j.concurrent;

import java.util.concurrent.atomic.AtomicReference;

public class CASCount {
    private AtomicReference<Integer> count = new AtomicReference<>();

    public int getCount() {
        return count.get();
    }

    public void increment() {
        Integer elem;
        do {
            elem = count.get();
        } while (count.compareAndSet(elem, ++elem));
    }
}
