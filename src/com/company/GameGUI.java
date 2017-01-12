package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class GameGUI {
    private JFrame loginWindow;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public GameGUI(){
       prepareGUI();
    }
    public static void main(String[] args){
        GameGUI loginGUI = new GameGUI();
        loginGUI.showLoginWindow();
    }

    private void prepareGUI(){
        loginWindow = new JFrame("Online Poker");
        loginWindow.setSize(1280,768);
        loginWindow.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        loginWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        loginWindow.add(headerLabel);
        loginWindow.add(controlPanel);
        loginWindow.add(statusLabel);
        loginWindow.setVisible(true);
    }

    public void showLoginWindow(){
        headerLabel.setText("Herzlich Willkommen zum Online Poker " +
                "Texas Hold'em No Limit");

        JPanel panel = new JPanel();
        //panel.setSize(300,300);

        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Benutzername: ");
        JTextField nameText = new JTextField(6);
        JLabel passwortLabel = new JLabel("Passwort: ");
        JPasswordField passwortText = new JPasswordField(6);
        JButton loginButton = new JButton("Login");
        JButton registrationButton = new JButton("Registrieren");




        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup())
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(nameLabel)
                                .addComponent(passwortLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(nameText)
                                .addComponent(passwortText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(loginButton)
                                .addComponent(registrationButton))

        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(nameLabel)
                        .addComponent(nameText))
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwortLabel)
                        .addComponent(passwortText))
                .addGroup(layout.createParallelGroup()
                        .addComponent(loginButton))
                .addGroup(layout.createParallelGroup()
                        .addComponent(registrationButton))
        );

        panel.setLayout(layout);

        controlPanel.add(panel);
        loginWindow.setVisible(true);
    }
}
