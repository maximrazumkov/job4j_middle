package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASQueue<T> {

    private final AtomicReference<Node<T>> head = new AtomicReference<>();
    private final AtomicReference<Node<T>> tail = new AtomicReference<>();

    public void push(T value) {
        Node<T> newElement = new Node<>(value);
        Node<T> ref;
        do {
            ref = head.get();
            newElement.next = ref;
        } while (!head.compareAndSet(ref, newElement));
    }

    public T poll() {
        Node<T> ref;
        Node<T> temp;
        do {
            if (tail.get() == null && !setTail()) {
                throw new IllegalStateException("Queue is empty");
            }
            ref = tail.get();
            temp = ref.next;
        } while (!tail.compareAndSet(ref, temp));
        ref.next = null;
        return ref.value;
    }

    private boolean setTail() {
        boolean result = false;
        Node<T> newElement;
        Node<T> ref;
        Node<T> temp;
        while ((newElement = head.get()) != null) {
            do {
                ref = tail.get();
                temp = newElement.next;
                newElement.next = ref;
            } while (!tail.compareAndSet(ref, newElement) || !head.compareAndSet(newElement, temp));
            result = true;
        }
        return result;
    }

    private static final class Node<T> {
        final T value;

        Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }
}
