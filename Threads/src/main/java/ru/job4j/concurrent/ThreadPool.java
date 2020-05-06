package ru.job4j.concurrent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    public static void main(String[] args) {
        SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
        ThreadPool threadPool = ThreadPool.newFixedThreadPool(3);
        threadPool.work(new Runnable() {
            @Override
            public void run() {
                System.out.println();
                for (int i = 0; i < 101; ++i) {
                    try {
                        Thread.sleep(100);
                        System.out.print("\rLoading1 : " + i + "%");
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();;
                    }
                }
            }
        });
        threadPool.work(new Runnable() {
            @Override
            public void run() {
                System.out.println();
                for (int i = 0; i < 101; ++i) {
                    try {
                        Thread.sleep(100);
                        System.out.print("\rLoading2 : " + i + "%");
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();;
                    }
                }
            }
        });
        threadPool.work(new Runnable() {
            @Override
            public void run() {
                System.out.println();
                for (int i = 0; i < 101; ++i) {
                    try {
                        Thread.sleep(100);
                        System.out.print("\rLoading3 : " + i + "%");
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();;
                    }
                }
            }
        });
    }

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
            tasks.wait();
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
