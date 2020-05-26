package ru.job4j.concurrent.switcher;

public class MasterSlaveBarrier {

    private boolean isMaster;
    private boolean isSlave;

    public MasterSlaveBarrier(Boolean isMaster, Boolean isSlave) {
        this.isMaster = isMaster;
        this.isSlave = isSlave;
    }

    public synchronized void tryMaster() {
        while (isMaster) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread A");
    }

    public synchronized void trySlave() {
        while (isSlave) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread B");
    }

    public synchronized void done() {
        isMaster = !isMaster;
        isSlave = !isSlave;
        this.notifyAll();
    }
}
