package main;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Generator implements ActionListener {


    JFrame frame = new JFrame("Password Generator");

    JPanel welcomePanel = new JPanel();
    JPanel instructionsPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    JPanel resultsPanel = new JPanel();

    JLabel welcomeLabel = new JLabel("Password Generator");
    JLabel instructionsLabel = new JLabel("Enter text to be encrypted below");
    JLabel instructionsLabel2 = new JLabel("Leave blank if generating password");
    JTextField toEncryptTextField = new JTextField();
    JTextField resultsTextField = new JTextField();

    JButton clearButton = new JButton("Clear");
    JButton generateButton = new JButton("Generate");
    JButton encryptButton = new JButton("Encrypt");

    JRadioButton digit8 = new JRadioButton("8-Digits");
    JRadioButton digit10 = new JRadioButton("10-Digits");
    JRadioButton digit12 = new JRadioButton("12-Digits");
    ButtonGroup group = new ButtonGroup();

    GridLayout instructionGridLayout = new GridLayout(3, 1, 0, 10);
    GridLayout buttonGridLayout = new GridLayout(2, 3, 20, 0);
    GridLayout resultGridLayout = new GridLayout(1, 1, 0, 0);





    void launcher(){

        //panel config
        //welcomePanel.setBackground(Color.BLACK);
        welcomePanel.setBounds(85,10,250,50);

        //instructionsPanel.setBackground(Color.RED);
        instructionsPanel.setBounds(25,80, 355,120);
        instructionsPanel.setLayout(instructionGridLayout);

        //buttonsPanel.setBackground(Color.GREEN);
        buttonsPanel.setBounds(25, 210, 355, 65);
        buttonsPanel.setLayout(buttonGridLayout);

        //resultsPanel.setBackground(Color.BLUE);
        resultsPanel.setBounds(25,290,355,75);
        resultsPanel.setLayout(resultGridLayout);

        //panel components config
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 26));

        instructionsLabel.setFont(new Font(null, Font.PLAIN, 16));
        instructionsLabel2.setFont(new Font(null, Font.PLAIN, 16));
        instructionsLabel.setHorizontalAlignment(JLabel.CENTER);
        instructionsLabel2.setHorizontalAlignment(JLabel.CENTER);

        digit8.setHorizontalAlignment(JLabel.CENTER);
        digit8.addActionListener(this);
        digit10.setHorizontalAlignment(JLabel.CENTER);
        digit10.addActionListener(this);
        digit12.setHorizontalAlignment(JLabel.CENTER);
        digit12.addActionListener(this);
        clearButton.setHorizontalAlignment(JLabel.CENTER);
        clearButton.addActionListener(this);
        generateButton.setHorizontalAlignment(JLabel.CENTER);
        generateButton.addActionListener(this);
        encryptButton.setHorizontalAlignment(JLabel.CENTER);
        encryptButton.addActionListener(this);

        //add radio button to group so only one can be selected at a time
        group.add(digit8);
        group.add(digit10);
        group.add(digit12);

        resultsTextField.setHorizontalAlignment(JLabel.CENTER);
        resultsTextField.setFont(new Font(null, Font.PLAIN, 18));
        resultsTextField.setBackground(null);
        resultsTextField.setEditable(false);
        resultsTextField.setVisible(false);

        //add components to panels
        welcomePanel.add(welcomeLabel);

        instructionsPanel.add(instructionsLabel);
        instructionsPanel.add(instructionsLabel2);
        instructionsPanel.add(toEncryptTextField);

        buttonsPanel.add(digit8);
        buttonsPanel.add(digit10);
        buttonsPanel.add(digit12);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(generateButton);
        buttonsPanel.add(encryptButton);

        resultsPanel.add(resultsTextField);

        //add components to frame
        frame.add(welcomePanel);
        frame.add(instructionsPanel);
        frame.add(buttonsPanel);
        frame.add(resultsPanel);

        //frame config
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(420,420);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }//end launcher




    @Override
    public void actionPerformed(ActionEvent e) {
        //clear button action
        if(e.getSource() == clearButton){
            toEncryptTextField.setText("");
            resultsTextField.setVisible(false);
            group.clearSelection();
        }//end clear button action

        //generate button action
        if(e.getSource() == generateButton){

            //check if digit selection is made
            if(digit8.isSelected() || digit10.isSelected() || digit12.isSelected() ){

                int leftLimit = 35; // numeral '0' = 48 --> 35 = special characters
                int rightLimit = 122; // letter 'z'
                int passwordLength = 0;
                String genPassword = "";
                Random random = new Random();

                if(digit8.isSelected()){
                    passwordLength = 8;
                    genPassword = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(passwordLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
                }

                if(digit10.isSelected()){
                    passwordLength = 10;
                    genPassword = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(passwordLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
                }

                if(digit12.isSelected()){
                    passwordLength = 12;
                    genPassword = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(passwordLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
                }

                //genPassword = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
                resultsTextField.setText(genPassword);
                resultsTextField.setVisible(true);

            }else{
                resultsTextField.setText("Please Select Digits");
                resultsTextField.setVisible(true);

            }//end if selection

        }//end generate button action

        //encrypt button action
        if(e.getSource() == encryptButton){
            group.clearSelection();
            resultsTextField.setText("");
            resultsTextField.setVisible(false);

            String text = toEncryptTextField.getText();
            String encryptedText = "Coming Soon!";


            for(int i = 0; i <= text.length(); i++){

            }

            resultsTextField.setText(encryptedText);
            resultsTextField.setVisible(true);

        }//end encrypt button action

    }//end action performed
}
