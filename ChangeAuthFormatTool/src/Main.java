import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\Bassit\\IdeaProjects\\ChangeAuthFormatTool\\src\\testData.csv";
        String file = path.replace("\\", "\\\\");

        String outputPath = System.getProperty("user.home") + File.separator + "Downloads\\ChangeAuth.csv";

        //create array to hold parse values then call parseFile method to parse and add to arrayList
        List<String[]> content = new ArrayList<>();
        parseFile(content, file);
        System.out.println(Arrays.toString(content.getFirst()));

//        String[] firstName = createColumnArray(content, 0);
//        String[] lastName = createColumnArray(content, 1);
//        String[] emailAddress = createColumnArray(content, 2);
//        String[] phone = createColumnArray(content, 3);
//        String[] signUpDate = createColumnArray(content, 4);
//        String[] nbd = createColumnArray(content, 5);
//        String[] rfid = createColumnArray(content, 6);
//        String[] plan = createColumnArray(content, 7);
//        String[] billingAmount = createColumnArray(content, 8);
//        String[] address = createColumnArray(content, 9);
//        String[] city = createColumnArray(content, 10);
//        String[] state = createColumnArray(content, 11);
//        String[] zip = createColumnArray(content, 12);
//        String[] licensePlate = createColumnArray(content, 13);
          //String[] row1 = createRowArray(content, 0);

        ArrayList<String> results = new ArrayList<String>();


        System.out.println(Arrays.toString(content.get(1)));
        System.out.println(Arrays.toString(content.get(2)));

        if(content.get(1)[1].equals(content.get(2)[1])){
            if(content.get(1)[6].equals(content.get(2)[6])){
                System.out.println("Matching tag found");
            }else{
                //System.out.println("No matching tag found, incrementing first name to: " + content.get(2)[1].replace(content.get(2)[1], content.get(2)[1].concat(" 1")));
                String[] row = new String[content.getFirst().length];
                for(int i=0; i<=content.getFirst().length-1; i++){
                    String rowData = content.get(1)[i];
                    row[i]= rowData;
                }
                //results.add(Arrays.toString(content.get(1)));
                replaceValue(row, 1, content.get(2)[1].concat(" 1"));
                System.out.println("New Row: " + Arrays.toString(row));

            }

        }


        //Array list to hold the results of the matching data based on auth code






        //createOutputFile(results, outputPath);







//        String dateRaw = "2023-12-1200:00:00";

//        int year = Integer.parseInt(dateRaw.substring(0, 4));
//        int month = Integer.parseInt(dateRaw.substring(5, 7));
//        int day = Integer.parseInt(dateRaw.substring(8, 10));

//        System.out.println(dateRaw.replace("00:00:00", ""));
//
//        System.out.println(year + 2);

//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 1988);
//        cal.set(Calendar.MONTH, Calendar.JANUARY);
//        cal.set(Calendar.DAY_OF_MONTH, 21);
//        Date dateRepresentation = cal.getTime();
//        System.out.println(dateRepresentation);
    }

    private static void replaceValue(String[] array, int index, String newValue){
        for(int i=0; i<= array.length; i++){
            if(i == index){
                array[i] = newValue;
            }
        }
    }


    //parses the source .csv file into a content array
    private static void parseFile(List<String[]> content, String file) throws IOException {
        //reads the contents from the file provided and add them to the content array list by splitting at commas
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
            System.out.println("File successfully parsed");

        } catch (FileNotFoundException e) {
            System.out.println("Invalid Path");
        }
    }

    //creates arrays using columns
    private static String[] createRowArray(List<String[]> content, int rowPosition) {
        String[] outputArray = new String[content.size()];
        for (int i = 0; i <= content.size() - 1; i++) {
            String cont = content.get(rowPosition)[i];
            outputArray[i] = cont;
        }
        return outputArray;
    }

    //creates an output .csv file to specified path
    private static void createOutputFile(ArrayList<String> results, String outputPath) throws IOException {
        FileWriter writer = new FileWriter(outputPath);
        for (int i = 0; i < results.size(); i++) {
            //header labels
            if (i == 0) {
                writer.append("FirstName" + "," + "lastName" + "," + "Home Phone" + "," + "Sign Up Date" + "," + "NextBillDate" + "," + "Tag ID" + "," + "Wash Package" + "," + "Address1" + "," + "City" + "," + "StateAbbr" + "," +
                        "PostalCode" + "," +"License Plate");
            }
            //adds new lines after all comlumn values are printed
            if (i % 12 == 0) {
                writer.append("\n");
            }
            writer.append(results.get(i));
            writer.append(",");
        }
        writer.close();
    }
}
