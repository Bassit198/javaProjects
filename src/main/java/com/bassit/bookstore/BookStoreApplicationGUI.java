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
    private JToggleButton listAllBooksButton;
    private JToggleButton manageCustomerButton;
    private JToggleButton manageMembershipButton;
    private JToggleButton manageTransactionButton;
    private JButton quitButton;
    private JPanel listCard;
    private JPanel customerCard;
    private JPanel memberCard;
    private JPanel transactionCard;
    private JToggleButton createCustomerButton;
    private JToggleButton searchNameButton;
    private JToggleButton searchEmailButton;
    private JToggleButton searchNumberButton;
    private JToggleButton updateFnameButton;
    private JToggleButton updateLnameButton;
    private JToggleButton updateEmailButton;
    private JToggleButton updatePhoneButton;
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
        //panels lists
        List<JPanel> sideBarPanelList = new ArrayList<>();
        Collections.addAll(sideBarPanelList, listCard, customerCard, memberCard, transactionCard);

        List<JPanel> customerCardPanelList = new ArrayList<>();
        Collections.addAll(customerCardPanelList, createCustomerCard, searchNameCard, searchEmailCard, searchNumberCard, updateFnameCard, updateLnameCard, updateEmailCard, updatePhoneCard);

        //buttons lists
        List<JToggleButton> sideBarButtonList = new ArrayList<>();
        Collections.addAll(sideBarButtonList, listAllBooksButton, manageCustomerButton, manageMembershipButton, manageTransactionButton);

        List<JToggleButton> customerCardButtonList = new ArrayList<>();
        Collections.addAll(customerCardButtonList, createCustomerButton, searchNameButton, searchEmailButton, searchNumberButton, updateFnameButton, updateLnameButton, updateEmailButton, updatePhoneButton);

        //button styles
        HashMap<String, String> buttonStyle = getButtonStyleHashMap();

        //painting all components
        //set toggle action for sidebar - paint all buttons default blue, paint selected button dark blue and also switch cards as necessary
        setButtonSelected(sideBarPanelList, 0, sideBarButtonList, buttonStyle, sideBarButtonList.get(0), 0, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(sideBarPanelList, 1, sideBarButtonList, buttonStyle, sideBarButtonList.get(1), 1, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(sideBarPanelList, 2, sideBarButtonList, buttonStyle, sideBarButtonList.get(2), 2, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(sideBarPanelList, 3, sideBarButtonList, buttonStyle, sideBarButtonList.get(3), 3, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));


        //set toggle action for manage customer - paint all buttons default blue, paint selected button dark blue and also switch cards as necessary
        setButtonSelected(customerCardPanelList, 0, customerCardButtonList, buttonStyle, customerCardButtonList.get(0), 0, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(customerCardPanelList, 1, customerCardButtonList, buttonStyle, customerCardButtonList.get(1), 1, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(customerCardPanelList, 2, customerCardButtonList, buttonStyle, customerCardButtonList.get(2), 2, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(customerCardPanelList, 3, customerCardButtonList, buttonStyle, customerCardButtonList.get(3), 3, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(customerCardPanelList, 4, customerCardButtonList, buttonStyle, customerCardButtonList.get(4), 4, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(customerCardPanelList, 5, customerCardButtonList, buttonStyle, customerCardButtonList.get(5), 5, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(customerCardPanelList, 6, customerCardButtonList, buttonStyle, customerCardButtonList.get(6), 6, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(customerCardPanelList, 7, customerCardButtonList, buttonStyle, customerCardButtonList.get(7), 7, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));

        //manage customer additional buttons painting
        paintButtonDefaultSize(quitButton, buttonStyle.get("Blue"));

        paintButtonDefaultSize(submitButton_customer, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(submitButton_customer, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));

        paintButtonDefaultSize(searchButton_customerName, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(searchButton_customerName, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));

        paintButtonDefaultSize(searchButton_customerEmail, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(searchButton_customerEmail, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));

        paintButtonDefaultSize(searchButton_customerPhone, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(searchButton_customerPhone, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));

        paintButtonDefaultSize(updateButton_customerFName, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(updateButton_customerFName, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));

        paintButtonDefaultSize(updateButton_customerLName, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(updateButton_customerLName, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));

        paintButtonDefaultSize(updateButton_customerEmail, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(updateButton_customerEmail, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));

        paintButtonDefaultSize(updateButton_customerPhone, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(updateButton_customerPhone, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));


        //action listeners for all buttons
        //quit button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


    }

    private static HashMap<String, String> getButtonStyleHashMap() {
        HashMap<String, String> buttonStyle = new HashMap<>();
        buttonStyle.put("Blue", "C:\\Users\\Bassit\\IdeaProjectsUltimate\\BookStore\\src\\main\\resources\\buttonImages\\blueButton.png");
        buttonStyle.put("DarkBlue", "C:\\Users\\Bassit\\IdeaProjectsUltimate\\BookStore\\src\\main\\resources\\buttonImages\\darkBlueButton.png");
        buttonStyle.put("Green", "C:\\Users\\Bassit\\IdeaProjectsUltimate\\BookStore\\src\\main\\resources\\buttonImages\\greenButton.png");
        buttonStyle.put("DarkGreen", "C:\\Users\\Bassit\\IdeaProjectsUltimate\\BookStore\\src\\main\\resources\\buttonImages\\darkGreenButton.png");
        return buttonStyle;
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

    public void paintButtonDefaultSize(JToggleButton button, String imagePath){
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

    public void setButtonSelected(List<JPanel> panelList, int panelToShow, List<JToggleButton> buttonList, HashMap<String, String> buttonStyle, JToggleButton buttonToClick, int buttonToToggle, String hoverEnterPath, String hoverExitPath){
        //paint all buttons to default blue
        for (JToggleButton button : buttonList) {
            paintButtonDefaultSize(button, buttonStyle.get("Blue"));
        }
        buttonToClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i=0; i<buttonList.size(); i++){
                    if(i==buttonToToggle){
                        paintButtonToggle(buttonList.get(i), buttonStyle.get("DarkBlue"));
                    }else{
                        buttonList.get(i).setSelected(false);
                        paintButtonToggle(buttonList.get(i), buttonStyle.get("Blue"));
                    }
                }

                for(int i=0; i<panelList.size(); i++){
                    if(i==panelToShow){
                        panelList.get(i).setVisible(true);
                    }else{
                        panelList.get(i).setVisible(false);
                    }
                }
            }
        });


    }

    public void paintButtonToggle(JToggleButton button, String imagePath){
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

//
//    public void hoverEffectButtonDefaultSize(JToggleButton button, String hoverEnterPath, String hoverExitPath){
//
//        button.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                paintButtonDefaultSize(button, hoverEnterPath);
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                paintButtonDefaultSize(button, hoverExitPath);
//            }
//        });
//    }
//
//    public void changeCardPanel(List<JPanel> list, JButton button, int indexShow){
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                for (int i=0; i<list.size(); i++){
//                    if(i==indexShow){
//                        list.get(i).setVisible(true);
//                    }else{
//                        list.get(i).setVisible(false);
//                    }
//                }
//            }
//        });
//
//    }

    //    public void paintButton(JButton button, int width, int height, String imagePath){
//        ImageIcon icon = new ImageIcon(imagePath);
//        Image img = icon.getImage();
//        Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
//        ImageIcon newIcon = new ImageIcon(newimg);
//
//        button.setIcon(newIcon);
//        button.setHorizontalTextPosition(SwingConstants.CENTER);
//        button.setContentAreaFilled(false);
//        button.setBorder(null);
//        button.setForeground(Color.WHITE);
//    }

//    public void hoverEffectButton(JButton button, String hoverEnterPath, String hoverExitPath ,int width, int height){
//
//        button.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                paintButton(button, width, height, hoverEnterPath);
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                paintButton(button, width, height, hoverExitPath);
//            }
//        });
//    }










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
