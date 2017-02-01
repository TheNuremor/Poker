package com.company.Client;


import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class GameGUI extends JFrame implements ActionListener {
    private JFrame gameWindow;
    private JPanel controlPanel;
    private JPanel playerCardsPanel;
    private JPanel notePanel;
    private JPanel interactionPanel;
    private JPanel playerListPanel;
    private JPanel playerInfoPanel;
    public JPanel betPanel;
    public JTextArea textArea;
    public JTextArea betTextArea;
    private JTextField usernameFieldLogin;
    private JTextField usernameFieldReg;
    private JTextField betValueField;
    private JPasswordField passwordFieldLogin;
    private JPasswordField passwordFieldReg;
    private JPasswordField passwordFieldCheck;
    private JButton gotoRegistration;
    private JButton login;
    private JButton registration;
    private JButton raise;
    private JButton call;
    private JButton fold;
    private JButton allIn;
    private Image img;
    private GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public String username;
    private boolean registrationFinished = false;
    private boolean loginFinished = false;
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
        gameWindow.getContentPane().setBackground(new Color(0,119,14));
        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //gameWindow.setUndecorated(true);
        //gameWindow.setSize(1280,768);
        gameWindow.setLayout(gridBagLayout);

        /*
        gameWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionPerformed(new ActionEvent(registration,NORMAL,registration.getActionCommand()));
                }
            }
        });
        */

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
        gridBagConstraints.insets = new Insets(20, 20, 20, 20);
        textArea = new JTextArea();
        textArea.setText(   "Bitte melden sie sich an, oder registrieren sich!\n\n" +
                "Auf dem Server befinden sich " + sizePlayerList + "/6 Spieler");
        textArea.setBackground(gameWindow.getBackground());
        gridBagLayout.setConstraints(textArea, gridBagConstraints);
        controlPanel.add(textArea);


        //UserName
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 20, 5, 0);
        JLabel username = new JLabel("Benutzername:");
        gridBagLayout.setConstraints(username, gridBagConstraints);
        controlPanel.add(username);
        //UserNameField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 20);
        usernameFieldLogin = new JTextField(8);
        gridBagLayout.setConstraints(usernameFieldLogin, gridBagConstraints);
        controlPanel.add(usernameFieldLogin);


        //Password
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(5, 20, 10, 0);
        JLabel password = new JLabel("Passwort:");
        gridBagLayout.setConstraints(password, gridBagConstraints);
        controlPanel.add(password);
        //PasswordField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 10, 20);
        passwordFieldLogin = new JPasswordField(8);
        passwordFieldLogin.setToolTipText("Passwort");
        gridBagLayout.setConstraints(passwordFieldLogin, gridBagConstraints);
        controlPanel.add(passwordFieldLogin);


        //Buttons
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.CENTER;
        //Login
        gridBagConstraints.insets = new Insets(5, 20, 20, 0);
        login = new JButton("Login and Join");
        login.addActionListener(this);
        gridBagLayout.setConstraints(login, gridBagConstraints);
        controlPanel.add(login);
        //Registration
        gridBagConstraints.insets = new Insets(5, 5, 20, 20);
        gotoRegistration = new JButton("Registrieren");
        gotoRegistration.addActionListener(this);
        gridBagLayout.setConstraints(gotoRegistration, gridBagConstraints);
        controlPanel.add(gotoRegistration);


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
        gridBagConstraints.insets = new Insets(20, 20, 20, 20);
        textArea = new JTextArea();
        textArea.setText(   "Bitte registrieren sie sich, um Online-Poker spielen zu können!\n\n"+
                "Auf dem Server befinden sich " + sizePlayerList + "/6 Spieler");
        textArea.setBackground(gameWindow.getBackground());
        gridBagLayout.setConstraints(textArea, gridBagConstraints);
        controlPanel.add(textArea);


        //UserName
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 20, 5, 0);
        JLabel usernameReg = new JLabel("Benutzername:");
        gridBagLayout.setConstraints(usernameReg, gridBagConstraints);
        controlPanel.add(usernameReg);
        //UserNameField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 3, 5, 20);
        usernameFieldReg = new JTextField(8);
        gridBagLayout.setConstraints(usernameFieldReg, gridBagConstraints);
        controlPanel.add(usernameFieldReg);


        //Password
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(5, 20, 5, 0);
        JLabel passwordReg = new JLabel("Passwort:");
        gridBagLayout.setConstraints(passwordReg, gridBagConstraints);
        controlPanel.add(passwordReg);
        //PasswordField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 3, 5, 20);
        passwordFieldReg = new JPasswordField(8);
        gridBagLayout.setConstraints(passwordFieldReg, gridBagConstraints);
        controlPanel.add(passwordFieldReg);


        //Password
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(5, 20, 10, 0);
        JLabel passwordCheck = new JLabel("Passwort wiederholen:");
        gridBagLayout.setConstraints(passwordCheck, gridBagConstraints);
        controlPanel.add(passwordCheck);
        //PasswordField
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 3, 10, 20);
        passwordFieldCheck = new JPasswordField(8);
        gridBagLayout.setConstraints(passwordFieldCheck, gridBagConstraints);
        controlPanel.add(passwordFieldCheck);

        //Registration
        gridBagConstraints.gridwidth = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 60, 20, 60);
        registration = new JButton("Registrieren");
        registration.addActionListener(this);
        gridBagLayout.setConstraints(registration, gridBagConstraints);
        controlPanel.add(registration);


        gameWindow.add(controlPanel);
        gameWindow.setVisible(true);

    }

    public void showGameWindow() {
        //PlayerListPanel
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        playerListPanel = new JPanel();
        gridBagLayout.setConstraints(playerListPanel, gridBagConstraints);
        playerListPanel.setBackground(new Color(0, 119, 14));

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
            playerPanel.setBackground(new Color(0x2B2B2B));
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
            label.setBackground(new Color(0x2B2B2B));
            label.setForeground(new Color(0xD4D4D4));
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
        controlPanel.setBackground(new Color(0, 119, 14));

        //NotePanel
        gridBagConstraints.gridwidth = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        notePanel = new JPanel();
        gridBagLayout.setConstraints(notePanel, gridBagConstraints);
        notePanel.setBackground(new Color(77,77,77));

        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        textArea = new JTextArea();
        textArea.setBackground(new Color(0x2B2B2B));
        textArea.setCaretColor(new Color(0xD4D4D4));
        textArea.setForeground(new Color(0xD4D4D4));

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
        playerCardsPanel.setBackground(new Color(0, 119, 14));


        //InteractionPanel
        gridBagConstraints.gridwidth = GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        interactionPanel = new JPanel();
        gridBagLayout.setConstraints(interactionPanel, gridBagConstraints);
        interactionPanel.setBackground(new Color(0,119,14));
        interactionPanel.setPreferredSize(new Dimension(500, 250));

        //PlayerInfoPanel
        gridBagConstraints.gridwidth = GridBagConstraints.NORTH;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        playerInfoPanel = new JPanel();
        gridBagLayout.setConstraints(playerInfoPanel, gridBagConstraints);
        playerInfoPanel.setBackground(new Color(0x2B2B2B));
        interactionPanel.add(playerInfoPanel);
        //Label
        gridBagConstraints.gridwidth = GridBagConstraints.PAGE_START;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new Insets(20, 20, 20, 20);
        String str2 =   "<html><table><tr><th>Name: " + username + "</th>" + "<th>Geld: " + cash +"</th></tr>" +
                "<tr><th>Rolle: " + role + "</th>" + "<th>Spielergebot: " + playerBet + "</th></tr>" +
                "<tr><th>isInGame: " + isInGame + "</th>" + "<th>isAllIn: " + isAllIn + "</th></tr></table></html>";
        JLabel label = new JLabel(str2);
        label.setBackground(new Color(0x2B2B2B));
        label.setForeground(new Color(0xD4D4D4));
        gridBagLayout.setConstraints(label, gridBagConstraints);
        playerInfoPanel.add(label);


        //BetPanel
        gridBagConstraints.gridwidth = GridBagConstraints.SOUTH;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        betPanel = new JPanel();
        gridBagLayout.setConstraints(betPanel, gridBagConstraints);
        betPanel.setBackground(new Color(0x2B2B2B));
        interactionPanel.add(betPanel);

        //BetTextArea
        gridBagConstraints.gridwidth = GridBagConstraints.NORTH;
        gridBagConstraints.gridheight = GridBagConstraints.NORTH;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(10,10,10,10);
        betTextArea = new JTextArea();
        gridBagLayout.setConstraints(betTextArea, gridBagConstraints);
        betTextArea.setBackground(new Color(0x2B2B2B));
        betTextArea.setCaretColor(new Color(0xD4D4D4));
        betTextArea.setForeground(new Color(0xD4D4D4));
        betPanel.add(betTextArea);
        betTextArea.append("Bitte geben sie ein Gebot ab.");

        //BetValueField
        gridBagConstraints.gridwidth = GridBagConstraints.LINE_START;
        gridBagConstraints.gridheight = GridBagConstraints.CENTER;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(20, 20, 10, 5);
        betValueField = new JTextField(12);
        gridBagLayout.setConstraints(betValueField, gridBagConstraints);
        betPanel.add(betValueField);
        //Raise
        gridBagConstraints.gridwidth = GridBagConstraints.LINE_END;
        gridBagConstraints.gridheight = GridBagConstraints.CENTER;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        //gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(20, 0, 10, 20);
        raise = new JButton("Raise");
        raise.addActionListener(this);
        gridBagLayout.setConstraints(raise, gridBagConstraints);
        betPanel.add(raise);

        //Fold
        gridBagConstraints.gridwidth = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.gridheight = GridBagConstraints.SOUTH;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        //gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(10, 20, 20, 0);
        fold = new JButton("Fold");
        fold.addActionListener(this);
        gridBagLayout.setConstraints(fold, gridBagConstraints);
        betPanel.add(fold);
        //Call
        gridBagConstraints.gridwidth = GridBagConstraints.PAGE_END;
        gridBagConstraints.gridheight = GridBagConstraints.SOUTH;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new Insets(10, 0, 20, 0);
        call = new JButton("Call");
        call.addActionListener(this);
        gridBagLayout.setConstraints(call, gridBagConstraints);
        betPanel.add(call);
        //AllIn
        gridBagConstraints.gridwidth = GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.gridheight = GridBagConstraints.SOUTH;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new Insets(10, 0, 20, 20);
        allIn = new JButton("All In");
        allIn.addActionListener(this);
        gridBagLayout.setConstraints(allIn, gridBagConstraints);
        betPanel.add(allIn);


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
        URL resource = getClass().getClassLoader().getResource("Texture/CardTextureMod/blank.png");

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

    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == this.login){
            controlPanel.removeAll();
            showGameWindow();
        }
        else if(actionEvent.getSource() == this.gotoRegistration){
            controlPanel.removeAll();
            showRegistrationWindow();
        }
        else if (actionEvent.getSource() == this.registration){
            username = usernameFieldReg.getText();
            String password = String.valueOf(passwordFieldReg.getPassword());
            String passwordCheck = String.valueOf(passwordFieldReg.getPassword());

            if (Objects.equals(password, passwordCheck) && password.length()!=0 && passwordCheck.length() !=0 && username.length()!=0){
                creatUser(username, password);
                controlPanel.removeAll();
                showLoginWindow();
                usernameFieldLogin.setText(username);
                passwordFieldLogin.setText(password);
                textArea.append("\n\nSie haben sich erfolgreich registriert,\n sie können nun einem Spiel beitreten");
            }
            else {
                usernameFieldReg.setText("");
                passwordFieldReg.setText("");
                passwordFieldCheck.setText("");
                if (!textArea.getText().contains("Ihre Passwörter sind nicht gleich")) {
                    textArea.append("\n\nIhre Passwörter sind nicht gleich,\n oder sie haben keinen Benutzernamen eingegeben!");
                }
            }
        }
    }

    public void actionPerformedGame(ActionEvent gameActionEvent){
        if (gameActionEvent.getSource() == this.raise){

        }
        else if (gameActionEvent.getSource() == this.fold){

        }
        else if (gameActionEvent.getSource() == this.call){

        }
        else if (gameActionEvent.getSource() == this.allIn){

        }
    }

    public boolean creatUser (String username, String password){

        return true;
    }
}
