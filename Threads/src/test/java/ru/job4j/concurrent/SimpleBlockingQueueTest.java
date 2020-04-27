package ru.job4j.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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


    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final List<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        List<Integer> result = Arrays.asList(0, 1, 2, 3, 4);
        assertThat(buffer, is(result));
    }
}
