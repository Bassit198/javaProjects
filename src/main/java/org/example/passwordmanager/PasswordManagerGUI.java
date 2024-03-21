package org.example.passwordmanager;

import com.sun.jdi.event.MonitorContendedEnteredEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

@SpringBootApplication
public class PasswordManagerGUI {

    private final RestTemplate restTemplate = new RestTemplate();


    private static JFrame frame = new JFrame("Password Manager");
    private JPanel basePanel;
    private JPanel mainPanel;
    private JPanel contentPane;

    //login page
    private JPanel loginPagePanel;
    private JTextField username_loginPage;
    private JTextField password_loginPage;
    private JButton loginButton_loginPage;
    private JButton clearButton_loginPage;
    private JLabel usernameLabel;
    private JPanel homePagePanel;
    private JToggleButton addAccountButton;
    private JToggleButton getAccountButton;
    private JToggleButton deleteAccountButton;
    private JPanel sideBarPanel;
    private JPanel mainViewPanel;
    private JToggleButton updateAccountButton;
    private JButton logoutButton;
    private JPanel addAccountPanel;
    private JTextField accountName_addAccount;
    private JTextField accountUsername_addAccount;
    private JTextField accountPassword_addAccount;
    private JButton addButton;
    private JButton clearButton;
    private JLabel loggedInUserLabel;
    private JPanel getAccountPanel;
    private JPanel deleteAccountPanel;
    private JPanel updateAccountPanel;


    public PasswordManagerGUI() {

        loggedInUserLabel.setVisible(false);

        List<JPanel> sideBarPanelList = new ArrayList<>();
        Collections.addAll(sideBarPanelList, addAccountPanel, getAccountPanel, deleteAccountPanel, updateAccountPanel);

        List<JToggleButton> sideBarButtonList = new ArrayList<>();
        Collections.addAll(sideBarButtonList, addAccountButton, getAccountButton, deleteAccountButton, updateAccountButton);

        HashMap<String, String> buttonStyle = getButtonStyleHashMap();

        setButtonSelected(sideBarPanelList, 0, sideBarButtonList, buttonStyle, sideBarButtonList.get(0), 0, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(sideBarPanelList, 1, sideBarButtonList, buttonStyle, sideBarButtonList.get(1), 1, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(sideBarPanelList, 2, sideBarButtonList, buttonStyle, sideBarButtonList.get(2), 2, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        setButtonSelected(sideBarPanelList, 3, sideBarButtonList, buttonStyle, sideBarButtonList.get(3), 3, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));



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
                String username = username_loginPage.getText();
                String password = password_loginPage.getText();
                checkUserCredentials(username, password);

            }
        });

        //add account buttons
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountName_addAccount.setText("");
                accountUsername_addAccount.setText("");
                accountPassword_addAccount.setText("");
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loggedInUserLabel.getText();
                final String url = "http://localhost:8080/passwords/" + username + "/add";
                Map<String, String> map = new HashMap<>();
                map.put("accountName", accountName_addAccount.getText());
                map.put("accountUsername", accountUsername_addAccount.getText());
                map.put("accountPassword", accountPassword_addAccount.getText());
                restTemplate.postForEntity(url, map, Void.class);
                JOptionPane.showMessageDialog(null, "Account Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void checkUserCredentials(String username, String password){
        final String uri = "http://localhost:8080/users/checkPassword/" + username + "/" + password;
        Integer result = restTemplate.getForObject(uri, Integer.class);

        assert result != null;
        if(result == 1){
            final String url = "http://localhost:8080/session/create";
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

    public void switchPanel(List<JPanel> panelList, int panelToShow){
        for (int i = 0; i < panelList.size(); i++) {
            if (i == panelToShow) {
                panelList.get(i).setVisible(true);
            } else {
                panelList.get(i).setVisible(false);
            }
        }
    }



    public void setButtonSelected(List<JPanel> panelList, int panelToShow, List<JToggleButton> buttonList, HashMap<String, String> buttonStyle, JToggleButton buttonToClick, int buttonToToggle, String hoverEnterPath, String hoverExitPath) {
        //paint all buttons to default blue
        for (JToggleButton button : buttonList) {
            paintButtonDefaultSize(button, buttonStyle.get("Blue"));
        }
        buttonToClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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


    public void paintButtonDefaultSize(JToggleButton button, String imagePath){
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

    public void paintButtonToggle(JToggleButton button, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image newimage = img.getScaledInstance(150, 26, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimage);

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
        return buttonStyle;
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

        //JOptionPane.showMessageDialog(null, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//end main
}


