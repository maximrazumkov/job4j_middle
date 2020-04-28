package ru.job4j.concurrent.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        final String suject = "Notification %s to email %s. \n body = Add a new event to %s";
        final String body = "Add a new event to %s";
        pool.submit(() -> {
            send(
                    String.format(suject, user.getUsername(), user.getEmail()),
                    String.format(body, user.getUsername()),
                    user.getEmail()
            );
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String suject, String body, String email) {

    }
}
