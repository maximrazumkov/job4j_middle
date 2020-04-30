package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URL;

public class Loader {
    public boolean load(String src, String desc, int size) {
        boolean result = true;
        try (
                BufferedInputStream br = new BufferedInputStream(new URL(src).openStream());
                FileOutputStream fw = new FileOutputStream(desc);
        ) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = br.read(dataBuffer, 0, 1024)) != -1) {
                fw.write(dataBuffer, 0, bytesRead);
                if (bytesRead > size) {
                    int secondForSleep = ((bytesRead / size) + 1) * 1000;
                    Thread.sleep(secondForSleep);
                }
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
