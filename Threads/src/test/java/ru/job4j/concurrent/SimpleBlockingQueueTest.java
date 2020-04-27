package ru.job4j.concurrent;

import org.junit.Test;

import java.util.Random;

public class SimpleBlockingQueueTest {

    private class Produce extends Thread {
        private final SimpleBlockingQueue<Integer> queue;

        private Produce(final SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; ++i) {
                queue.offer(new Random().nextInt(100));
            }
        }
    }

    private class Consumer extends Thread {
        private final SimpleBlockingQueue<Integer> queue;

        private Consumer(final SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10002; ++i) {
                System.out.println(queue.poll());
            }
        }
    }

    @Test
    public void peekAndPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread produce = new Produce(queue);
        Thread consumer = new Consumer(queue);
        produce.start();
        consumer.start();
        produce.join();
        queue.offer(100500);
        consumer.join();
    }
}
