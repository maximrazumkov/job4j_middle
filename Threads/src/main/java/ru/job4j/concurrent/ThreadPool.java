package ru.job4j.concurrent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads;
    private final SimpleBlockingQueue<Runnable> tasks;

    public static ThreadPool newFixedThreadPool(int size) {
        List<Thread> threads = new ArrayList<>(size);
        SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
        for (int i = 0; i < size; ++i) {
            Thread thread = new InnerThread(tasks);
            thread.start();
            threads.add(thread);
        }
        ThreadPool threadPool = new ThreadPool(threads, tasks);
        return threadPool;
    }

    private ThreadPool(List<Thread> threads, SimpleBlockingQueue<Runnable> tasks) {
        this.threads = threads;
        this.tasks = tasks;
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        try {
            threads.forEach(thread -> thread.interrupt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class InnerThread extends Thread {
        private final SimpleBlockingQueue<Runnable> tasks;

        public InnerThread(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = tasks.poll();
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
