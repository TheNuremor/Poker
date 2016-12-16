package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket clientSocket;
    public Socket client;
    public String str = null;
    Table table = new Table();



    public ServerThread (Socket client, ServerSocket server, boolean gameRunning) {
        server = server;
        client = client;
        gameRunning =gameRunning;
    }


    @Override
    public void run(){
        login();

    }

    public void login(){
        try {

            OutputStream out = client.getOutputStream();
            PrintStream output  = new PrintStream(out, true);

            InputStream in = client.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            System.out.println("Willkommen auf dem Server :)");
            System.out.println("\n\tBitte geben sie ihren Namen ein:\n");

            str = input.readLine();
            System.out.printf("\n\n\tIhr Name ist:", str);

            if (!gameRunning) {
                Player player = new Player();
                player.setName(str);
                table.addPlayer(player, table.playerList);

                System.out.printf("Der Spieler %s wurde zur Spielerliste hinzugefügt!", str);
            }else {
                System.out.println("Es sind schon zu viele Spieler im Spiel.");
            }
            server.checkLogin();

        } catch (IOException e) {
            System.out.println("IOException in run()...");
        }finally {
            close();
        }
    }

    public void sendDate(){

    }


    public void close(){

    }

}