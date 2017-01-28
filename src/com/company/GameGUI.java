package com.company;


import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;


public class GameGUI extends JFrame {
    private JFrame gameWindow;
    public JPanel controlPanel;
    private JPanel playerCardsPanel;
    private JPanel notePanel;
    private JPanel interactionPanel;
    private JPanel playerListPanel;
    public JTextArea textArea;
    private Image img;
    private GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();


    public String name;
    public int cash;
    public int playerBet;
    public int tableBet;
    public boolean isAllIn;
    public boolean isInGame;
    public int pot;
    public String role;

    public int sizePlayerList=0;

    public GameGUI() {
        prepareGUI();
    }

    public static void main(String[] args) {
        GameGUI gameGUI = new GameGUI();
        //gameGUI.showLoginWindow();
        //gameGUI.showRegistrationWindow();
        gameGUI.showGameWindow();
    }

    private void prepareGUI() {

        gameWindow = new JFrame("Online Poker");
        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //gameWindow.setUndecorated(true);
        //gameWindow.setSize(1280,768);
        gameWindow.setLayout(gridBagLayout);


        gameWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        controlPanel = new JPanel();
        gridBagLayout.setConstraints(controlPanel, gridBagConstraints);
        gameWindow.add(controlPanel);

        gameWindow.setVisible(true);
    }


    public void showLoginWindow() {

        controlPanel.setLayout(gridBagLayout);

        //TextArea
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        textArea = new JTextArea();
        textArea.setText(   "Bitte melden sie sich an, oder registrieren sich!\n\n" +
                            "Auf dem Server befinden sich " + sizePlayerList + "/6 Spieler");
        textArea.setBackground(gameWindow.getBackground());
        gridBagLayout.setConstraints(textArea, gridBagConstraints);
        controlPanel.add(textArea);


        //UserName
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        JLabel username = new JLabel("Benutzername:");
        gridBagLayout.setConstraints(username, gridBagConstraints);
        controlPanel.add(username);
        //UserNameField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        JTextField usernameField = new JTextField(8);
        gridBagLayout.setConstraints(usernameField, gridBagConstraints);
        controlPanel.add(usernameField);


        //Password
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(5, 0, 10, 0);
        JLabel password = new JLabel("Passwort:");
        gridBagLayout.setConstraints(password, gridBagConstraints);
        controlPanel.add(password);
        //PasswordField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 10, 5);
        JPasswordField passwordField = new JPasswordField(8);
        gridBagLayout.setConstraints(passwordField, gridBagConstraints);
        controlPanel.add(passwordField);


        //Buttons
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.CENTER;
        //Login
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        JButton login = new JButton("Login and Join");
        gridBagLayout.setConstraints(login, gridBagConstraints);
        controlPanel.add(login);
        //Registration
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        JButton registration = new JButton("Registrieren");
        gridBagLayout.setConstraints(registration, gridBagConstraints);
        controlPanel.add(registration);


        gameWindow.add(controlPanel);
        gameWindow.setVisible(true);
    }

    public void showRegistrationWindow() {
        controlPanel.setLayout(gridBagLayout);

        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        textArea = new JTextArea();
        textArea.setText(   "Bitte registrieren sie sich, um Online-Poker spielen zu können!\n\n"+
                            "Auf dem Server befinden sich " + sizePlayerList + "/6 Spieler");
        textArea.setBackground(gameWindow.getBackground());
        gridBagLayout.setConstraints(textArea, gridBagConstraints);
        controlPanel.add(textArea);


        //UserName
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        JLabel username = new JLabel("Benutzername:");
        gridBagLayout.setConstraints(username, gridBagConstraints);
        controlPanel.add(username);
        //UserNameField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 3, 5, 5);
        JTextField usernameField = new JTextField(8);
        gridBagLayout.setConstraints(usernameField, gridBagConstraints);
        controlPanel.add(usernameField);


        //Password
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        JLabel password = new JLabel("Passwort:");
        gridBagLayout.setConstraints(password, gridBagConstraints);
        controlPanel.add(password);
        //PasswordField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 3, 5, 5);
        JPasswordField passwordField = new JPasswordField(8);
        gridBagLayout.setConstraints(passwordField, gridBagConstraints);
        controlPanel.add(passwordField);


        //Password
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(5, 0, 10, 0);
        JLabel passwordRepeat = new JLabel("Passwort wiederholen:");
        gridBagLayout.setConstraints(passwordRepeat, gridBagConstraints);
        controlPanel.add(passwordRepeat);
        //PasswordField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 3, 10, 5);
        JPasswordField passwordRepeatField = new JPasswordField(8);
        gridBagLayout.setConstraints(passwordRepeatField, gridBagConstraints);
        controlPanel.add(passwordRepeatField);

        //Registration
        gridBagConstraints.gridwidth = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 60, 5, 60);
        JButton registration = new JButton("Registrieren");
        gridBagLayout.setConstraints(registration, gridBagConstraints);
        controlPanel.add(registration);


        gameWindow.add(controlPanel);
        gameWindow.setVisible(true);

    }

    public void showGameWindow() {
        //FIRST_LINE_START, PAGE_START, FIRST_LINE_END, LINE_START, CENTER, LINE_END, LAST_LINE_START, PAGE_END und LAST_LINE_END
        //PlayerListPanel
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        playerListPanel = new JPanel();
        gridBagLayout.setConstraints(playerListPanel, gridBagConstraints);
        playerListPanel.setBackground(Color.RED);

//TODO Spielerliste bekommen
             int p=5;
        for (int i = 0; i <p ; i++) {
            //PlayerPanel
            gridBagConstraints.gridwidth = GridBagConstraints.LINE_START;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.anchor = GridBagConstraints.LINE_START;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            JPanel playerPanel = new JPanel();
            gridBagLayout.setConstraints(playerPanel, gridBagConstraints);
            playerPanel.setSize(500,500);
            playerListPanel.add(playerPanel);

            //Label
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.anchor = GridBagConstraints.LINE_START;
            gridBagConstraints.insets = new Insets(0, 0, 5, 0);
            String str =    "<html>Name: " + name +
                            "<br>Geld: " + cash +
                            "<br>Spielergebot: " + playerBet +
                            "<br>Rolle: " + role +
                            "<br>isInGame: " + isInGame +
                            "<br>isAllIn: " + isAllIn +  "</html>";
            JLabel label = new JLabel(str);
            gridBagLayout.setConstraints(label, gridBagConstraints);
            playerPanel.add(label);
            playerListPanel.updateUI();
        }

        //ControllPanel
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        controlPanel = new JPanel();
        gridBagLayout.setConstraints(controlPanel, gridBagConstraints);
        controlPanel.setBackground(Color.GRAY);

        //NotePanel
        gridBagConstraints.gridwidth = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        notePanel = new JPanel();
        gridBagLayout.setConstraints(notePanel, gridBagConstraints);
        notePanel.setBackground(Color.BLUE);
        notePanel.setMaximumSize(new Dimension(50,50));
        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        textArea = new JTextArea();

//TODO Autoscroll Coursor nach unten setzen!!
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setAutoscrolls(true);
        gridBagLayout.setConstraints(scrollPane, gridBagConstraints);

        textArea.setPreferredSize(new Dimension(500,250));
        textArea.setCaretPosition(textArea.getDocument().getLength());
        notePanel.add(scrollPane);

        notePanel.updateUI();



        //PlayerCardPanel
        gridBagConstraints.gridwidth = GridBagConstraints.PAGE_END;
        gridBagConstraints.gridheight = GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        playerCardsPanel = new JPanel();
        gridBagLayout.setConstraints(playerCardsPanel, gridBagConstraints);
        playerCardsPanel.setBackground(Color.LIGHT_GRAY);


        //InteractionPanel
        gridBagConstraints.gridwidth = GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        interactionPanel = new JPanel();
        gridBagLayout.setConstraints(interactionPanel, gridBagConstraints);
        interactionPanel.setBackground(Color.BLUE);

        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        String str2 =    "<html>Name: " + name +
                "<br>Geld: " + cash +
                "<br>Spielergebot: " + playerBet +
                "<br>Rolle: " + role +
                "<br>isInGame: " + isInGame +
                "<br>isAllIn: " + isAllIn +  "</html>";
        JLabel label = new JLabel(str2);

        gridBagLayout.setConstraints(label, gridBagConstraints);
        interactionPanel.add(label);
        interactionPanel.updateUI();


        insertImage(controlPanel, 5);
        insertImage(playerCardsPanel, 2);


        gameWindow.add(playerListPanel);
        gameWindow.add(controlPanel);
        gameWindow.add(notePanel);
        gameWindow.add(playerCardsPanel);
        gameWindow.add(interactionPanel);
        gameWindow.setVisible(true);
    }

    public void insertImage(JPanel imagePanel, int x) {
        URL resource = getClass().getClassLoader().getResource("../../Texture/CardTextures/blank.png");
        //URL resource = getClass().getClassLoader().getResource("C:/Users/Pascal/Documents/Informatik/Uni Jena/Semester III/Fortgeschrittenes Programmierpraktikum/Poker/Poker/out/production/Poker/CardTexture/ASS_CLUBS.png");


        try {
            img = ImageIO.read(resource);//Write path of your image here
        } catch (IOException ex) { ex.printStackTrace(); }

        for (int i = 0; i < x; i++) {
            final JPanel panel = new JPanel() {
                @Override
                public void paint(Graphics g) {
                    Graphics2D g2D = (Graphics2D) g;
                    AffineTransform transform = new AffineTransform();
                    transform.scale(0.25, 0.25);
                    g2D.drawImage(img, transform, null);
                }
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(img.getWidth(null) / 4, img.getHeight(null) / 4);
                }
            };
            imagePanel.add(panel);
        }
    }

    public Dimension percentSize(int percentW, int percentH){
        //public Dimension getPreferredSize() {
        int w = (int) (Math.round(percentW * 100.0/gameWindow.getWidth()));
        int h = (int) (Math.round(percentH * 100.0/gameWindow.getWidth()));
        return new Dimension(w, h);
    }
}
