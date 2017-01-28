package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Observable;

public class ClientThread extends Observable implements Runnable{

    public PrintStream output;
    public String bet;
    private boolean finished = false;
    private Table table;
    private Socket client;
    private String name;

    public ClientThread(Socket client, Table table) {
        this.table = table;
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
        setChanged();
        notifyObservers();
    }

    @Override
    public void run(){
        try {
            OutputStream out = client.getOutputStream();
            output = new PrintStream(out, true);
            InputStream in = client.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            output.println("Willkommen auf dem Server");
            output.println("/Nameadd");
            name = input.readLine();
            output.println("Ihr Name ist: " + name);

            Player player = new Player(this);
            player.setName(name);
            table.addPlayer(player, table.playerList);

            setFinished(true);
            System.out.println("Der Spieler " + name + " wurde hinzugefügt!");

            while (true) {
                bet = input.readLine();
                String betSplit[] = bet.split(":");
                if (betSplit[0] == "/betset") {
                    bet = betSplit[1];
                }
            }
        } catch (IOException e) {
            System.out.println("IOException in run()...");
        }finally {
            close();
        }
    }

    public void sendData(String data) {
        output.println(data);
    }

    public void close(){
        output.println("/Disconnect");
    }
}