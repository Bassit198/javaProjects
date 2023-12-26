import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MergeFormatterGUI {
    private JPanel mainPanel;
    private JPanel basePanel;
    private JLabel TitleLabel;
    private JButton generateButton;
    private JTextField pathField;
    private JLabel resultLabel;

    public MergeFormatterGUI() {
        resultLabel.setVisible(false);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String path = pathField.getText();
                if (path.isEmpty()) {
                    resultLabel.setText("Invalid Path");
                    resultLabel.setVisible(true);
                } else {

                    if (path.contains("\"")) {
                        StringBuilder sb = new StringBuilder(path);
                        sb.deleteCharAt(0);
                        sb.deleteCharAt(path.length() - 2);
                        path = String.valueOf(sb);
                    }
                    String file = path.replace("\\", "\\\\");

                    String outputPath = System.getProperty("user.home") + File.separator + "Downloads\\MergeOutput.csv";

                    //create array to hold parse values then call parseFile method to parse and add to arrayList
                    List<String[]> content = new ArrayList<>();
                    try {
                        parseFile(content, file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (RuntimeException rt) {
                        resultLabel.setText("Unable To Parse File");
                        resultLabel.setVisible(true);
                    }

                    //creates a list of all the column in the form of arrays
                    String[][] columnList = columns(content);

                    //value used to assign 1 auth code for searching purposes
                    String dataCapAuthString = "";

                    //Array list to hold the resuts of the matching data based on auth code
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
                                results.add(columnList[19][i]);//license_plate
                                results.add(columnList[1][j]); //token1
                                results.add(columnList[19][i]);//token2
                                results.add(columnList[19][i]);//credit_card_type_id
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

                    //creates the output file to the path specified
                    try {
                        createOutputFile(results, outputPath);
                        resultLabel.setText(outputPath);
                        resultLabel.setVisible(true);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (RuntimeException rt) {
                        resultLabel.setText("Unable To Generate File");
                        resultLabel.setVisible(true);
                    }

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
    private static void parseFile(List<String[]> content, String file) throws IOException {
        //reads the contents from the file provided and add them to the content array list by splitting at commas
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
            //System.out.println("File successfully parsed");

        } catch (FileNotFoundException e) {

            //System.out.println("Invalid Path");
        }
    }


    //creates arrays using columns
    private static String[] createColumnArray(List<String[]> content, int columnPosition) {
        String[] outputArray = new String[content.size()];
        for (int i = 0; i <= content.size() - 1; i++) {
            String cont = content.get(i)[columnPosition];
            outputArray[i] = cont;
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
                cont = cont.replace("\"", "");
                String result = swap(cont, 0, cont.length() - 7);
                result = result.replace('.', '/');
                formatDates[i] = result;
            }
        }
        return formatDates;
    }

    //swaps two characters based on position
    private static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(i + 1, str.charAt(j + 1));

        sb.setCharAt(j, str.charAt(i));
        sb.setCharAt(j + 1, str.charAt(i + 1));
        return sb.toString();
    }

    //creates all the arrays with values from csv based on column position
    private static String[][] columns(List<String[]> content) {
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
        for (int i = 0; i < content.size(); i++) {
            blankColumn[i] = " ";
        }

        //format the dates
        String[] formattedLBDDate = formatDate(lastBillDateColumn, "last_Bill_Date");
        String[] formattedNBDDate = formatDate(nextBillDateColumn, "next_Bill_Date");

        return new String[][]{dataCapAuth, dataCapToken, dataCapCCExp, dataCapCC, membersAuth, membersPlan, membersCC, membersCCExp, membersFirstName, membersLastName, membersAddress, membersCity, membersState, membersZip, membersPhone, membersEmail, membersRFID, formattedLBDDate, formattedNBDDate, blankColumn};
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
        basePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 3, new Insets(2, 10, 10, 10), -1, -1));
        mainPanel.add(basePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(500, 300), null, 0, false));
        TitleLabel = new JLabel();
        Font TitleLabelFont = this.$$$getFont$$$("Courier New", Font.PLAIN, 28, TitleLabel.getFont());
        if (TitleLabelFont != null) TitleLabel.setFont(TitleLabelFont);
        TitleLabel.setText("Token Formatter");
        basePanel.add(TitleLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        basePanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Calibri", Font.PLAIN, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Enter Source File Path:");
        basePanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        generateButton = new JButton();
        generateButton.setText("Generate");
        basePanel.add(generateButton, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pathField = new JTextField();
        basePanel.add(pathField, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        resultLabel = new JLabel();
        Font resultLabelFont = this.$$$getFont$$$("Calibri", Font.PLAIN, 16, resultLabel.getFont());
        if (resultLabelFont != null) resultLabel.setFont(resultLabelFont);
        resultLabel.setHorizontalTextPosition(11);
        resultLabel.setText("Label");
        basePanel.add(resultLabel, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        basePanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        basePanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        basePanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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