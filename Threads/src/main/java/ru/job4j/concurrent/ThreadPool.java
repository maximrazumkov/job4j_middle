package ru.job4j.concurrent;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public void work(Runnable job) {
        tasks.offer(job);
        threads.forEach(Thread::notify);
    }

    public void shutdown() {
        threads.forEach(thread -> {
            try {
                thread.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
