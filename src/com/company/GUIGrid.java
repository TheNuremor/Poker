package com.company;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIGrid {


    /*
     * Hinzufügen von JPanels und JLabels und
     * verwenden eines GridBagLayouts
     */
    class GridBagLayoutFrame extends JFrame{
        public GridBagLayoutFrame(){
            super("GridBagLayout");
            setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE);

            //Einstellen eines GridBagLayouts
            GridBagLayout gridBagLayout = new GridBagLayout();

            getContentPane().setLayout(gridBagLayout);
            /*
	 * Hier wird ein GridBagConstraint-Objekt
	 * erstellt
	 */
            GridBagConstraints gridBagConstraints = new GridBagConstraints();

	/*
	 * Einstellen der Constraints und
	 * Hinzufügen der Objekte
	 */

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

            String[] strArray = {
                    "Sportwagen",
                    "Limosine",
                    "Coupe",
                    "Transporter",
                    "Bus",
                    "Lkw",
                    "Motorrad"};

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

            setSize(300,400);
            setLocation(50,50);
            setVisible(true);
        }
    }
    public class FrameCreator08 {
        public static void main(String[] args){
            new GridBagLayoutFrame();
        }
    }


}
