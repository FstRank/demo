package com.thread.test;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipeStreamTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter();

        writer.connect(reader);

        new Thread(() -> {
            int count = 0;
            try {
                while (true) {
                    count++;
                    try {
                        String data = String.valueOf(count);
                        System.out.println("Write:" + data);
                        writer.write(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(2000);

        new Thread(() -> {
            char[] byteArray = new char[20];
            while (true) {
                int readLength = 0;
                try {
                    readLength = reader.read(byteArray);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (readLength != -1) {
                    String newData = new String(byteArray, 0, readLength);
                    System.out.println("Read:" + newData);
                }
            }
        }).start();
    }
}