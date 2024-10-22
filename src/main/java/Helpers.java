import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Helpers {
    public String printHelperHeading() {
        return "Helper Heading";
    }

    public String csvPath(String filename) {
        String documentsPath = System.getProperty("user.home") + "/Downloads";
        Path documentsDir = Paths.get(documentsPath, new String[0]);
        return String.valueOf(documentsDir.resolve(filename));
    }

    public void parseFile(List<String[]> csvContents, String file) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String line = "";
                while ((line = br.readLine()) != null)
                    csvContents.add(line.split(",", -1));
                String newWord = "[placeHolder]";
                int columnsTotal = 0;
                for (String[] strings : csvContents) {
                    for (int j = 0; j < strings.length; j++) {
                        if (strings[j].isEmpty() || strings[j] == null)
                            strings[j] = newWord;
                    }
                    columnsTotal = strings.length;
                }
                if (columnsTotal > 23) {
                    System.out.println("Unable to parse file.");
                    System.out.println("Required number of columns is 23. Provided columns is " + columnsTotal + "\n------------------------------------------------------------");
                    System.exit(103);
                } else {
                    System.out.println("Number of Columns: " + columnsTotal);
                    System.out.println("File successfully parsed.\n------------------------------------------------------------");
                }
                br.close();
            } catch (Throwable throwable) {
                try {
                    br.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to Parse File.");
            System.out.println("File not found.\n------------------------------------------------------------");
            System.exit(103);
        }
    }

    public String[] createColumnArray(List<String[]> csvContents, int columnPosition) {
        String[] outputArray = new String[csvContents.size()];
        try {
            for (int i = 0; i <= csvContents.size() - 1; i++) {
                String cont = ((String[])csvContents.get(i))[columnPosition];
                outputArray[i] = cont;
            }
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {}
        return outputArray;
    }

    public String swapDate(String str) {
        StringBuilder sb = new StringBuilder("xx/xx/");
        if (str.length() == 9) {
            int firstNumber = Integer.parseInt(str.substring(0, 2));
            sb.setCharAt(0, '0');
            sb.setCharAt(1, str.charAt(3));
            sb.setCharAt(3, str.charAt(0));
            sb.setCharAt(4, str.charAt(1));
            sb.append(str.substring(5));
        } else if (str.length() == 10) {
            sb.setCharAt(0, str.charAt(3));
            sb.setCharAt(1, str.charAt(4));
            sb.setCharAt(3, str.charAt(0));
            sb.setCharAt(4, str.charAt(1));
            sb.append(str.substring(6));
        } else {
            return "InvalidDatePresent";
        }
        return sb.toString();
    }

    public String[] formatDates(String[] nextBillDate, String header) {
        String[] formattedNBD = new String[nextBillDate.length];
        for (int i = 0; i < nextBillDate.length; i++) {
            formattedNBD[0] = header;
            formattedNBD[i] = swapDate(nextBillDate[i]);
        }
        return formattedNBD;
    }

    public String[] checkDates(String[] dates, String header) {
        String[] finalDates = new String[dates.length];
        for (int i = 0; i < dates.length; i++) {
            finalDates[0] = header;
            if (dates[i].contains(".")) {
                finalDates[i] = dates[i].replace('.', '/');
            } else if (dates[i].contains("-")) {
                finalDates[i] = dates[i].replace('-', '/');
            } else if(dates[i].equals("[placeHolder]")){
                finalDates[i] = "NoDateProvided";
            } else {
                finalDates[i] = dates[i];
            }
        }
        return finalDates;
    }

    public String[] formatPhoneNumbers(String[] phoneNumbers, String header) {
        String[] finalNumbers = new String[phoneNumbers.length];
        for (int i = 0; i < phoneNumbers.length; i++) {
            finalNumbers[0] = header;
            if (i > 0)
                finalNumbers[i] = phoneNumbers[i].replaceAll("[()\\s-]+", "");
        }
        return finalNumbers;
    }

    public String[] createBlankColumn(int size) {
        String[] blankArray = new String[size];
        for (int i = 0; i < size; i++)
            blankArray[i] = "";
        return blankArray;
    }

    public String[] formatCCType(String[] ccMaskedNumber, String header) {
        String[] ccType = new String[ccMaskedNumber.length];
        for (int i = 0; i < ccMaskedNumber.length; i++) {
            ccType[0] = header;
            if (ccMaskedNumber[i].charAt(0) == 'X') {
                ccType[i] = "InvalidNumber --> X";
            } else if (ccMaskedNumber[i].charAt(0) == '2') {
                ccType[i] = String.valueOf(2);
            } else if (ccMaskedNumber[i].charAt(0) == '3') {
                ccType[i] = String.valueOf(4);
            } else if (ccMaskedNumber[i].charAt(0) == '4') {
                ccType[i] = String.valueOf(1);
            } else if (ccMaskedNumber[i].charAt(0) == '5') {
                ccType[i] = String.valueOf(2);
            } else if (ccMaskedNumber[i].charAt(0) == '6') {
                ccType[i] = String.valueOf(3);
            } else {
                ccType[i] = "InvalidValue";
            }
        }
        return ccType;
    }

    public String[] formatCCExp(String[] ccExp) {
        String[] ccExpFormat = new String[ccExp.length];
        StringBuilder sb = new StringBuilder("xx/xx/xxxx");
        for (int i = 0; i < ccExp.length; i++) {
            if (i == 0) {
                ccExpFormat[0] = "cc_exp_date";
            } else if (ccExp[i].length() == 3) {
                sb.setCharAt(0, '0');
                sb.setCharAt(1, ccExp[i].charAt(0));
                sb.setCharAt(3, '0');
                sb.setCharAt(4, '1');
                sb.setCharAt(6, '2');
                sb.setCharAt(7, '0');
                sb.setCharAt(8, ccExp[i].charAt(1));
                sb.setCharAt(9, ccExp[i].charAt(2));
                ccExpFormat[i] = sb.toString();
            } else if (ccExp[i].length() == 4) {
                sb.setCharAt(0, ccExp[i].charAt(0));
                sb.setCharAt(1, ccExp[i].charAt(1));
                sb.setCharAt(3, '0');
                sb.setCharAt(4, '1');
                sb.setCharAt(6, '2');
                sb.setCharAt(7, '0');
                sb.setCharAt(8, ccExp[i].charAt(2));
                sb.setCharAt(9, ccExp[i].charAt(3));
                ccExpFormat[i] = sb.toString();
            } else if(ccExp[i].equals("[placeHolder]")){
                ccExpFormat[i] = "NoExpProvided";
            } else {
                ccExpFormat[i] = "InvalidCCExp";
            }
        }
        return ccExpFormat;
    }

    public String[] checkccNumber(String[] ccNumbers) {
        String[] results = new String[ccNumbers.length];
        for(int i=0; i<ccNumbers.length; i++) {
            results[0] = "cc_masked_number";

            String number = ccNumbers[i];
            if(number.equals("[placeHolder]") || number.equals(null) || number.isEmpty()) {
                results[i] = "NoNumberProvided";
            }else if(!number.contains("XXXXXX")){
                results[i] = "InvalidNumber";
            }else {
                results[i] = number;
            }
        }

        return results;
    }

    public void createOutputFile(ArrayList<String> results, String outputPath) throws IOException {
        System.out.println("Creating output file...\n------------------------------------------------------------");
        try {
            FileWriter writer = new FileWriter(outputPath);
            try {
                for (int i = 0; i < results.size(); i++) {
                    if (i == 0)
                        writer.append("DataCap Auth,DataCap Token,first_name,last_name,email,phone,created_date,next_bill_date, old_nbd, card_numbers,name,OriginalName,Original Price,amount,addr1,city,state,zip_code,license_plates,token1,token2,credit_card_type_id,cc_exp_date,cc_masked_number,last_bill_date,original_amount,washbook_recurring_status_id,payment_authorizer_type_id,balance,trial_period,trial_price,original_trial_price,trial_count,site_id");
                    if (i % 34 == 0)
                        writer.append("\n");
                    writer.append(results.get(i));
                    writer.append(",");
                }
                System.out.println("Output file successfully created at: " + outputPath + "\n------------------------------------------------------------");
                writer.close();
            } catch (Throwable throwable) {
                try {
                    writer.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (FileNotFoundException fn) {
            System.out.println("Output file is open. Please close and retry.\n------------------------------------------------------------");
        }
    }
}
