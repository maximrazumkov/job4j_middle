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
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = bf.readLine()) != null) {
                output.append(line);
            }
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
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
        try (PrintWriter pr = new PrintWriter(new FileOutputStream(file))) {
            pr.write(content);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
