package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) {

        Socket clientSocket = null;

        try {
            clientSocket = new Socket("localHost", 1111);
            System.out.println("Client connected");
            OutputStream out = clientSocket.getOutputStream();
            PrintStream output  = new PrintStream(out, true);

            InputStream in = clientSocket.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(in));
            BufferedReader consoleinput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                while (input.ready()) {
                    String string = input.readLine();
                    switch (string){
                        case "/Nameadd":
                            System.out.println("Bitte Namen eingeben: ");
                            output.println(consoleinput.readLine());
                            break;
                        case "/Disconnect":
                            System.out.println("Goodbye!");
                            System.exit(0);
                            break;
                        case "/Bet":
                            System.out.println("Bitte Gebot eingeben: ");
                            output.println("/betset:" + consoleinput.readLine());
                            break;
                        default:
                            System.out.println(string);
                    }

                }
            }

        } catch (UnknownHostException e) {
            System.out.println("Unknown Host...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException...");
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                    System.out.println("Client disconnected...");
                } catch (IOException e) {
                    System.out.println("Client can't disconnect...");
                    e.printStackTrace();
                }
            }
        }
    }
}