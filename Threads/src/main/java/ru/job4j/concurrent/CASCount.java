package ru.job4j.concurrent;

import java.util.concurrent.atomic.AtomicReference;

public class CASCount {
    private AtomicReference<Integer> count = new AtomicReference<>(0);

    public Integer getCount() {
        return count.get();
    }

    public void increment() {
        Integer elemOld;
        Integer elemNew;
        do {
            elemOld = count.get();
            elemNew = elemOld + 1;
        } while (!count.compareAndSet(elemOld, elemNew));
    }
}
