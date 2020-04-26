package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final SimpleLinkedList<T> singleLockList = new SimpleLinkedList<>();

    public synchronized void add(T value) {
        singleLockList.add(value);
    }

    public synchronized T get(int index) {
        return singleLockList.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(singleLockList).iterator();
    }

    private SimpleLinkedList<T> copy(SimpleLinkedList<T> singleLockList) {
        SimpleLinkedList<T> newSingleLockList = new SimpleLinkedList<>();
        singleLockList.forEach(singleLock -> newSingleLockList.add(singleLock));
        return newSingleLockList;
    }
}