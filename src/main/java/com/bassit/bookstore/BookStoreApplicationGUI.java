package com.bassit.bookstore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class BookStoreApplicationGUI {
    private JPanel mainPanel;
    private JPanel basePanel;
    private JPanel sideBar;
    private JPanel mainView;
    private JButton listAllBooksButton;
    private JButton manageCustomerButton;
    private JButton manageMembershipButton;
    private JButton manageTransactionButton;
    private JButton quitButton;
    private JPanel listCard;
    private JPanel customerCard;
    private JPanel memberCard;
    private JPanel transactionCard;
    private JButton createCustomerButton;
    private JButton searchNameButton;
    private JButton searchEmailButton;
    private JButton searchNumberButton;
    private JButton updateFnameButton;
    private JButton updateLnameButton;
    private JButton updateEmailButton;
    private JButton updatePhoneButton;
    private JPanel customerMenuPanel;
    private JPanel customerViewPanel;
    private JPanel createCustomerCard;
    private JPanel searchNameCard;
    private JPanel searchEmailCard;
    private JPanel searchNumberCard;
    private JPanel updateFnameCard;
    private JPanel updateLnameCard;
    private JPanel updateEmailCard;
    private JPanel updatePhoneCard;
    private JTextField createCustomerFname;
    private JTextField createCustomerLname;
    private JTextField createCustomerEmail;
    private JTextField createCustomerPhone;
    private JButton submitButton_customer;
    private JTextField searchNameFname;
    private JTextField searchNameLname;
    private JButton searchButton_customerName;
    private JTextField searchEmail_Email;
    private JButton searchButton_customerEmail;
    private JTextField searchNumber_number;
    private JButton searchButton_customerPhone;
    private JTextField updateFname_fname;
    private JTextField updateFname_lname;
    private JTextField updateFname_newFname;
    private JButton updateButton_customerFName;
    private JTextField updateLname_fname;
    private JTextField updateLname_oldLname;
    private JTextField updateLname_newLname;
    private JButton updateButton_customerLName;
    private JTextField updateEmail_fname;
    private JTextField updateEmail_lname;
    private JTextField updateEmail_newEmail;
    private JButton updateButton_customerEmail;
    private JTextField updatePhone_fname;
    private JTextField updatePhone_lname;
    private JTextField updatePhone_newPhone;
    private JButton updateButton_customerPhone;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JPanel membershipMenuPanel;
    private JPanel membershipViewPanel;
    private JPanel card1;
    private JPanel card2;
    private JButton card1Button;
    private JButton card2Button;

    private static final JFrame frame = new JFrame("Token Formatter");

    public BookStoreApplicationGUI() {

        //change side button cards
        List<JPanel> sideButtonList = new ArrayList<>();
        Collections.addAll(sideButtonList, listCard, customerCard, memberCard, transactionCard);
        changeCardPanel(sideButtonList, listAllBooksButton, 0);
        changeCardPanel(sideButtonList, manageCustomerButton, 1);
        changeCardPanel(sideButtonList, manageMembershipButton, 2);
        changeCardPanel(sideButtonList, manageTransactionButton, 3);

        //change customer button cards
        List<JPanel> customerCardsList = new ArrayList<>();
        Collections.addAll(customerCardsList, createCustomerCard, searchNameCard, searchEmailCard, searchNumberCard, updateFnameCard, updateLnameCard, updateEmailCard, updatePhoneCard);
        changeCardPanel(customerCardsList, createCustomerButton, 0);
        changeCardPanel(customerCardsList, searchNameButton, 1);
        changeCardPanel(customerCardsList, searchEmailButton, 2);
        changeCardPanel(customerCardsList, searchNumberButton, 3);
        changeCardPanel(customerCardsList, updateFnameButton, 4);
        changeCardPanel(customerCardsList, updateLnameButton, 5);
        changeCardPanel(customerCardsList, updateEmailButton, 6);
        changeCardPanel(customerCardsList, updatePhoneButton, 7);

        //quit button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        HashMap<String, String> buttonsList = getButtonsPathHashMap();

        paintButtonDefaultSize(listAllBooksButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(manageCustomerButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(manageMembershipButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(manageTransactionButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(quitButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(listAllBooksButton, buttonsList.get("Blue"));

        hoverEffectButtonDefaultSize(listAllBooksButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(manageCustomerButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(manageMembershipButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(manageTransactionButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(quitButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));


        paintButtonDefaultSize(createCustomerButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(searchNameButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(searchEmailButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(searchNumberButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(updateFnameButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(updateLnameButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(updateEmailButton, buttonsList.get("Blue"));
        paintButtonDefaultSize(updatePhoneButton, buttonsList.get("Blue"));

        hoverEffectButtonDefaultSize(createCustomerButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(searchNameButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(searchEmailButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(searchNumberButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(updateFnameButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(updateLnameButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(updateEmailButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));
        hoverEffectButtonDefaultSize(updatePhoneButton, buttonsList.get("DarkBlue"),  buttonsList.get("Blue"));

        paintButtonDefaultSize(submitButton_customer, buttonsList.get("Green"));
        hoverEffectButtonDefaultSize(submitButton_customer, buttonsList.get("DarkGreen"), buttonsList.get("Green"));

        paintButtonDefaultSize(searchButton_customerName, buttonsList.get("Green"));
        hoverEffectButtonDefaultSize(searchButton_customerName, buttonsList.get("DarkGreen"), buttonsList.get("Green"));

        paintButtonDefaultSize(searchButton_customerEmail, buttonsList.get("Green"));
        hoverEffectButtonDefaultSize(searchButton_customerEmail, buttonsList.get("DarkGreen"), buttonsList.get("Green"));

        paintButtonDefaultSize(searchButton_customerPhone, buttonsList.get("Green"));
        hoverEffectButtonDefaultSize(searchButton_customerPhone, buttonsList.get("DarkGreen"), buttonsList.get("Green"));

        paintButtonDefaultSize(updateButton_customerFName, buttonsList.get("Green"));
        hoverEffectButtonDefaultSize(updateButton_customerFName, buttonsList.get("DarkGreen"), buttonsList.get("Green"));

        paintButtonDefaultSize(updateButton_customerLName, buttonsList.get("Green"));
        hoverEffectButtonDefaultSize(updateButton_customerLName, buttonsList.get("DarkGreen"), buttonsList.get("Green"));

        paintButtonDefaultSize(updateButton_customerEmail, buttonsList.get("Green"));
        hoverEffectButtonDefaultSize(updateButton_customerEmail, buttonsList.get("DarkGreen"), buttonsList.get("Green"));

        paintButtonDefaultSize(updateButton_customerPhone, buttonsList.get("Green"));
        hoverEffectButtonDefaultSize(updateButton_customerPhone, buttonsList.get("DarkGreen"), buttonsList.get("Green"));


    }







    private static HashMap<String, String> getButtonsPathHashMap() {
        HashMap<String, String> buttonsList = new HashMap<>();
        buttonsList.put("Blue", "C:\\Users\\Bassit\\IdeaProjectsUltimate\\BookStore\\src\\main\\resources\\buttonImages\\blueButton.png");
        buttonsList.put("DarkBlue", "C:\\Users\\Bassit\\IdeaProjectsUltimate\\BookStore\\src\\main\\resources\\buttonImages\\darkBlueButton.png");
        buttonsList.put("Green", "C:\\Users\\Bassit\\IdeaProjectsUltimate\\BookStore\\src\\main\\resources\\buttonImages\\greenButton.png");
        buttonsList.put("DarkGreen", "C:\\Users\\Bassit\\IdeaProjectsUltimate\\BookStore\\src\\main\\resources\\buttonImages\\darkGreenButton.png");
        return buttonsList;
    }

    public void paintButton(JButton button, int width, int height, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        button.setIcon(newIcon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setForeground(Color.WHITE);
    }

    public void paintButtonDefaultSize(JButton button, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(150, 30, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        button.setIcon(newIcon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setForeground(Color.WHITE);
    }

    public void hoverEffectButton(JButton button, String hoverEnterPath, String hoverExitPath ,int width, int height){

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paintButton(button, width, height, hoverEnterPath);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                paintButton(button, width, height, hoverExitPath);
            }
        });
    }

    public void hoverEffectButtonDefaultSize(JButton button, String hoverEnterPath, String hoverExitPath){

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paintButtonDefaultSize(button, hoverEnterPath);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                paintButtonDefaultSize(button, hoverExitPath);
            }
        });
    }

    public void changeCardPanel(List<JPanel> list, JButton button, int indexShow){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0; i<list.size(); i++){
                    if(i==indexShow){
                        list.get(i).setVisible(true);
                    }else{
                        list.get(i).setVisible(false);
                    }
                }
            }
        });

    }

    public static void main(String[] args) {
        frame.setContentPane(new BookStoreApplicationGUI().mainPanel);
        frame.setSize(1200, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);
    }


}
