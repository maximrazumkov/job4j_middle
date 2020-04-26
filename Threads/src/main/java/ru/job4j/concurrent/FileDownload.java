package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class FileDownload {
    public static void main(String[] args) {
        String file = args[0];
        int size = Integer.valueOf(args[1]);
        try (
                BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml");
        ) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (bytesRead > size) {
                    int secondForSleep = ((bytesRead / size) + 1) * 1000;
                    Thread.sleep(secondForSleep);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
