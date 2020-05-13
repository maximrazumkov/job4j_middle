package ru.job4j.concurrent;

import org.junit.Test;

import java.util.SortedMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    private static class CustomThread extends Thread {
        private final CASCount casCount;
        private final int iteractions;

        public CustomThread(final CASCount casCount, final int iteractions) {
            this.casCount = casCount;
            this.iteractions = iteractions;
        }

        @Override
        public void run() {
            for (int i = 0; i < iteractions; ++i) {
                casCount.increment();
            }
        }
    }

    @Test
    public void whenCountSomeThreadsThat() throws Exception {
        CASCount casCount = new CASCount();
        Thread th1 = new CustomThread(casCount, 1000);
        Thread th2 = new CustomThread(casCount, 1000);
        Thread th3 = new CustomThread(casCount, 1000);
        Thread th4 = new CustomThread(casCount, 1000);
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th1.join();
        th2.join();
        th3.join();
        th4.join();
        Integer intr = casCount.getCount();
        assertThat(intr, is(4000));
    }
}
