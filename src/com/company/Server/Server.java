package com.company.Server;

import com.company.Game.Player;
import com.company.Game.Table;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {


    public static void main(String args[]) throws IOException {

        ServerSocket server = new ServerSocket(1111);  //Server wird mit Port erstellt.
        System.out.println("Server gestartet");

        List<Player> playerQueue = new LinkedList<>();
        List<Table> games = new LinkedList<>();

        while (true) {
            Socket client;

            try {
                if (playerQueue.size() < 4) {
                    client = server.accept();
                    playerQueue.add(new Player(client));
                }
                if (playerQueue.size() == 4) {
                    Table game = new Table();
                    playerQueue.forEach(game::addPlayer);
                    playerQueue.clear();
                    
                    game.start();
                    games.add(game);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
