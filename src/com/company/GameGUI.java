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
    private JPanel playerPanel;
    private Image img;
    private GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();


    public GameGUI(){
       prepareGUI();
    }

    public static void main(String[] args){
        GameGUI gameGUI = new GameGUI();
        //gameGUI.showLoginWindow();
        //gameGUI.showRegistrationWindow();
        gameGUI.showLobbyWindow();
        //gameGUI.showGameWindow();


    }

    private void prepareGUI(){

        gameWindow = new JFrame("Online Poker");
        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //gameWindow.setUndecorated(true);
        //gameWindow.setSize(1280,768);
        gameWindow.setLayout(gridBagLayout);

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        gameWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        controlPanel = new JPanel();
        gridBagLayout.setConstraints(controlPanel, gridBagConstraints);
        gameWindow.add(controlPanel);


        gameWindow.add(headerLabel);
        //gameWindow.add(controlPanel);
        gameWindow.add(statusLabel);
        gameWindow.setVisible(true);
    }

    public void testWindow(){
        //Einstellen eines GridBagLayouts
        GridBagLayout gridBagLayout = new GridBagLayout();
        getContentPane().setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        //Konstruktor Insets: oben, links, unten, rechts
        gridBagConstraints.insets = new Insets(0,0,5,0);
        JLabel label = new JLabel("Autobörse: Kfz-Verkauf");
        gridBagLayout.setConstraints(label, gridBagConstraints);
        getContentPane().add(label);

        //Label
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        label = new JLabel("Fahrzeugtyp:");
        gridBagLayout.setConstraints(label, gridBagConstraints);
        getContentPane().add(label);

        //ComboBox für Fahrzeugtyp
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3;
        gridBagConstraints.insets = new Insets( 0, 5, 0, 5);
        String[] strArray = {"Sportwagen", "Limosine", "Coupe", "Transporter", "Bus", "Lkw", "Motorrad"};
        JComboBox cmbType = new JComboBox(strArray);
        gridBagLayout.setConstraints(cmbType, gridBagConstraints);
        getContentPane().add(cmbType);

        //Label
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.weightx = 1;
        label = new JLabel("Beschreibung:");
        gridBagLayout.setConstraints(label, gridBagConstraints);
        getContentPane().add(label);

        //TextArea für die Beschreibung
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10;
        gridBagConstraints.weighty = 10;
        gridBagConstraints.insets = new Insets( 0, 5, 0, 5);
        JTextArea txtExpl = new JTextArea("Beschreibung");
        JScrollPane scrollPane = new JScrollPane(txtExpl);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gridBagLayout.setConstraints(scrollPane, gridBagConstraints);
        getContentPane().add(scrollPane);

        //Label
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        label = new JLabel("Preis:");
        gridBagLayout.setConstraints(label, gridBagConstraints);
        getContentPane().add(label);

        //Textfeld für den Preis
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3;
        gridBagConstraints.insets = new Insets( 0, 5, 0, 5);
        JTextField txtPrice = new JTextField("0,00");
        gridBagLayout.setConstraints(txtPrice, gridBagConstraints);
        getContentPane().add(txtPrice);

        //Buttons
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;

        gridBagConstraints.fill = GridBagConstraints.NONE;

        JButton btnSend = new JButton("Senden");
        gridBagLayout.setConstraints(btnSend, gridBagConstraints);
        getContentPane().add(btnSend);

        JButton btnCancel = new JButton("Abbrechen");
        gridBagLayout.setConstraints(btnCancel, gridBagConstraints);
        getContentPane().add(btnCancel);

        gameWindow.add(getContentPane());
        gameWindow.setVisible(true);

        //setSize(300,400);
        //setLocation(50,50);
        //setVisible(true);

    }

    public void showLoginWindow(){

        controlPanel.setLayout(gridBagLayout);

        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        //Konstruktor Insets: oben, links, unten, rechts
        gridBagConstraints.insets = new Insets(0,0,20,0);
        JLabel header = new JLabel("Bitte melden sie sich an, oder registrieren sich!");
        gridBagLayout.setConstraints(header, gridBagConstraints);
        controlPanel.add(header);

        //Label
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0,0,5,0);
        JLabel username = new JLabel("Benutzername:");
        gridBagLayout.setConstraints(username, gridBagConstraints);
        controlPanel.add(username);


        //TextArea für die Beschreibung
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10;
        gridBagConstraints.weighty = 10;
        gridBagConstraints.insets = new Insets( 0, 5, 0, 5);

        JTextField usernameField = new JTextField( 8);
        gridBagLayout.setConstraints(usernameField, gridBagConstraints);
        controlPanel.add(usernameField);

        //Password
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0,0,5,0);
        JLabel password = new JLabel("Passwort:");
        gridBagLayout.setConstraints(password, gridBagConstraints);
        controlPanel.add(password);


        //PasswordField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10;
        gridBagConstraints.weighty = 10;
        gridBagConstraints.insets = new Insets( 0, 5, 0, 5);

        JPasswordField passwordField = new JPasswordField( 8);
        gridBagLayout.setConstraints(passwordField, gridBagConstraints);
        controlPanel.add(passwordField);

        //Buttons
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.CENTER;

        gridBagConstraints.insets = new Insets(0,0,0,0);
        JButton login = new JButton("Login");
        gridBagLayout.setConstraints(login, gridBagConstraints);
        controlPanel.add(login);

        gridBagConstraints.insets = new Insets(0,5,0,0);
        JButton registration = new JButton("Registrieren");
        gridBagLayout.setConstraints(registration, gridBagConstraints);
        controlPanel.add(registration);

        gameWindow.add(controlPanel);
        gameWindow.setVisible(true);
    }


    public void showRegistrationWindow(){
        controlPanel.setLayout(gridBagLayout);

        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        //Konstruktor Insets: oben, links, unten, rechts
        gridBagConstraints.insets = new Insets(0,0,20,0);
        JLabel header = new JLabel("Bitte registrieren sie sich, um Online-Poker spielen zu können!");
        gridBagLayout.setConstraints(header, gridBagConstraints);
        controlPanel.add(header);

        //Label
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0,0,5,0);
        JLabel username = new JLabel("Benutzername:");
        gridBagLayout.setConstraints(username, gridBagConstraints);
        controlPanel.add(username);


        //TextArea für die Beschreibung
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10;
        gridBagConstraints.weighty = 10;
        gridBagConstraints.insets = new Insets( 0, 5, 0, 5);

        JTextField usernameField = new JTextField( 8);
        gridBagLayout.setConstraints(usernameField, gridBagConstraints);
        controlPanel.add(usernameField);

        //Password
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0,0,5,0);
        JLabel password = new JLabel("Passwort:");
        gridBagLayout.setConstraints(password, gridBagConstraints);
        controlPanel.add(password);


        //PasswordField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10;
        gridBagConstraints.weighty = 10;
        gridBagConstraints.insets = new Insets( 0, 5, 0, 5);

        JPasswordField passwordField = new JPasswordField( 8);
        gridBagLayout.setConstraints(passwordField, gridBagConstraints);
        controlPanel.add(passwordField);
        //Password
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0,0,5,0);
        JLabel passwordRepeat = new JLabel("Passwort wiederholen:");
        gridBagLayout.setConstraints(passwordRepeat, gridBagConstraints);
        controlPanel.add(passwordRepeat);


        //PasswordField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10;
        gridBagConstraints.weighty = 10;
        gridBagConstraints.insets = new Insets( 0, 5, 0, 5);

        JPasswordField passwordRepeatField = new JPasswordField( 8);
        gridBagLayout.setConstraints(passwordRepeatField, gridBagConstraints);
        controlPanel.add(passwordRepeatField);

        //Buttons
        gridBagConstraints.gridwidth = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0,50,0,0);
        JButton registration = new JButton("Registrieren");
        gridBagLayout.setConstraints(registration, gridBagConstraints);
        controlPanel.add(registration);


        gameWindow.add(controlPanel);
        gameWindow.setVisible(true);

    }

    public void showLobbyWindow(){
        int x = 0;
        controlPanel.setLayout(gridBagLayout);

        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        JLabel header = new JLabel("Möchten sie einem Spiel Beitreten?");
        gridBagLayout.setConstraints(header, gridBagConstraints);
        controlPanel.add(header);

        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(40,0,40,0);
        JLabel server = new JLabel("Auf dem Server befinden sich "+ x + "/6 Spieler");
        gridBagLayout.setConstraints(server, gridBagConstraints);
        controlPanel.add(server);

        //Buttons
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.CENTER;

        gridBagConstraints.insets = new Insets(0,0,0,0);
        JButton disconnect = new JButton("Disconnect");
        gridBagLayout.setConstraints(disconnect, gridBagConstraints);
        controlPanel.add(disconnect);

        gridBagConstraints.insets = new Insets(0,5,0,0);
        JButton join = new JButton("Join");
        gridBagLayout.setConstraints(join, gridBagConstraints);
        controlPanel.add(join);

        gameWindow.add(controlPanel);
        gameWindow.setVisible(true);
    }


    public void showGameWindow() {

        gameWindow.setLayout(new GridBagLayout());

        headerLabel.setText("Control in action: ImageIcon");

        //controlPanel.setBackground(Color.gray);
        playerPanel = new JPanel();
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;

        insertImage(controlPanel, 5);
        insertImage(playerPanel, 2);


        //Add other components to mainPanel

        gameWindow.add(headerLabel, gridBagConstraints);
        gameWindow.add(controlPanel, gridBagConstraints);
        gameWindow.add(playerPanel, gridBagConstraints);
        gameWindow.setVisible(true);
    }

    public void insertImage(JPanel imagePanel, int x){
        URL resource = getClass().getResource("../../CardTextures/blank2.png");

        try {
            img = ImageIO.read(resource);//Write path of your image here

        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);//Change ClassName to your class Name
        }
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
                    return new Dimension(img.getWidth(null)/4 , img.getHeight(null)/4);
                }
            };
            imagePanel.add(panel);
        }
        /*@Override
            protected void paintComponent(Graphics g){
                Graphics g2 = g.create();
                g2.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                g2.dispose();
            }*/
    }



}
