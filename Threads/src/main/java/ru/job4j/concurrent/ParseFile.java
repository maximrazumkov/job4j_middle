package ru.job4j.concurrent;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        try (InputStream i = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = i.read()) > 0) {
                output.append((char) data);
            }
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        try (InputStream i = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public synchronized void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
