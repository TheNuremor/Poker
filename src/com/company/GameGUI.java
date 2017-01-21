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
        //gameGUI.showLobbyWindow();
        //gameGUI.showGameWindow();
        gameGUI.login();

    }

    private void prepareGUI(){




        gameWindow = new JFrame("Online Poker");
        gameWindow.setSize(1280,768);
        gameWindow.setLayout(gridBagLayout);

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        gameWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();


        gameWindow.add(headerLabel);
        gameWindow.add(controlPanel);
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
    public void login(){

        controlPanel.setLayout(gridBagLayout);

        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        //Konstruktor Insets: oben, links, unten, rechts
        gridBagConstraints.insets = new Insets(0,0,5,0);
        JLabel header = new JLabel("Bitte melden sie sich an, oder registrieren sich!");
        gridBagLayout.setConstraints(header, gridBagConstraints);
        controlPanel.add(header);

        //Label
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0,0,0,0);
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

        JTextField usernameField = new JTextField( 12);
        gridBagLayout.setConstraints(usernameField, gridBagConstraints);
        controlPanel.add(usernameField);

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
        controlPanel.add(panel, GridBagConstraints.CENTER);
        gameWindow.setVisible(true);
    }

    public void showRegistrationWindow(){
        headerLabel.setText("Bitte Registrieren sie sich um Spielen zu können");

        JPanel panel = new JPanel();
        panel.setSize(500,500);


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
        controlPanel.add(panel, GridBagConstraints.CENTER);
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
        controlPanel.add(panel, GridBagConstraints.CENTER);
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
