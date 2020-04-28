package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    for (int i = 0; i < 101; ++i) {
                        try {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + i + "%");
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();;
                        }
                    }
                }
        );
        thread.start();
    }
}
