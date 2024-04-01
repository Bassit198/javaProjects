
//"C:\Users\Bassit\IdeaProjects\MergeFormatter\src\testFileForMerge.xlsx"

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MergeFormatter {

    public static void main(String[] args) throws IOException {



        String input = "C:\\Users\\Bassit\\IdeaProjects\\MergeFormatter\\src\\testFileForMerge.csv";
        String outputPath = System.getProperty("user.home") + File.separator + "Downloads\\MergeOutput.csv";

        //create array to hold parse values then call parseFile method to parse and add to arrayList
        List<String[]> content = new ArrayList<>();
        parseFile(content, input);

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
                    results.add(columnList[0][j]);
                    results.add(columnList[1][j]);
                    results.add(columnList[8][i]);
                    results.add(columnList[9][i]);
                    results.add(columnList[15][i]);
                    results.add(columnList[14][i]);
                    results.add(columnList[18][i]);
                    results.add(columnList[16][i]);
                    results.add(columnList[5][i]);
                    results.add(columnList[10][i]);
                    results.add(columnList[11][i]);
                    results.add(columnList[12][i]);
                    results.add(columnList[13][i]);
                    results.add(columnList[1][i]);
                    results.add(columnList[7][i]);
                    results.add(columnList[6][i]);
                    results.add(columnList[17][i]);
                }//end if
            }//end inner
        }//end outer

        //creates the output file to the path specified
        createOutputFile(results, outputPath);

    }//end main


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

        //format the dates
        String[] formattedLBDDate = formatDate(lastBillDateColumn, "last_Bill_Date");
        String[] formattedNBDDate = formatDate(nextBillDateColumn, "next_Bill_Date");

        return new String[][]{dataCapAuth, dataCapToken, dataCapCCExp, dataCapCC, membersAuth, membersPlan, membersCC, membersCCExp, membersFirstName, membersLastName, membersAddress, membersCity, membersState, membersZip, membersPhone, membersEmail, membersRFID, formattedLBDDate, formattedNBDDate};
    }

    //creates an output .csv file to specified path
    private static void createOutputFile(ArrayList<String> results, String outputPath) throws IOException {
        FileWriter writer = new FileWriter(outputPath);
        for (int i = 0; i < results.size(); i++) {
            //header labels
            if (i == 0) {
                writer.append("DataCap Auth" + "," + "DataCap Token" + "," + "first_name" + "," + "last_name" + "," + "email" + "," + "phone" + "," + "next_bill_date" + "," + "card_numbers" + "," + "name" + "," + "addr1" + "," + "city" + "," + "state" + "," + "zip_code" + "," + "token1" + "," + "cc_exp_date" + "," + "cc_masked_number" + "," + "last_bill_date");
            }
            //adds new lines after all comlumn values are printed
            if (i % 17 == 0) {
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }
}
