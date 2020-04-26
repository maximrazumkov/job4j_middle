package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws Exception {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        System.out.println(progress.getState());
        Thread.sleep(5000);
        progress.interrupt();
        System.out.println(progress.getState());
    }

    @Override
    public void run() {
       while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.print("\r load: " + '-');
                Thread.sleep(500);
                System.out.print("\r load: " + '\\');
                Thread.sleep(500);
                System.out.print("\r load: " + '|');
                Thread.sleep(500);
                System.out.print("\r load: " + '/');
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
