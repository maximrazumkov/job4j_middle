package ru.job4j.concurrent;

public class ParallelSearch {

    public static void main(String[] args) throws Exception {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        try {
                            System.out.println(queue.poll());
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                            throw e;
                        }
                    }
                }
        );
        consumer.start();
        Thread produce = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        produce.start();
        produce.join();
        consumer.interrupt();
    }
}
