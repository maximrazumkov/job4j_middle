package ru.job4j.concurrent.switcher;

public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier masterSlaveBarrier = new MasterSlaveBarrier(false, true);
        Thread first = new Thread(
                () -> {
                    while (true) {
                        masterSlaveBarrier.tryMaster();
                        masterSlaveBarrier.done();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        masterSlaveBarrier.trySlave();
                        masterSlaveBarrier.done();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
