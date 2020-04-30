package ru.job4j.concurrent;

public class FileDownload {
    public static void main(String[] args) {
        String file = args[0];
        int size = Integer.valueOf(args[1]);
        Loader loader = new Loader();
        loader.load(file, "pom_tmp.xml", size);
    }
}
