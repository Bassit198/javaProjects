import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.*;

public class JokesFunGUI {
    private JPanel mainPanel;
    private JPanel basePanel;
    private JPanel containerPanel;
    private JPanel resultsPanel;
    private JLabel categoryLabel;
    private JRadioButton darkRadioButton;
    private JRadioButton christmasRadioButton;
    private JRadioButton punRadioButton;
    private JRadioButton spookyRadioButton;
    private JRadioButton miscRadioButton;
    private JRadioButton programmingRadioButton;
    private JLabel amountLabel;
    private JTextField amountText;
    private JLabel headingLabel;
    private JButton getJokeButton;
    private JTextArea resultsTextArea;

    private final DefaultListModel<String> listOfAccountsModel = new DefaultListModel<>();

    public JokesFunGUI() throws IOException, InterruptedException {

        ButtonGroup categoryGroup = new ButtonGroup();
        categoryGroup.add(darkRadioButton);
        categoryGroup.add(punRadioButton);
        categoryGroup.add(spookyRadioButton);
        categoryGroup.add(miscRadioButton);
        categoryGroup.add(programmingRadioButton);
        categoryGroup.add(christmasRadioButton);

        HelperFunctions helperFunctions = new HelperFunctions();

        helperFunctions.paintBackground(containerPanel);
        helperFunctions.paintBackground(resultsPanel);

        helperFunctions.paintRadioButton(darkRadioButton, categoryLabel);
        helperFunctions.paintRadioButton(punRadioButton, categoryLabel);
        helperFunctions.paintRadioButton(spookyRadioButton, categoryLabel);
        helperFunctions.paintRadioButton(miscRadioButton, categoryLabel);
        helperFunctions.paintRadioButton(programmingRadioButton, categoryLabel);
        helperFunctions.paintRadioButton(christmasRadioButton, categoryLabel);

        helperFunctions.paintTextField(amountText, categoryLabel);

        HashMap<String, String> buttonStyle = helperFunctions.getButtonStyleHashMap();
        helperFunctions.paintButtonDefaultSize(getJokeButton, buttonStyle.get("dGreen"), 100, 28);
        helperFunctions.hoverEffectButtonDefaultSize(getJokeButton, buttonStyle.get("green"), buttonStyle.get("dGreen"), 100, 28);

        resultsTextArea.setEditable(false);
        resultsTextArea.setFont(categoryLabel.getFont());
        resultsTextArea.setForeground(headingLabel.getForeground());
        resultsTextArea.setBackground(containerPanel.getBackground());
        resultsPanel.setLayout(new BorderLayout());

        // Create a JTextArea for displaying text
        resultsTextArea.setWrapStyleWord(true);
        resultsTextArea.setLineWrap(true);

        // Add the text area to a JScrollPane with a vertical scrollbar
        JScrollPane scrollPane = new JScrollPane(resultsTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create a vertical scrollbar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

        // Add components to the content panel
        resultsPanel.add(scrollPane, BorderLayout.CENTER);
        resultsTextArea.setMargin(new Insets(5, 10,5,10));

        //scroll bar design
        scrollPane.getVerticalScrollBar().setBackground(containerPanel.getBackground());
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
                this.scrollBarWidth = 7;
            }
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

        getJokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //only dark
                if(darkRadioButton.isSelected()){
                    String uri = "https://v2.jokeapi.dev/joke/Dark?format=txt";
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Pun
                if(punRadioButton.isSelected()){
                    String uri = "https://v2.jokeapi.dev/joke/Pun?format=txt";
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Spooky
                if(spookyRadioButton.isSelected()){
                    String uri = "https://v2.jokeapi.dev/joke/Spooky?format=txt";
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Misc
                if(miscRadioButton.isSelected()){
                    String uri = "https://v2.jokeapi.dev/joke/Miscellaneous?format=txt";
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Programming
                if(programmingRadioButton.isSelected()){
                    String uri = "https://v2.jokeapi.dev/joke/Programming?format=txt";
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Christmas
                if(christmasRadioButton.isSelected()){
                    String uri = "https://v2.jokeapi.dev/joke/Christmas?format=txt";
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }

                //amount
                if(darkRadioButton.isSelected() && !amountText.getText().isEmpty()){
                    String uri = "https://v2.jokeapi.dev/joke/Dark?format=txt&amount=" + amountText.getText();
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Pun
                if(punRadioButton.isSelected() && !amountText.getText().isEmpty()){
                    String uri = "https://v2.jokeapi.dev/joke/Pun?format=txt&amount=" + amountText.getText();
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Spooky
                if(spookyRadioButton.isSelected() && !amountText.getText().isEmpty()){
                    String uri = "https://v2.jokeapi.dev/joke/Spooky?format=txt&amount=" + amountText.getText();
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Misc
                if(miscRadioButton.isSelected() && !amountText.getText().isEmpty()){
                    String uri = "https://v2.jokeapi.dev/joke/Miscellaneous?format=txt&amount=" + amountText.getText();;
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Programming
                if(programmingRadioButton.isSelected() && !amountText.getText().isEmpty()){
                    String uri = "https://v2.jokeapi.dev/joke/Programming?format=txt&amount=" + amountText.getText();;
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
                //only Christmas
                if(christmasRadioButton.isSelected() && !amountText.getText().isEmpty()){
                    String uri = "https://v2.jokeapi.dev/joke/Christmas?format=txt&amount=" + amountText.getText();;
                    HttpResponse<String> response = helperFunctions.sendApiGet(uri);
                    resultsTextArea.setText("\n" + response.body() + "\n==================================================================");
                }
            }
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame frame = new JFrame("Jokes");
        frame.setContentPane(new JokesFunGUI().basePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
