package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {}
        );
        Thread second = new Thread(
                () -> {}
        );
        System.out.println(String.format("%s: %s", "first", first.getState()));
        System.out.println(String.format("%s: %s", "second", first.getState()));
        first.start();
        second.start();
        while ((first.getState() != Thread.State.TERMINATED) || (second.getState() != Thread.State.TERMINATED)) {
            System.out.println(String.format("%s: %s", "first", first.getState()));
            System.out.println(String.format("%s: %s", "second", first.getState()));
        }
        System.out.println(String.format("%s: %s", "first", first.getState()));
        System.out.println(String.format("%s: %s", "second", first.getState()));
    }
}
