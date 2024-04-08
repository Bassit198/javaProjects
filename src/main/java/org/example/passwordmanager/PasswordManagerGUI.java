package org.example.passwordmanager;

import org.example.passwordmanager.Models.Passwords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

@SpringBootApplication
public class PasswordManagerGUI {

    private static final RestTemplate restTemplate = new RestTemplate();


    private static JFrame frame = new JFrame("Password Manager");
    private JPanel basePanel;
    private JPanel mainPanel;
    private JPanel contentPane;
    private JPanel sideBarPanel;
    private JPanel mainViewPanel;
    private JLabel usernameLabel;
    private JLabel pageLabel;

    //login page
    private JPanel loginPagePanel;
    private JTextField username_loginPage;
    private JTextField password_loginPage;
    private JButton loginButton_loginPage;
    private JButton clearButton_loginPage;

    //side bar components
    private JPanel homePagePanel;
    private JToggleButton addAccountButton;
    private JToggleButton getAccountListButton;
    private JToggleButton deleteAccountButton;
    private JToggleButton updateAccountButton;
    private JButton logoutButton;

    //add account page components
    private JPanel addAccountPanel;
    private JTextField accountName_addAccount;
    private JTextField accountUsername_addAccount;
    private JTextField accountPassword_addAccount;
    private JButton addButton_addAccountPage;
    private JButton clearButton_addAccountPage;
    private JLabel loggedInUserLabel;

    //get account page components
    private JPanel getAccountPanel;

    //delete account page components
    private JPanel deleteAccountPanel;

    //update account page components
    private JPanel updateAccountPanel;

    //find account list components
    private JButton showAccounts_getAccountPage;
    private JScrollPane scrollPane_getAccount;
    private JList listOfAccounts;

    //find account password components
    private JToggleButton findPasswordButton;
    private JPanel findPasswordPanel;
    private JTextField accountName_findAccount;
    private JButton searchButton_findAccount;
    private JButton clearButton_findAccount;
    private JList passwordList_findAccount;
    private JScrollPane scrollPane_findAccount;

    //delete account components
    private JTextField accountName_deleteAccount;
    private JTextField userPassword_deleteAccount;
    private JButton deleteButton_deleteAccount;
    private JButton clearButton_deleteAccount;

    //update account components
    private JPanel contentPanel_updateAccount;
    private JPanel updateAccountNamePanel_updateAccount;
    private JPanel updateAccountPasswordPanel_updateAccount;
    private JPanel updateAccountUserNamePanel_udpateAccount;

    private JToggleButton updateAccountName_updateAccount;
    private JToggleButton updateAccountUsername_updateAccount;
    private JToggleButton updateAccountPassword_updateAccount;
    private JTextField oldAccountName_updateAccount;
    private JTextField newAccountName_updateAccount;
    private JButton updateButton_updateAccountName;


    //models for lists
    private final DefaultListModel<String> listOfAccountsModel = new DefaultListModel<>();
    private final DefaultListModel<String> passwordListModel = new DefaultListModel<>();


    public PasswordManagerGUI() {

        loggedInUserLabel.setVisible(false);


        List<JPanel> sideBarPanelList = new ArrayList<>();
        Collections.addAll(sideBarPanelList, addAccountPanel, getAccountPanel, findPasswordPanel, deleteAccountPanel, updateAccountPanel);

        List<JToggleButton> sideBarButtonList = new ArrayList<>();
        Collections.addAll(sideBarButtonList, addAccountButton, getAccountListButton, findPasswordButton,deleteAccountButton, updateAccountButton);

        List<JToggleButton> updateAccountButtonList = new ArrayList<>();
        Collections.addAll(updateAccountButtonList, updateAccountName_updateAccount, updateAccountUsername_updateAccount, updateAccountPassword_updateAccount);

        List<JPanel> updateAccountPanelList = new ArrayList<>();
        Collections.addAll(updateAccountPanelList, updateAccountNamePanel_updateAccount, updateAccountUserNamePanel_udpateAccount, updateAccountPasswordPanel_updateAccount);

        HashMap<String, String> buttonStyle = getButtonStyleHashMap();

        setButtonSelectedDefaultSize(sideBarPanelList, 0, sideBarButtonList, buttonStyle, sideBarButtonList.get(0), 0, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelectedDefaultSize(sideBarPanelList, 1, sideBarButtonList, buttonStyle, sideBarButtonList.get(1), 1, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelectedDefaultSize(sideBarPanelList, 2, sideBarButtonList, buttonStyle, sideBarButtonList.get(2), 2, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelectedDefaultSize(sideBarPanelList, 3, sideBarButtonList, buttonStyle, sideBarButtonList.get(3), 3, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelectedDefaultSize(sideBarPanelList, 4, sideBarButtonList, buttonStyle, sideBarButtonList.get(4), 4, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));

        setButtonSelectedVariousSize(updateAccountPanelList, 0, updateAccountButtonList, buttonStyle, updateAccountButtonList.get(0), 0, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelectedVariousSize(updateAccountPanelList, 1, updateAccountButtonList, buttonStyle, updateAccountButtonList.get(1), 1, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelectedVariousSize(updateAccountPanelList, 2, updateAccountButtonList, buttonStyle, updateAccountButtonList.get(2), 2, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));

        paintButtonWithHoverEffect(clearButton_loginPage, buttonStyle.get("Blue"), buttonStyle.get("Red"), 90, 26);
        paintButtonWithHoverEffect(loginButton_loginPage, buttonStyle.get("Blue"), buttonStyle.get("Green"), 90, 26);
        paintButtonWithHoverEffect(logoutButton, buttonStyle.get("Blue"), buttonStyle.get("Red"), 150, 26);

        paintButtonWithHoverEffect(clearButton_addAccountPage, buttonStyle.get("Blue"), buttonStyle.get("Red"), 90, 26);
        paintButtonWithHoverEffect(addButton_addAccountPage, buttonStyle.get("Blue"), buttonStyle.get("Green"), 90, 26);

        paintButtonWithHoverEffect(showAccounts_getAccountPage, buttonStyle.get("Blue"), buttonStyle.get("Green"), 90, 26);

        paintButtonWithHoverEffect(clearButton_findAccount, buttonStyle.get("Blue"), buttonStyle.get("Red"), 90, 26);
        paintButtonWithHoverEffect(searchButton_findAccount, buttonStyle.get("Blue"), buttonStyle.get("Green"), 90, 26);

        paintButtonWithHoverEffect(clearButton_deleteAccount, buttonStyle.get("Blue"), buttonStyle.get("Red"), 90, 26);
        paintButtonWithHoverEffect(deleteButton_deleteAccount, buttonStyle.get("Blue"), buttonStyle.get("Red"), 90, 26);





        //login page buttons
        clearButton_loginPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username_loginPage.setText("");
                password_loginPage.setText("");
            }
        });
        loginButton_loginPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(username_loginPage.getText().isEmpty() || password_loginPage.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter all fields", "Failed", JOptionPane.ERROR_MESSAGE);
                }else{
                    String username = username_loginPage.getText();
                    String password = password_loginPage.getText();
                    checkUserCredentials(username, password);
                    username_loginPage.setText("");
                    password_loginPage.setText("");
                }


            }
        });

        //add account buttons
        clearButton_addAccountPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountName_addAccount.setText("");
                accountUsername_addAccount.setText("");
                accountPassword_addAccount.setText("");
            }
        });
        addButton_addAccountPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(accountName_addAccount.getText().isEmpty() || accountUsername_addAccount.getText().isEmpty() || accountPassword_addAccount.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter all fields", "Failed", JOptionPane.ERROR_MESSAGE);
                }else{
                    String username = loggedInUserLabel.getText();
                    final String url = "http://localhost:8080/passwords/" + username + "/add";
                    Map<String, String> map = new HashMap<>();
                    map.put("accountName", accountName_addAccount.getText());
                    map.put("accountUsername", accountUsername_addAccount.getText());
                    map.put("accountPassword", accountPassword_addAccount.getText());
                    restTemplate.postForEntity(url, map, Void.class);
                    JOptionPane.showMessageDialog(null, "Account Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    accountUsername_addAccount.setText("");
                    accountPassword_addAccount.setText("");
                }
            }
        });

        //get account buttons
        showAccounts_getAccountPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loggedInUserLabel.getText();
                final String uri = "http://localhost:8080/passwords/showAccounts/" + username;
                Passwords[] passwordsArray = restTemplate.getForObject(uri, Passwords[].class);
                assert passwordsArray != null;
                List<Passwords> passwordsList = Arrays.asList(passwordsArray);

                if(passwordsList.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No accounts Found", "Failed", JOptionPane.ERROR_MESSAGE);
                }else{
                    listOfAccounts.setModel(listOfAccountsModel);
                    for (Passwords passwords : passwordsList) {
                        listOfAccountsModel.addElement("Account Name: " + passwords.getAccountName());
                        listOfAccountsModel.addElement(" ");
                    }
                    //scroll bar background
                    scrollPane_getAccount.getVerticalScrollBar().setBackground(Color.WHITE);
                    scrollPane_getAccount.getHorizontalScrollBar().setBackground(Color.WHITE);
                    //scroll bar
                    scrollPane_getAccount.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                        @Override
                        protected void configureScrollBarColors() {
                            this.thumbColor = new Color(200, 200, 200);
                            this.scrollBarWidth = 5;
                        }
                        //the 3 below override replaces the scroll bar buttons with regular buttons of 0x0 size
                        @Override
                        protected JButton createDecreaseButton(int orientation) {
                            return createZeroButton();
                        }
                        @Override
                        protected JButton createIncreaseButton(int orientation) {
                            return createZeroButton();
                        }
                        private JButton createZeroButton() {
                            JButton jbutton = new JButton();
                            jbutton.setPreferredSize(new Dimension(0, 0));
                            jbutton.setMinimumSize(new Dimension(0, 0));
                            jbutton.setMaximumSize(new Dimension(0, 0));
                            return jbutton;
                        }
                    });
                }

            }
        });

        //find password buttons
        clearButton_findAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountName_findAccount.setText("");
                scrollPane_findAccount.setVisible(false);
            }
        });
        searchButton_findAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountName = accountName_findAccount.getText();
                String username = loggedInUserLabel.getText();
                final String uri = "http://localhost:8080/passwordsList/get/" + accountName + "/" + username;
                Passwords[] passwordsArray = restTemplate.getForObject(uri, Passwords[].class);
                assert passwordsArray != null;
                List<Passwords> passwordsList = Arrays.asList(passwordsArray);
                if (passwordsList.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No accounts Found", "Failed", JOptionPane.ERROR_MESSAGE);
                }else{

                    passwordList_findAccount.setModel(passwordListModel);
                    for (Passwords passwords : passwordsList) {
                        passwordListModel.addElement("Account Name: " + passwords.getAccountName());
                        passwordListModel.addElement(passwords.getAccountPassword());
                        passwordListModel.addElement(" ");
                    }
                    scrollPane_findAccount.setVisible(true);

                    //scroll bar background
                    scrollPane_findAccount.getVerticalScrollBar().setBackground(Color.WHITE);
                    scrollPane_findAccount.getHorizontalScrollBar().setBackground(Color.WHITE);
                    //scroll bar
                    scrollPane_findAccount.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                        @Override
                        protected void configureScrollBarColors() {
                            this.thumbColor = new Color(200, 200, 200);
                            this.scrollBarWidth = 5;
                        }
                        //the 3 below override replaces the scroll bar buttons with regular buttons of 0x0 size
                        @Override
                        protected JButton createDecreaseButton(int orientation) {
                            return createZeroButton();
                        }
                        @Override
                        protected JButton createIncreaseButton(int orientation) {
                            return createZeroButton();
                        }
                        private JButton createZeroButton() {
                            JButton jbutton = new JButton();
                            jbutton.setPreferredSize(new Dimension(0, 0));
                            jbutton.setMinimumSize(new Dimension(0, 0));
                            jbutton.setMaximumSize(new Dimension(0, 0));
                            return jbutton;
                        }

                    });
                }

            }
        });

        //delete account buttons
        clearButton_deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountName_deleteAccount.setText("");
                userPassword_deleteAccount.setText("");
            }
        });
        deleteButton_deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loggedInUserLabel.getText();
                String accountName = accountName_deleteAccount.getText();
                String userPassword = userPassword_deleteAccount.getText();
                final String uri = "http://localhost:8080/passwords/delete/" + username + "/" + accountName + "/" + userPassword;
                //restTemplate.postForEntity(uri, null, String.class);
                Integer result = restTemplate.getForObject(uri, Integer.class);
                assert result!=null;
                if(result == 1){
                    JOptionPane.showMessageDialog(null, "Account Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    accountName_deleteAccount.setText("");
                    userPassword_deleteAccount.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "Unable to remove account. Invalid information entered or account not found", "Failed", JOptionPane.ERROR_MESSAGE);
                }




            }
        });

        //update account buttons
        updateButton_updateAccountName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loggedInUserLabel.getText();
                String accountName = oldAccountName_updateAccount.getText();

                if(username.isEmpty() || accountName.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Invalid", JOptionPane.ERROR_MESSAGE);
                }else{
                    final String uri = "http://localhost:8080/passwords/updateAccountName/ " + username + "/" + accountName;
                    Map<String, String> map = new HashMap<>();
                    map.put("accountName", newAccountName_updateAccount.getText());
                    Integer request = restTemplate.postForObject(uri, map, Integer.class);
                    if(request == null){
                        JOptionPane.showMessageDialog(null, "Error processing your request", "Try Again", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(request == 200){
                            JOptionPane.showMessageDialog(null, "Account name updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "No account found with that name", "Invalid", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });





        //logout button action
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOutUser();
            }
        });



    }//end class

    public void logOutUser() {
        List<JPanel> panelList = new ArrayList<>();
        Collections.addAll(panelList, loginPagePanel, homePagePanel);

        String username = loggedInUserLabel.getText();
        final String uri = "http://localhost:8080/session/logout/" + username;
        restTemplate.postForEntity(uri, null, Void.class);
        JOptionPane.showMessageDialog(null, "Logged out Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        //clear all input upon log out
        listOfAccountsModel.removeAllElements();
        passwordListModel.removeAllElements();
        username_loginPage.setText("");
        password_loginPage.setText("");
        accountName_addAccount.setText("");
        accountUsername_addAccount.setText("");
        accountPassword_addAccount.setText("");
        accountName_findAccount.setText("");

        switchPanel(panelList, 0);
    }

    public void checkUserCredentials(String username, String password){
        final String uri = "http://localhost:8080/users/checkPassword/" + username + "/" + password;
        Integer result = restTemplate.getForObject(uri, Integer.class);

        assert result != null;
        if(result == 1){
            final String url = "http://localhost:8080/session/create/" + username;
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            restTemplate.postForEntity(url, map, Void.class);
            JOptionPane.showMessageDialog(null, "Login Successfully", "Approved", JOptionPane.INFORMATION_MESSAGE);
            loggedInUserLabel.setText(username);
            loggedInUserLabel.setVisible(true);
            List<JPanel> panelList = new ArrayList<>();
            Collections.addAll(panelList, loginPagePanel, homePagePanel);
            switchPanel(panelList, 1);
        }else{
            JOptionPane.showMessageDialog(null, "Invalid Credentials. Try Again.", "Denied", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void switchPanel(List<JPanel> panelList, int panelToShow){
        for (int i = 0; i < panelList.size(); i++) {
            if (i == panelToShow) {
                panelList.get(i).setVisible(true);
            } else {
                panelList.get(i).setVisible(false);
            }
        }
    }

    public void setButtonSelectedDefaultSize(List<JPanel> panelList, int panelToShow, List<JToggleButton> buttonList, HashMap<String, String> buttonStyle, JToggleButton buttonToClick, int buttonToToggle, String hoverEnterPath, String hoverExitPath) {
        //paint all buttons to default blue
        for (JToggleButton button : buttonList) {
            paintToggleButtonDefaultSize(button, buttonStyle.get("Blue"));
        }
        buttonToClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < buttonList.size(); i++) {
                    if (i == buttonToToggle) {
                        paintToggleButtonDefaultSize(buttonList.get(i), buttonStyle.get("DarkBlue"));
                        pageLabel.setText(buttonList.get(buttonToToggle).getText());
                    } else {
                        buttonList.get(i).setSelected(false);
                        paintToggleButtonDefaultSize(buttonList.get(i), buttonStyle.get("Blue"));
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

    public void setButtonSelectedVariousSize(List<JPanel> panelList, int panelToShow, List<JToggleButton> buttonList, HashMap<String, String> buttonStyle, JToggleButton buttonToClick, int buttonToToggle, String hoverEnterPath, String hoverExitPath) {
        //paint all buttons to default blue
        for (JToggleButton button : buttonList) {
            paintButtonVariousSize(button, buttonStyle.get("Blue"), 90, 26);
        }
        buttonToClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < buttonList.size(); i++) {
                    if (i == buttonToToggle) {

                        paintButtonVariousSize(buttonList.get(i), buttonStyle.get("DarkBlue"), 90, 26);
                        pageLabel.setText(buttonList.get(buttonToToggle).getText());
                    } else {
                        buttonList.get(i).setSelected(false);
                        paintButtonVariousSize(buttonList.get(i), buttonStyle.get("Blue"), 90, 26);
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

    public void paintToggleButtonDefaultSize(JToggleButton button, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(150, 26, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        button.setIcon(newIcon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setForeground(Color.WHITE);
    }

    public void paintButtonVariousSize(JToggleButton button, String imagePath, int width, int height){
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

    public static HashMap<String, String> getButtonStyleHashMap() {
        HashMap<String, String> buttonStyle = new HashMap<>();
        buttonStyle.put("Blue", "src/main/resources/buttonImages/blueButton.png");
        buttonStyle.put("DarkBlue", "src/main/resources/buttonImages/darkBlueButton.png");
        buttonStyle.put("Green", "src/main/resources/buttonImages/greenButton.png");
        buttonStyle.put("DarkGreen", "src/main/resources/buttonImages/darkGreenButton.png");
        buttonStyle.put("Red", "src/main/resources/buttonImages/redButton.png");
        return buttonStyle;
    }

    public void paintButton(JButton button, String imagePath, int width, int height){
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

    public void paintButtonWithHoverEffect(JButton button, String imagePath, String hoverImagePath, int width, int height){
        HashMap<String, String> buttonStyle = getButtonStyleHashMap();
        paintButton(button, imagePath, width, height);
        //hoverEffectButtonDefaultSize(button, hoverImagePath, imagePath);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paintButton(button, hoverImagePath, width, height);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paintButton(button, imagePath, width, height);
            }
        });
    }


    //main method
    public static void main(String[] args) {
        SpringApplication.run(PasswordManagerGUI.class, args);
        frame.setContentPane(new PasswordManagerGUI().basePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);
    }//end main
}


