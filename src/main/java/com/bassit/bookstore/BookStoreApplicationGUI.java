package com.bassit.bookstore;

import com.bassit.bookstore.Models.Customers;
import com.bassit.bookstore.Services.CustomersService;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


@SpringBootApplication
@Log
public class BookStoreApplicationGUI {
    private JPanel mainPanel;
    private JPanel basePanel;
    private JPanel sideBar;
    private JPanel mainView;
    //sidebar buttons
    private JToggleButton listAllBooksButton;
    private JToggleButton manageCustomerButton;
    private JToggleButton manageMembershipButton;
    private JToggleButton manageTransactionButton;
    private JButton quitButton;

    //panels for sidebar buttons
    private JPanel listCard;
    private JPanel customerCard;
    private JPanel memberCard;
    private JPanel transactionCard;

    //manage customers buttons
    private JToggleButton createCustomerButton;
    private JToggleButton searchNameButton;
    private JToggleButton searchEmailButton;
    private JToggleButton searchNumberButton;
    private JToggleButton updateFnameButton;
    private JToggleButton updateLnameButton;
    private JToggleButton updateEmailButton;
    private JToggleButton updatePhoneButton;

    //panels for manage customer
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

    //fields and buttons for manage customers
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

    //manage membership buttons
    private JToggleButton createMembershipButton_membership;
    private JToggleButton searchUsernameButton_membership;
    private JToggleButton updateFirstNameButton_membership;
    private JToggleButton updateLastNameButton_membership;
    private JToggleButton updatePhoneButton_membership;
    private JToggleButton updateStatusButton_membership;
    private JToggleButton updateEmailButton_membership;
    private JToggleButton changeUsernameButton_membership;
    private JToggleButton changePasswordButton_membership;
    private JToggleButton updatePriceButton_membership;
    private JToggleButton updateExpirationButton_membership;
    private JToggleButton updatePlanButton_membership;

    //panels for manage membership
    private JPanel membershipMenuPanel;
    private JPanel membershipViewPanel;
    private JPanel createMemberPanel_membership;
    private JPanel searchUsernamePanel_membership;
    private JPanel updateFirstNamePanel_membership;
    private JPanel updateLastNamePanel_membership;
    private JPanel updatePhonePanel_membership;
    private JPanel updateEmailPanel_membership;
    private JPanel updateUsernamePanel_membership;
    private JPanel updatePasswordPanel_membership;
    private JPanel updateStatusPanel_membership;
    private JPanel updatePricePanel_membership;
    private JPanel updateExpirationPanel_membership;
    private JPanel updatePlanPanel_membership;

    //fields and buttons for manage membership
    //create member card
    private JTextField createMemberFname;
    private JTextField createMemberLname;
    private JTextField createMemberEmail;
    private JTextField createMemberPhone;
    private JTextField createMemberUsername;
    private JPasswordField createMemberPassword;
    private JTextField createMemberPlan;
    private JButton submitButton_createMembership;

    //search username card
    private JTextField searchMemberUsername;
    private JButton searchButton_usernameMembership;

    //update first name card
    private JTextField updateMemberFirstName_username;
    private JTextField updateMemberFirstName_newFname;
    private JButton updateButton_firstNameMembership;

    //update last name card
    private JTextField updateMemberLastName_username;
    private JTextField updateMemberLastName_newLname;
    private JButton updateButton_lastNameMembership;

    //update phone card
    private JTextField updateMemberPhone_username;
    private JTextField updateMemberPhone_newPhone;
    private JButton updateButton_phoneMember;

    //update email card
    private JTextField updateMemberEmail_username;
    private JTextField updateMemberEmail_newEmail;
    private JButton updateButton_emailMembership;

    //update username card
    private JTextField updateMemberUsername_oldUsername;
    private JTextField updateMemberUsername_newUsername;
    private JButton updateButton_usernameMembership;

    //update password card
    private JTextField updateMemberPassword_username;
    private JTextField updateMemberPassword_newPassword;
    private JButton updateButton_passwordMembership;

    //update plan card
    private JTextField updateMemberPlan_username;
    private JTextField updateMemberPlan_newPlan;
    private JButton updateButton_planMembership;

    //update status card
    private JTextField updateMemberStatus_username;
    private JTextField updateMemberStatus_newStatus;
    private JButton updateButton_statusMembership;

    //update price card
    private JTextField updateMemberPrice_username;
    private JTextField updateMemberPrice_newPrice;
    private JButton updateButton_priceMembership;

    //update expiration card
    private JTextField updateMemberExpiration_username;
    private JTextField updateMemberExpiration_newExpiration;
    private JButton updateButton_expirationMembership;

    //manage transaction buttons
    private JToggleButton createTransactionButton;
    private JToggleButton findTransactionNumberButton;
    private JToggleButton findTransactionDateButton;
    private JToggleButton refundTransactionButton;
    private JToggleButton cancelTransactionButton;

    //panels for transaction
    private JPanel transactionMenuPanel;
    private JPanel transactionViewPanel;
    private JPanel startTransactionPanel;
    private JPanel findTransactionByNumberPanel;
    private JPanel findTransactionByDatePanel;
    private JPanel refundTransactionPanel;
    private JPanel cancelTransactionPanel;

    //fields and buttons for transaction
    //start transaction
    private JTextField isbnStartTransaction;
    private JTextField ccNumberStartTransaction;
    private JButton purchaseButtonStartTransaction;

    //search by transaction number
    private JTextField transactionNumberSearchByNumber;
    private JButton searchButtonSearchByNumber;

    //search within date range
    private JTextField startDateTextFieldSearchByDate;
    private JTextField endDateTextFieldSearchByDate;
    private JButton searchButtonSearchWithinDateRange;

    //refund transaction
    private JTextField transactionNumberRefundTransaction;
    private JButton refundButtonRefundTransaction;

    //cancel transaction
    private JTextField transactionNumberCancelTransaction;
    private JButton cancelButtonCancelTransaction;
    private JPanel resultPanel;
    private JLabel firstNameResult;
    private JLabel lastNameResult;
    private JLabel emailResult;
    private JLabel phoneResult;


    private JPanel card1;
    private JPanel card2;
    private JButton card1Button;
    private JButton card2Button;

    private static final JFrame frame = new JFrame("Virtual Bookstore");

    public BookStoreApplicationGUI() {

        paintUI();


        //action listeners for all buttons
        //quit button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        //---------------------------------------------------------------------------------------MANAGE CUSTOMER ACTIONS---------------------------------------------------------------------------------------
        manageCustomerActions();

        //---------------------------------------------------------------------------------------MANAGE MEMBERSHIP ACTIONS---------------------------------------------------------------------------------------



        //---------------------------------------------------------------------------------------MANAGE TRANSACTIONS ACTIONS---------------------------------------------------------------------------------------




    }

    private void paintUI() {
        //panels lists
        List<JPanel> sideBarPanelList = new ArrayList<>();
        Collections.addAll(sideBarPanelList, listCard, customerCard, memberCard, transactionCard);

        List<JPanel> customerCardPanelList = new ArrayList<>();
        Collections.addAll(customerCardPanelList, createCustomerCard, searchNameCard, searchEmailCard, searchNumberCard, updateFnameCard, updateLnameCard, updateEmailCard, updatePhoneCard);

        List<JPanel> membershipCardPanelList = new ArrayList<>();
        Collections.addAll(membershipCardPanelList, createMemberPanel_membership, searchUsernamePanel_membership, updateFirstNamePanel_membership, updateLastNamePanel_membership, updatePhonePanel_membership, updateEmailPanel_membership, updateUsernamePanel_membership, updatePasswordPanel_membership, updatePlanPanel_membership, updateStatusPanel_membership, updatePricePanel_membership, updateExpirationPanel_membership);

        List<JPanel> transactionCardPanelList = new ArrayList<>();
        Collections.addAll(transactionCardPanelList, startTransactionPanel, findTransactionByNumberPanel, findTransactionByDatePanel, refundTransactionPanel, cancelTransactionPanel);

        //buttons lists
        List<JToggleButton> sideBarButtonList = new ArrayList<>();
        Collections.addAll(sideBarButtonList, listAllBooksButton, manageCustomerButton, manageMembershipButton, manageTransactionButton);

        List<JToggleButton> customerCardButtonList = new ArrayList<>();
        Collections.addAll(customerCardButtonList, createCustomerButton, searchNameButton, searchEmailButton, searchNumberButton, updateFnameButton, updateLnameButton, updateEmailButton, updatePhoneButton);

        List<JToggleButton> membershipCardButtonList = new ArrayList<>();
        Collections.addAll(membershipCardButtonList, createMembershipButton_membership, searchUsernameButton_membership, updateFirstNameButton_membership, updateLastNameButton_membership, updatePhoneButton_membership, updateEmailButton_membership, changeUsernameButton_membership, changePasswordButton_membership, updatePlanButton_membership, updateStatusButton_membership, updatePriceButton_membership, updateExpirationButton_membership);

        List<JToggleButton> transactionCardButtonList = new ArrayList<>();
        Collections.addAll(transactionCardButtonList, createTransactionButton, findTransactionNumberButton, findTransactionDateButton, refundTransactionButton, cancelTransactionButton);

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

        //set toggle action for manage membership - paint all buttons default blue, paint selected button dark blue and also switch cards as nesetButtonSelected(membershipCardPanelList, 0, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(0), 0, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 1, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(1), 1, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 2, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(2), 2, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 3, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(3), 3, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 4, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(4), 4, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 5, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(5), 5, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 6, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(6), 6, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 7, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(7), 7, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 8, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(8), 8, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 9, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(9), 9, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 10, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(10), 10, buttonStyle.get("DarkBlue"), buttonStyle.get("BsetButtonSelected(membershipCardPanelList, 11, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(11), 11, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(membershipCardPanelList, 0, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(0), 0, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(membershipCardPanelList, 1, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(1), 1, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(membershipCardPanelList, 2, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(2), 2, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(membershipCardPanelList, 3, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(3), 3, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(membershipCardPanelList, 4, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(4), 4, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(membershipCardPanelList, 5, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(5), 5, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(membershipCardPanelList, 6, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(6), 6, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(membershipCardPanelList, 7, membershipCardButtonList, buttonStyle, membershipCardButtonList.get(7), 7, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));

        //set toggle action for manage transaction - paint all buttons default blue, paint selected button dark blue and also switch cards as necessary
        setButtonSelected(transactionCardPanelList, 0, transactionCardButtonList, buttonStyle, transactionCardButtonList.get(0), 0, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(transactionCardPanelList, 1, transactionCardButtonList, buttonStyle, transactionCardButtonList.get(1), 1, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(transactionCardPanelList, 2, transactionCardButtonList, buttonStyle, transactionCardButtonList.get(2), 2, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(transactionCardPanelList, 3, transactionCardButtonList, buttonStyle, transactionCardButtonList.get(3), 3, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(transactionCardPanelList, 4, transactionCardButtonList, buttonStyle, transactionCardButtonList.get(4), 4, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));

        //manage customer additional buttons
        paintButtonDefaultSize(quitButton, buttonStyle.get("Blue"));

        //manage customer additional buttons
        paintButtonGreenWithHoverEffect(submitButton_customer);
        paintButtonGreenWithHoverEffect(searchButton_customerName);
        paintButtonGreenWithHoverEffect(searchButton_customerEmail);
        paintButtonGreenWithHoverEffect(searchButton_customerPhone);
        paintButtonGreenWithHoverEffect(updateButton_customerFName);
        paintButtonGreenWithHoverEffect(updateButton_customerLName);
        paintButtonGreenWithHoverEffect(updateButton_customerEmail);
        paintButtonGreenWithHoverEffect(updateButton_customerPhone);

        //manage members additional buttons painting
        paintButtonGreenWithHoverEffect(submitButton_createMembership);
        paintButtonGreenWithHoverEffect(searchButton_usernameMembership);
        paintButtonGreenWithHoverEffect(updateButton_firstNameMembership);
        paintButtonGreenWithHoverEffect(updateButton_lastNameMembership);
        paintButtonGreenWithHoverEffect(updateButton_phoneMember);
        paintButtonGreenWithHoverEffect(updateButton_emailMembership);
        paintButtonGreenWithHoverEffect(updateButton_usernameMembership);
        paintButtonGreenWithHoverEffect(updateButton_passwordMembership);
        paintButtonGreenWithHoverEffect(updateButton_planMembership);
        paintButtonGreenWithHoverEffect(updateButton_statusMembership);
        paintButtonGreenWithHoverEffect(updateButton_priceMembership);
        paintButtonGreenWithHoverEffect(updateButton_expirationMembership);

        //manage transaction additional buttons painting
        paintButtonGreenWithHoverEffect(purchaseButtonStartTransaction);
        paintButtonGreenWithHoverEffect(searchButtonSearchByNumber);
        paintButtonGreenWithHoverEffect(searchButtonSearchWithinDateRange);
        paintButtonGreenWithHoverEffect(refundButtonRefundTransaction);
        paintButtonGreenWithHoverEffect(cancelButtonCancelTransaction);
    }

    private void manageCustomerActions() {
        //button actions for manage customers page
        CustomersService customersService = new CustomersService();
        resultPanel.setVisible(false);
        //create new customer submit button
        submitButton_customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("Submit button pressed for creating customer.");
                if(createCustomerFname.getText().isEmpty() || createCustomerLname.getText().isEmpty() || createCustomerEmail.getText().isEmpty() || createCustomerPhone.getText().isEmpty()){
                    failResultPopUp("Fail", "Please Fill in all Fields");
                }else{
                    resultPanel.setVisible(false);
                    String firstName = createCustomerFname.getText();
                    String lastName = createCustomerLname.getText();
                    String email = createCustomerEmail.getText();
                    String phoneNumber = createCustomerPhone.getText();
                    customersService.createdCustomer_User(firstName, lastName, email, phoneNumber);
                    log.info("Request sent to API successfully");
                    successResultPopUp("Success", "Successfully Created Customer");
                }

            }
        });

        //search customer by name button
        searchButton_customerName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("Search button pressed for search by name");
                resultPanel.setVisible(false);
                String firstName = searchNameFname.getText();
                String lastName = searchNameLname.getText();
                List<Customers> resultsList = customersService.findCustomerByFirstAndLastName_User(firstName, lastName);
                log.info("Request sent to API successfully");
                showResults(resultsList);
            }
        });

        //search customer by email button
        searchButton_customerEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPanel.setVisible(false);
                String email = searchEmail_Email.getText();
                List<Customers> resultsList = customersService.findCustomerByEmail_User(email);
                log.info("Request sent to API successfully");
                showResults(resultsList);
            }
        });

        //search customer by phone button
        searchButton_customerPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPanel.setVisible(false);
                String phone = searchNumber_number.getText();
                List<Customers> resultsList = customersService.findCustomerByPhoneNumber_User(phone);
                log.info("Request sent to API successfully");
                showResults(resultsList);
            }
        });

        //update customer first name button
        updateButton_customerFName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPanel.setVisible(false);
                String oldFirstName = updateFname_fname.getText();
                String lastName = updateFname_lname.getText();
                String newFirstName = updateFname_newFname.getText();
                customersService.updateCustomerFirstName_User(oldFirstName, lastName, newFirstName);
                log.info("Request sent to API successfully");
                successResultPopUp("Success", "Successfully Updated Customer First Name");
            }
        });

        //update customer last name button
        updateButton_customerLName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPanel.setVisible(false);
                String firstName = updateLname_fname.getText();
                String oldLastName = updateLname_oldLname.getText();
                String newLastName = updateLname_newLname.getText();
                customersService.updateCustomerLastName_User(firstName, oldLastName, newLastName);
                log.info("Request sent to API successfully");
                successResultPopUp("Success", "Successfully Updated Customer Last Name");
            }
        });

        //update customer email button
        updateButton_customerEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPanel.setVisible(false);
                String firstName = updateEmail_fname.getText();
                String lastName = updateEmail_lname.getText();
                String newEmail = updateEmail_newEmail.getText();
                customersService.updateCustomerEmail_User(firstName, lastName, newEmail);
                log.info("Request sent to API successfully");
                successResultPopUp("Success", "Successfully Updated Customer Email");
            }
        });

        //update customer phone number button
        updateButton_customerPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPanel.setVisible(false);
                String firstName = updatePhone_fname.getText();
                String lastName = updatePhone_lname.getText();
                String newPhone = updatePhone_newPhone.getText();
                customersService.updateCustomerPhoneNumber_User(firstName, lastName, newPhone);
                log.info("Request sent to API successfully");
                successResultPopUp("Success", "Successfully Updated Customer Phone");
            }
        });
    }


    //------------------------------------------------------------------------------------------------HELPERS------------------------------------------------------------------------------------------------
    private void showResults(List<Customers> resultsList){
        for(Customers customers : resultsList){
            firstNameResult.setText(customers.getFirstName());
            lastNameResult.setText(customers.getLastName());
            emailResult.setText(customers.getEmail());
            phoneResult.setText(customers.getPhoneNumber());
        }
        resultPanel.setVisible(true);
        log.info("Result displayed to user successfully");
    }

    public static void successResultPopUp(String title, String message){
        ImageIcon icon = new ImageIcon("src/main/resources/greenCheck.png");
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
        log.info("Result displayed to user successfully");
    }

    public static void failResultPopUp(String title, String message){
        ImageIcon icon = new ImageIcon("src/main/resources/greenCheck.png");
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
        log.info("Failed to retrieve results");
    }

    public void setButtonSelected(List<JPanel> panelList, int panelToShow, List<JToggleButton> buttonList, HashMap<String, String> buttonStyle, JToggleButton buttonToClick, int buttonToToggle, String hoverEnterPath, String hoverExitPath) {
        //paint all buttons to default blue
        for (JToggleButton button : buttonList) {
            paintButtonDefaultSize(button, buttonStyle.get("Blue"));
        }
        buttonToClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPanel.setVisible(false);
                for (int i = 0; i < buttonList.size(); i++) {
                    if (i == buttonToToggle) {
                        paintButtonToggle(buttonList.get(i), buttonStyle.get("DarkBlue"));
                    } else {
                        buttonList.get(i).setSelected(false);
                        paintButtonToggle(buttonList.get(i), buttonStyle.get("Blue"));
                    }
                }

                for (int i = 0; i < panelList.size(); i++) {
                    if (i == panelToShow) {
                        panelList.get(i).setVisible(true);
                    } else {
                        panelList.get(i).setVisible(false);
                    }
                }
            }
        });
    }

    public static HashMap<String, String> getButtonStyleHashMap() {
        HashMap<String, String> buttonStyle = new HashMap<>();
        buttonStyle.put("Blue", "src/main/resources/buttonImages/blueButton.png");
        buttonStyle.put("DarkBlue", "src/main/resources/buttonImages/darkBlueButton.png");
        buttonStyle.put("Green", "src/main/resources/buttonImages/greenButton.png");
        buttonStyle.put("DarkGreen", "src/main/resources/buttonImages/darkGreenButton.png");
        return buttonStyle;
    }

    public void paintButtonGreenWithHoverEffect(JButton button){
        HashMap<String, String> buttonStyle = getButtonStyleHashMap();
        paintButtonDefaultSize(button, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(button, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));
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

    public void paintButtonToggle(JToggleButton button, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image newimage = img.getScaledInstance(150, 30, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimage);

        button.setIcon(newIcon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setForeground(Color.WHITE);
    }






    //main method
    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplicationGUI.class, args);
        frame.setContentPane(new BookStoreApplicationGUI().mainPanel);
        frame.setSize(1200, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);
    }



}
