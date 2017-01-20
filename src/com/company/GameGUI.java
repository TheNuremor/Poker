package com.company;


import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;


public class GameGUI extends JFrame{
    private JFrame gameWindow;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private Image img;
    private Image img2;

    public GameGUI(){
       prepareGUI();
    }
    public static void main(String[] args){
        GameGUI gameGUI = new GameGUI();
        //gameGUI.showLoginWindow();
        //gameGUI.showRegistrationWindow();
        //gameGUI.showLobbyWindow();
        gameGUI.showGameWindow();

    }

    private void prepareGUI(){
        gameWindow = new JFrame("Online Poker");
        gameWindow.setSize(1280,768);
        gameWindow.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        gameWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        gameWindow.add(headerLabel);
        gameWindow.add(controlPanel);
        gameWindow.add(statusLabel);
        gameWindow.setVisible(true);
    }

    public void showLoginWindow(){
        headerLabel.setText("Herzlich Willkommen zum Online Poker " +
                "Texas Hold'em No Limit");

        JPanel panel = new JPanel();
        panel.setSize(400,400);
        panel.setBackground(Color.gray);



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
        gameWindow.setVisible(true);
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
        gameWindow.setVisible(true);
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
        gameWindow.setVisible(true);
    }


    public void showGameWindow() {

        headerLabel.setText("Control in action: ImageIcon");
        statusLabel.setText("Test");


        //panel.setSize(600,400);
        //controlPanel.setBackground(Color.gray);


        URL resource = getClass().getResource("../../CardTextures/blank2.png");
        URL resource2 = getClass().getResource("../../CardTextures/blank.png");

        try {
            img = ImageIO.read(resource);//Write path of your image here
            img2 = ImageIO.read(resource2);
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);//Change ClassName to your class Name
        }
        for (int i = 0; i < 5; i++) {

        final JPanel panel2 = new JPanel() {
            @Override
            public void paint(Graphics g) {
                Graphics2D g2D = (Graphics2D) g;
                AffineTransform transform = new AffineTransform();
                transform.scale(0.25, 0.25);
                g2D.drawImage(img, transform, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(img.getWidth(null) / 2, img.getHeight(null) / 2);
            }
        };
        controlPanel.add(panel2);

        }

        final JPanel panel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                Graphics2D g2D = (Graphics2D) g;
                AffineTransform transform = new AffineTransform();
                transform.scale(0.25, 0.25);
                g2D.drawImage(img, transform, null);
            }

            /*@Override
            protected void paintComponent(Graphics g){
                Graphics g2 = g.create();
                g2.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                g2.dispose();
            }*/

            @Override
            public Dimension getPreferredSize(){
                return new Dimension(img.getWidth(null)/2, img.getHeight(null)/2);
            }
        };

        controlPanel.add(panel);

        //controlPanel.add(panel2);
        //Add other components to mainPanel
        //controlPanel.updateUI();
        gameWindow.add(controlPanel);
        gameWindow.setVisible(true);
    }





}
