package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket client;

    public ServerThread (Socket accept) {
        client = accept;
    }



    @Override
    public void run(){
        try {
            OutputStream out = client.getOutputStream();
            PrintStream output  = new PrintStream(out, true);

            InputStream in = client.getInputStream();
            System.out.println("Verfügbare Bytes: " + in.available());
            BufferedReader input = new BufferedReader(new InputStreamReader(in));
            System.out.println("Willkommen auf dem Server :)");
            login();



            System.out.println("Hello World");

        } catch (IOException e) {
            System.out.println("IOException in run()...");
        }finally {
            close();
        }
    }
    public void login(){

    }

    public void close(){

    }

}
