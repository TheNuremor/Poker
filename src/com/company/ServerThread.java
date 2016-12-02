package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread {


    public static void main(String[] args) throws Exception {
        new ServerSocket(3141);

        final Socket t = new Socket("localhost", 3141);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Hello World");
                    System.out.println(t.getInputStream().read());
                    System.out.println("Hello Server");
                } catch (IOException e) {
                    System.out.println("Hi");
                }
            }
        }).start();

        Thread.sleep(2000);
        t.close();
    }
}
