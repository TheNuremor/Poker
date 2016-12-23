package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {


    public static void main(String args[]) throws IOException {

        ServerSocket server = new ServerSocket(1111);  //Server wird mit Port erstellt.
        System.out.println("Server gestartet");

        Table table = new Table();
        List<ClientThread> threadList = new LinkedList<>();
        ClientObserver clob = new ClientObserver(table, threadList);

        while (true) {
            Socket client = null;

            try {
                if (threadList.size() < 3) {
                    client = server.accept();
                    ClientThread clientThread = new ClientThread(client, table);
                    clientThread.addObserver(clob);
                    threadList.add(clientThread);

                    new Thread(clientThread).start();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!table.allPlayersFinished()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                table.startGame();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
