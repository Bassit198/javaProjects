package main;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimeZoneConverter implements ActionListener{

    JFrame frame = new JFrame("TimeZoneConverter");
    JLabel welcomeLabel = new JLabel("Time Zone Converter");
    JPanel welcomeLabelPanel = new JPanel();
    JPanel instructionsPanel = new JPanel();
    JPanel resultsPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    JLabel timeLabel = new JLabel("Enter your time: ");
    JTextField timeLabelField = new JTextField();
    JLabel timeZoneLabel = new JLabel("Select your time zone: ");
    JLabel destinationLabel = new JLabel("Choose Destination: ");
    String[] sourceZones = {"Pacific", "Mountain", "Central", "Eastern"};
    JComboBox sourceZonesDropDown = new JComboBox(sourceZones);
    String[] destinationZones = {"Pacific", "Mountain", "Central", "Eastern"};
    JComboBox destinationZonesDropDown = new JComboBox(destinationZones);
    JButton clearButton = new JButton("Clear");
    JButton convertButton = new JButton("Convert!");
    JTextField resultsTimeTextField = new JTextField();


    GridLayout gridlayout = new GridLayout(3,2,10,5);
    GridLayout buttonGridLayout = new GridLayout(1, 2, 30, 0);


    public void mainDisplay(){
        //panels config
        //welcomeLabelPanel.setBackground(Color.red);
        welcomeLabelPanel.setBounds(85,10,250,50);

        //instructionsPanel.setBackground(Color.blue);
        instructionsPanel.setBounds(35,70, 350,120);
        instructionsPanel.setLayout(gridlayout);

        //resultsPanel.setBackground(Color.green);
        resultsPanel.setBounds(60,210,300,90);

        //buttonsPanel.setBackground(Color.red);
        buttonsPanel.setBounds(35, 315, 350, 35);
        buttonsPanel.setLayout(buttonGridLayout);

        //add components to panel
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 26));
        welcomeLabelPanel.add(welcomeLabel);

        //components properties
        timeLabel.setFont(new Font(null, Font.PLAIN, 14));
        timeZoneLabel.setFont(new Font(null, Font.PLAIN, 14));
        destinationLabel.setFont(new Font(null, Font.PLAIN, 14));
        sourceZonesDropDown.addActionListener(this);
        destinationZonesDropDown.addActionListener(this);
        resultsTimeTextField.setBounds(0,0,300,10);
        resultsTimeTextField.setBackground(null);
        resultsTimeTextField.setBorder(null);
        clearButton.addActionListener(this);
        convertButton.addActionListener(this);

        //add components to instructions panel
        instructionsPanel.add(timeLabel);
        instructionsPanel.add(timeLabelField);
        instructionsPanel.add(timeZoneLabel);
        instructionsPanel.add(sourceZonesDropDown);
        instructionsPanel.add(destinationLabel);
        instructionsPanel.add(destinationZonesDropDown);

        //add componenets to result panel
        resultsPanel.setLayout(buttonGridLayout);
        resultsPanel.add(resultsTimeTextField);

        //add components to button panel
        buttonsPanel.add(clearButton);
        buttonsPanel.add(convertButton);

        //frame config
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(420,420);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //add panels to frame
        frame.add(welcomeLabelPanel);
        frame.add(instructionsPanel);
        frame.add(resultsPanel);
        frame.add(buttonsPanel);

    }//end main display method







    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clearButton){
            timeLabelField.setText("");
            sourceZonesDropDown.setSelectedIndex(0);
            destinationZonesDropDown.setSelectedIndex(0);
            resultsTimeTextField.setText("");
        }//end clear button action

        if(e.getSource() == convertButton){
            try{
                String givenTime = timeLabelField.getText();
                int sourceSelection = sourceZonesDropDown.getSelectedIndex();
                int destinationSelection = destinationZonesDropDown.getSelectedIndex();

                if(givenTime.length() > 4){

                    //add input to char array for procesing
                    String[] parse = givenTime.split(":");
                    int hours = Integer.parseInt(parse[0]);
                    int minutes = Integer.parseInt(parse[1]);

                    if(parse.length > 2){
                        JOptionPane.showMessageDialog(instructionsPanel, "Invalid Time Entry.\nUse Format 00:00", "Error", JOptionPane.WARNING_MESSAGE);
                    }

                    if(hours > 24  || hours < 0 || minutes > 59|| minutes < 0){
                        JOptionPane.showMessageDialog(instructionsPanel, "Invalid Time Entry.\nUse Format 00:00", "Error", JOptionPane.WARNING_MESSAGE);
                    }

                    if(sourceSelection == destinationSelection){
                        if(minutes < 10){
                            resultsTimeTextField.setText("No conversion Needed. Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("No conversion Needed. Time = " + hours + ":" + minutes);
                        }
                    }


                    //conversion logics
                    //pacific to mountain
                    if((sourceSelection == 0) && (destinationSelection == 1)){
                        hours += 1;
                        if(minutes <= 9){
                            resultsTimeTextField.setText("Pacific to Mountain Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Pacific to Mountain Time = " + hours + ":" + minutes);
                        }

                    }

                    //pacific to central
                    if((sourceSelection == 0) && destinationSelection == 2){
                        hours += 2;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Pacific to Central Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Pacific to Central Time = " + hours + ":" + minutes);
                        }
                    }

                    //pacific to eastern
                    if((sourceSelection == 0) && destinationSelection == 3){
                        hours += 3;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Pacific to Eastern Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Pacific to Eastern Time = " + hours + ":" + minutes);
                        }
                    }

                    //mountain to pacific
                    if((sourceSelection == 1) && destinationSelection == 0){
                        hours -= 1;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Mountain to Pacific Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Mountain to Pacific Time = " + hours + ":" + minutes);
                        }
                    }

                    //mountain to central
                    if((sourceSelection == 1) && destinationSelection == 2){
                        hours += 1;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Mountain to Central Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Mountain to Central Time = " + hours + ":" + minutes);
                        }
                    }

                    //mountain to eastern
                    if((sourceSelection == 1) && destinationSelection == 3){
                        hours += 2;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Mountain to Eastern Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Mountain to Eastern Time = " + hours + ":" + minutes);
                        }
                    }

                    //central to pacific
                    if((sourceSelection == 2) && destinationSelection == 0){
                        hours -= 2;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Central to Pacific Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Central to Pacific Time = " + hours + ":" + minutes);
                        }
                    }

                    //central to mountain
                    if((sourceSelection == 2) && destinationSelection == 1){
                        hours -= 1;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Central to Mountain Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Central to Mountain Time = " + hours + ":" + minutes);
                        }
                    }

                    //central to eastern
                    if((sourceSelection == 2) && destinationSelection == 3){
                        hours += 1;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Central to Eastern Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Central to Eastern Time = " + hours + ":" + minutes);
                        }
                    }

                    //eastern to pacific
                    if((sourceSelection == 3) && destinationSelection == 0){
                        hours -= 3;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Eastern to Pacific Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Eastern to Pacific Time = " + hours + ":" + minutes);
                        }
                    }

                    //eastern to mountain
                    if((sourceSelection == 3) && destinationSelection == 1){
                        hours -= 2;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Eastern to Mountain Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Eastern to Mountain Time = " + hours + ":" + minutes);
                        }
                    }

                    //eastern to central
                    if((sourceSelection == 3) && destinationSelection == 2){
                        hours -= 1;
                        if(minutes < 10){
                            resultsTimeTextField.setText("Eastern to Central Time = " + hours + ":0" + minutes);
                        }else{
                            resultsTimeTextField.setText("Eastern to Central Time = " + hours + ":" + minutes);
                        }
                    }


                }else{
                    JOptionPane.showMessageDialog(instructionsPanel, "Invalid Time Format. Format = 00:00", "Error", JOptionPane.WARNING_MESSAGE);
                }

                resultsTimeTextField.setFont(new Font(null, Font.PLAIN, 18));
                resultsTimeTextField.setEditable(false);

            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(instructionsPanel, "Invalid Time Entry.2", "Error", JOptionPane.WARNING_MESSAGE);
            }//end try/catch

        }//end convert button action

    }//end action performed method
}
