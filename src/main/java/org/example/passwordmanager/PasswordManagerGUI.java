package org.example.passwordmanager;

import org.example.passwordmanager.Models.Users;
import org.example.passwordmanager.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public PasswordManagerGUI() {

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
                int result = checkUserCredentials(username, password);
                if(result == 1){
                    final String uri = "http://localhost:8080/session/create";
                    Map<String, String> map = new HashMap<>();
                    map.put("username", username);
                    restTemplate.postForEntity(uri, map, Void.class);
                    JOptionPane.showMessageDialog(null, "Login Successfully", "Approved", JOptionPane.INFORMATION_MESSAGE);

                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Credentials. Try Again.", "Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }

    public Integer checkUserCredentials(String username, String password){
        final String uri = "http://localhost:8080/users/checkPassword/" + username + "/" + password;
        return restTemplate.getForObject(uri, Integer.class);
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


