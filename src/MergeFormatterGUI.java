import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MergeFormatterGUI {
    private JPanel mainPanel;
    private JPanel basePanel;
    private JLabel TitleLabel;
    private JButton browseButton;
    private JLabel resultLabel;
    private JButton generateButton;
    private JLabel selectedLabel;
    private JRadioButton dayRadioButton;
    private JRadioButton monthRadioButton;
    private String path;

    public MergeFormatterGUI() {
        resultLabel.setVisible(false);
        selectedLabel.setVisible(false);

        ButtonGroup group = new ButtonGroup();
        group.add(dayRadioButton);
        group.add(monthRadioButton);

        //button styles
        HashMap<String, String> buttonStyle = getButtonStyleHashMap();

        paintButtonDefaultSize(browseButton, buttonStyle.get("Blue"));
        paintButtonDefaultSize(generateButton, buttonStyle.get("Green"));
        hoverEffectButtonDefaultSize(browseButton, buttonStyle.get("DarkBlue"), buttonStyle.get("Blue"));
        hoverEffectButtonDefaultSize(generateButton, buttonStyle.get("DarkGreen"), buttonStyle.get("Green"));

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    JFileChooser fileChooser = new JFileChooser();
                    int response = fileChooser.showOpenDialog(null);
                    path = fileChooser.getSelectedFile().getAbsolutePath();

                    if (response == JFileChooser.APPROVE_OPTION) {
                        selectedLabel.setText(fileChooser.getSelectedFile().getName());
                        selectedLabel.setVisible(true);
                    }
                    //System.out.println(path);
                } catch (Exception ex) {
                    resultLabel.setText("Invalid file or MergeOutput is open in Excel");
                    resultLabel.setVisible(true);
                }
            }

        });//end browse button action

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (path.contains("\"")) {
                    StringBuilder sb = new StringBuilder(path);
                    sb.deleteCharAt(0);
                    sb.deleteCharAt(path.length() - 2);
                    path = String.valueOf(sb);
                }

                //file path used for parsing file
                String file = path.replace("\\", "\\\\");

                //output path
                String outputPath = System.getProperty("user.home") + File.separator + "Downloads\\MergeOutput.csv";

                //create array to hold parse values then call parseFile method to parse and add to arrayList
                List<String[]> content = new ArrayList<>();
                try {
                    parseFile(content, file);
                } catch (IOException ex) {
                    resultLabel.setText("Invalid Input");
                    resultLabel.setVisible(true);
                } catch (RuntimeException rt) {
                    resultLabel.setText("Unable To Parse File. Invalid Format.");
                    selectedLabel.setVisible(false);
                    resultLabel.setVisible(true);
                }

                //creates a list of all the column in the form of arrays
                String[][] columnList = columns(content);

                //array for ccTypeID
                String[] ccTypeId = ccTypeIDFormat(columnList);

                //value used to assign 1 auth code for searching purposes
                String dataCapAuthString = "";

                //Array list to hold the results of the matching data based on auth code
                ArrayList<String> results = new ArrayList<String>();

                //outer loop to loop the datacap auth codes
                for (int j = 1; j < columnList[0].length; j++) {
                    dataCapAuthString = columnList[0][j];
                    //inner loop to loop the membership data auth codes
                    for (int i = 0; i < columnList[4].length; i++) {
                        //if the datacap and membership auth codes matches, add it to the results list
                        if (columnList[4][i].equals(dataCapAuthString)) {
                            results.add(columnList[0][j]); //DataCap Auth
                            results.add(columnList[1][j]); //DataCap Token
                            results.add(columnList[8][i]); //first_name
                            results.add(columnList[9][i]); //last_name
                            results.add(columnList[15][i]); //email
                            results.add(columnList[14][i]); //phone
                            results.add(columnList[19][i]);//created_date
                            results.add(columnList[18][i]); //next_bill_date
                            results.add(columnList[16][i]); //card_numbers
                            results.add(columnList[5][i]); //name
                            results.add(columnList[19][i]);//amount
                            results.add(columnList[10][i]); //addr1
                            results.add(columnList[11][i]); //city
                            results.add(columnList[12][i]); //state
                            results.add(columnList[13][i]); //zip_code
                            //results.add(columnList[19][i]);//license_plate
                            results.add(columnList[20][i]);//license_plate
                            results.add(columnList[1][j]); //token1
                            results.add(columnList[19][i]);//token2
                            //results.add(columnList[19][i]);//credit_card_type_id
                            results.add(ccTypeId[i]);//credit_card_type_id
                            results.add(columnList[7][i]); //cc_exp_date
                            results.add(columnList[6][i]); //cc_masked_number
                            results.add(columnList[17][i]); //last_bill_date
                            results.add(columnList[19][i]);//original_amount
                            results.add(columnList[19][i]);//washbook_recurring_status_id
                            results.add(columnList[19][i]);//payment_authorizer_type_id
                            results.add(columnList[19][i]);//balance
                            results.add(columnList[19][i]);//trial_period
                            results.add(columnList[19][i]);//trial_price
                            results.add(columnList[19][i]);//original_trial_price
                            results.add(columnList[19][i]);//trial_count
                            results.add(columnList[19][i]);//site_id
                        }//end if
                    }//end inner
                }//end outer

                //creates the output file to the output path specified
                try {
                    createOutputFile(results, outputPath);
                    resultLabel.setText(outputPath);
                    resultLabel.setVisible(true);
                } catch (IOException ex) {
                    resultLabel.setText("Invalid file or MergeOutput is open in Excel");
                    resultLabel.setVisible(true);
                } catch (RuntimeException rt) {
                    resultLabel.setText("Unable To Parse File 102");
                    resultLabel.setVisible(true);
                }
            }


        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Token Formatter");
        frame.setContentPane(new MergeFormatterGUI().mainPanel);
        frame.setSize(720, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    //parses the source .csv file into a content array
    private void parseFile(List<String[]> content, String file) throws IOException {
        //reads the contents from the file provided and add them to the content array list by splitting at commas
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(",", -1)); //using -1 to accommodate for empty cells in csv file
            }
            String newWord = "[null]";
            for (int i = 0; i < content.size(); i++) {
                for (int j = 0; j < content.get(i).length; j++) {
                    if (content.get(i)[j].equals("") || content.get(10)[10] == null) {
                        content.get(i)[j] = newWord;
                    }
                }
            }
            //System.out.println("File successfully parsed");
            //System.out.println(Arrays.deepToString(content.toArray()));

        } catch (FileNotFoundException e) {
            resultLabel.setText("Unable To Parse File 103");
            resultLabel.setVisible(true);
            //System.out.println("Invalid Path");
        }
    }

    //creates arrays using columns
    private String[] createColumnArray(List<String[]> content, int columnPosition) {
        String[] outputArray = new String[content.size()];
        try {
            for (int i = 0; i <= content.size() - 1; i++) {
                String cont = content.get(i)[columnPosition];
                outputArray[i] = cont;
            }

        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return outputArray;

    }

    //formats the date to what we need
    private static String[] formatDate(String[] content, String header) {
        String[] formatDates = new String[content.length];
        for (int i = 0; i < content.length; i++) {
            //renames the first value so this will be the header in the .csv file
            if (i == 0) {
                formatDates[i] = header;
            } else {
                String cont = content[i];
                if (!cont.equals("[null]")) {
                    cont = cont.replace("\"", "");
                    String result = swap(cont, 0, cont.length() - 7);
                    result = result.replace('.', '/');
                    formatDates[i] = result;
                } else {
                    break;
                }
            }
        }
        return formatDates;
    }

    private static String[] formatDateReplaceSlash(String[] content, String header) {
        String[] formatDates = new String[content.length];
        for (int i = 0; i < content.length; i++) {
            //renames the first value so this will be the header in the .csv file
            if (i == 0) {
                formatDates[i] = header;
            } else {
                String cont = content[i];
                if (!cont.equals("[null]")) {
                    cont = cont.replace("\"", "");
                    String result = cont;
                    result = result.replace('.', '/');
                    formatDates[i] = result;
                } else {
                    break;
                }
            }
        }
        return formatDates;
    }

    //swaps two characters based on position
    private static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        //first digit swap
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(i + 1, str.charAt(j + 1));

        //second digit swap
        sb.setCharAt(j, str.charAt(i));
        sb.setCharAt(j + 1, str.charAt(i + 1));
        return sb.toString();
    }

    //creates all the arrays with values from csv based on column position
    private String[][] columns(List<String[]> content) {
        String[] dataCapAuth = createColumnArray(content, 0);
        String[] dataCapToken = createColumnArray(content, 1);
        String[] dataCapCCExp = createColumnArray(content, 2);
        String[] dataCapCC = createColumnArray(content, 3);
        String[] membersAuth = createColumnArray(content, 4);
        String[] membersPlan = createColumnArray(content, 5);
        String[] membersCC = createColumnArray(content, 6);
        String[] membersCCExp = createColumnArray(content, 7);
        String[] membersFirstName = createColumnArray(content, 8);
        String[] membersLastName = createColumnArray(content, 9);
        String[] membersAddress = createColumnArray(content, 10);
        String[] membersCity = createColumnArray(content, 11);
        String[] membersState = createColumnArray(content, 12);
        String[] membersZip = createColumnArray(content, 13);
        String[] membersPhone = createColumnArray(content, 14);
        String[] membersEmail = createColumnArray(content, 15);
        String[] membersRFID = createColumnArray(content, 16);
        String[] lastBillDateColumn = createColumnArray(content, 17);
        String[] nextBillDateColumn = createColumnArray(content, 19);
        String[] blankColumn = new String[content.size()];
        String[] licensePlateColumn = createColumnArray(content, 21);


        for (int i = 0; i < content.size(); i++) {
            blankColumn[i] = "";
        }

        //format the dates
        if (dayRadioButton.isSelected()) {
            String[] formattedLBDDate = formatDate(lastBillDateColumn, "last_Bill_Date");
            String[] formattedNBDDate = formatDate(nextBillDateColumn, "next_Bill_Date");
            return new String[][]{dataCapAuth, dataCapToken, dataCapCCExp, dataCapCC, membersAuth, membersPlan, membersCC, membersCCExp, membersFirstName, membersLastName, membersAddress, membersCity, membersState, membersZip, membersPhone, membersEmail, membersRFID, formattedLBDDate, formattedNBDDate, blankColumn, licensePlateColumn};
        } else {
            String[] replaceSlashLBD = formatDateReplaceSlash(lastBillDateColumn, "last_Bill_Date");
            String[] replaceSlashNBD = formatDateReplaceSlash(nextBillDateColumn, "next_Bill_Date");
            return new String[][]{dataCapAuth, dataCapToken, dataCapCCExp, dataCapCC, membersAuth, membersPlan, membersCC, membersCCExp, membersFirstName, membersLastName, membersAddress, membersCity, membersState, membersZip, membersPhone, membersEmail, membersRFID, replaceSlashLBD, replaceSlashNBD, blankColumn, licensePlateColumn};
        }

    }

    //method used to format cc type required
    public static String[] ccTypeIDFormat(String[][] columnList) {
        String[] ccExpDate = new String[columnList[6].length];
        for (int i = 0; i < columnList[6].length; i++) {
            if (columnList[6][i].charAt(0) == '2') {
                ccExpDate[i] = String.valueOf(2);
            } else if (columnList[6][i].charAt(0) == '3') {
                ccExpDate[i] = String.valueOf(4);
            } else if (columnList[6][i].charAt(0) == '4') {
                ccExpDate[i] = String.valueOf(1);
            } else if (columnList[6][i].charAt(0) == '5') {
                ccExpDate[i] = String.valueOf(2);
            } else if (columnList[6][i].charAt(0) == '6') {
                ccExpDate[i] = String.valueOf(3);
            } else {
                ccExpDate[i] = String.valueOf(5);
            }

        }
        return ccExpDate;
    }

    //creates an output .csv file to specified path
    private static void createOutputFile(ArrayList<String> results, String outputPath) throws IOException {
        FileWriter writer = new FileWriter(outputPath);
        for (int i = 0; i < results.size(); i++) {
            //header labels
            if (i == 0) {
                writer.append("DataCap Auth" + "," + "DataCap Token" + "," + "first_name" + "," + "last_name" + "," + "email" + "," + "phone" + "," + "created_date" + "," + "next_bill_date" + "," + "card_numbers" + "," + "name" + "," + "amount" + "," + "addr1" + "," + "city" + "," + "state" + "," + "zip_code" + "," + "license_plates" + "," + "token1" + "," + "token2" + "," + "credit_card_type_id" + "," + "cc_exp_date" + "," + "cc_masked_number" + "," + "last_bill_date" + "," +
                        "original_amount" + "," + "washbook_recurring_status_id" + "," + "payment_authorizer_type_id" + "," + "balance" + "," + "trial_period" + "," + "trial_price" + "," + "original_trial_price" + "," + "trial_count" + "," + "site_id");
            }
            //adds new lines after all comlumn values are printed
            if (i % 31 == 0) {
                writer.append("\n");
            }
            writer.append(results.get(i));
            writer.append(",");
        }
        writer.close();
    }


    public static void paintButtonDefaultSize(JButton button, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(150, 30, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        button.setIcon(newIcon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setForeground(Color.WHITE);
    }

    public static void hoverEffectButtonDefaultSize(JButton button, String hoverEnterPath, String hoverExitPath) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                paintButtonDefaultSize(button, hoverEnterPath);
            }

            public void mouseExited(MouseEvent evt) {
                paintButtonDefaultSize(button, hoverExitPath);
            }
        });
    }

    public static HashMap<String, String> getButtonStyleHashMap() {
        HashMap<String, String> buttonStyle = new HashMap<>();
        buttonStyle.put("Blue", "src/resources/buttonImages/blueButton.png");
        buttonStyle.put("DarkBlue", "src/resources/buttonImages/darkBlueButton.png");
        buttonStyle.put("Green", "src/resources/buttonImages/greenButton.png");
        buttonStyle.put("DarkGreen", "src/resources/buttonImages/darkGreenButton.png");
        return buttonStyle;
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        basePanel = new JPanel();
        basePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 4, new Insets(2, 10, 10, 10), -1, -1));
        mainPanel.add(basePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(500, 300), null, 0, false));
        TitleLabel = new JLabel();
        Font TitleLabelFont = this.$$$getFont$$$("Courier New", Font.PLAIN, 28, TitleLabel.getFont());
        if (TitleLabelFont != null) TitleLabel.setFont(TitleLabelFont);
        TitleLabel.setText("Token Formatter");
        basePanel.add(TitleLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(255, 66), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        basePanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 6, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Calibri", Font.PLAIN, 18, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Choose .csv File");
        basePanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        basePanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        browseButton = new JButton();
        browseButton.setText("Browse");
        basePanel.add(browseButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(81, 28), null, 0, false));
        generateButton = new JButton();
        generateButton.setText("Generate");
        basePanel.add(generateButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(90, 30), null, 0, false));
        selectedLabel = new JLabel();
        Font selectedLabelFont = this.$$$getFont$$$("Calibri", Font.PLAIN, 16, selectedLabel.getFont());
        if (selectedLabelFont != null) selectedLabel.setFont(selectedLabelFont);
        selectedLabel.setText("File Selected");
        basePanel.add(selectedLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resultLabel = new JLabel();
        Font resultLabelFont = this.$$$getFont$$$("Calibri", Font.PLAIN, 16, resultLabel.getFont());
        if (resultLabelFont != null) resultLabel.setFont(resultLabelFont);
        resultLabel.setHorizontalTextPosition(11);
        resultLabel.setText("Label");
        basePanel.add(resultLabel, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dayRadioButton = new JRadioButton();
        dayRadioButton.setFocusable(false);
        dayRadioButton.setText("DD-MM-YYYY");
        basePanel.add(dayRadioButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monthRadioButton = new JRadioButton();
        monthRadioButton.setFocusable(false);
        monthRadioButton.setText("MM-DD-YYYY");
        basePanel.add(monthRadioButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Source file date format:");
        basePanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}