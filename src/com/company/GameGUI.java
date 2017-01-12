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
        //loginGUI.showLoginWindow();
        //loginGUI.showRegistrationWindow();
        loginGUI.showLobbyWindow();

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
        panel.setSize(400,400);
        //panel.setBackground(Color.darkGray);



        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Benutzername:");
        final JTextField nameText = new JTextField(12);
        JLabel passwortLabel = new JLabel("     Passwort:    ");
        final JPasswordField passwortText = new JPasswordField(12);
        JButton loginButton = new JButton("Login");
        JButton registrationButton = new JButton("Registrieren");



        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addComponent(nameText))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(passwortLabel)
                                .addComponent(passwortText))
                        .addComponent(loginButton)
                        .addComponent(registrationButton)

        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(nameLabel)
                        .addComponent(nameText))
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwortLabel)
                        .addComponent(passwortText))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(loginButton)
                        .addComponent(registrationButton))

        );

        panel.setLayout(layout);
        controlPanel.add(panel);
        loginWindow.setVisible(true);
    }
    public void showRegistrationWindow(){
        headerLabel.setText("Bitte Registrieren sie sich um Spielen zu können");

        JPanel panel = new JPanel();
        panel.setSize(500,500);
        //panel.setBackground(Color.darkGray);


        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("       Benutzername:     ");
        final JTextField nameText = new JTextField(12);
        JLabel passwortLabel = new JLabel("          Passwort:           ");
        final JPasswordField passwortText = new JPasswordField(12);
        JLabel passwort2Label = new JLabel("Passwort wiederholen:");
        final JPasswordField passwort2Text = new JPasswordField(12);

        JButton registrationButton = new JButton("Registrieren");


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addComponent(nameText))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(passwortLabel)
                                .addComponent(passwortText))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(passwort2Label)
                                .addComponent(passwort2Text))
                        .addComponent(registrationButton)

        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(nameLabel)
                        .addComponent(nameText))
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwortLabel)
                        .addComponent(passwortText))
                .addGroup(layout.createParallelGroup()
                        .addComponent(passwort2Label)
                        .addComponent(passwort2Text))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(registrationButton))

        );

        panel.setLayout(layout);
        controlPanel.add(panel);
        loginWindow.setVisible(true);
    }

    public void showLobbyWindow(){
        headerLabel.setText("Möchten sie einem Spiel beitreten?");

        JPanel panel = new JPanel();
        panel.setSize(400,400);
        //panel.setBackground(Color.darkGray);



        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        JButton disconnectButton = new JButton("Disconnect");
        JButton joinButton = new JButton("Join");



        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(disconnectButton)
                        .addComponent(joinButton)

        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(disconnectButton)
                        .addComponent(joinButton)

        );

        panel.setLayout(layout);
        controlPanel.add(panel);
        loginWindow.setVisible(true);
    }


}