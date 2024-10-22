import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static final Helpers helpers = new Helpers();

    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("------------------------------------------------------------\nToken Formatter\n------------------------------------------------------------");
        String filename = null;
        do {
            System.out.println("Enter your file name (include file type such as .csv): ");
            filename = keyboard.nextLine();
        } while (!(new File(helpers.csvPath(filename))).exists());

        try {
            FileWriter writer = new FileWriter(helpers.csvPath("output.csv"));
        } catch (FileNotFoundException fn) {
            System.out.println("Output file is open. Please close and retry.\n------------------------------------------------------------");
            System.exit(-1);
        }

        System.out.println("------------------------------------------------------------");
        List<String[]> csvContents = (List)new ArrayList<>();
        System.out.println("CSV File Path: " + helpers.csvPath(filename));
        helpers.parseFile(csvContents, helpers.csvPath(filename));
        String[] authCode_datacap = helpers.createColumnArray(csvContents, 0);
        String[] datacap_token_datacap = helpers.createColumnArray(csvContents, 1);
        String[] cc_expiration_datacap = helpers.createColumnArray(csvContents, 2);
        String[] ccNumber_datcap = helpers.createColumnArray(csvContents, 3);
        String[] authCode_members = helpers.createColumnArray(csvContents, 4);
        String[] ccNumber_members = helpers.createColumnArray(csvContents, 5);
        String[] ccExp_members = helpers.createColumnArray(csvContents, 6);
        String[] firstName_members = helpers.createColumnArray(csvContents, 7);
        String[] lastName_members = helpers.createColumnArray(csvContents, 8);
        String[] address1_members = helpers.createColumnArray(csvContents, 9);
        String[] city_members = helpers.createColumnArray(csvContents, 10);
        String[] state_members = helpers.createColumnArray(csvContents, 11);
        String[] zipcode_members = helpers.createColumnArray(csvContents, 12);
        String[] phone_members = helpers.createColumnArray(csvContents, 13);
        String[] email_members = helpers.createColumnArray(csvContents, 14);
        String[] rfid_members = helpers.createColumnArray(csvContents, 15);
        String[] licensePlate_members = helpers.createColumnArray(csvContents, 16);
        String[] lastBillDate_members = helpers.createColumnArray(csvContents, 17);
        String[] nextBillDate_members = helpers.createColumnArray(csvContents, 18);
        String[] status_members = helpers.createColumnArray(csvContents, 19);
        String[] newPlanName_members = helpers.createColumnArray(csvContents, 20);
        String[] originalPlanName_members = helpers.createColumnArray(csvContents, 21);
        String[] originalPlanPrice_members = helpers.createColumnArray(csvContents, 22);
        String[] formattedNBD = new String[nextBillDate_members.length];
        String[] formattedLBD = new String[lastBillDate_members.length];
        String[] ccTypeID = new String[ccNumber_datcap.length];
        String[] formattedPhoneNumbers = new String[phone_members.length];
        System.out.println("Is the first digit present for the maskedCC number?");

        String ccResponse = keyboard.nextLine();
        if (ccResponse.equals("yes") || ccResponse.equals("y") || ccResponse.equals("1")) {
            ccTypeID = helpers.formatCCType(ccNumber_datcap, "credit_card_type_id");
            System.out.println("------------------------------------------------------------\nAssigning CC Type IDs...\n------------------------------------------------------------");
        }else {
            for(int i=0; i<ccTypeID.length; i++) {
                ccTypeID[i] = "5";
            }
        }
        System.out.println("Checking Dates...");
        formattedNBD = helpers.checkDates(nextBillDate_members, "next_bill_date");
        System.out.println("NBD done");
        formattedLBD = helpers.checkDates(lastBillDate_members, "last_bill_date");
        System.out.println("LBD done");
        System.out.println("Dates checked successfully\n------------------------------------------------------------");

        System.out.println("Formatting Phone Numbers...");
        formattedPhoneNumbers = helpers.formatPhoneNumbers(phone_members, "phone");
        System.out.println("Phone numbers formatted successfully\n------------------------------------------------------------");

        System.out.println("Formatting CC Exp Dates...");
        String[] formattedCCExp = helpers.formatCCExp(cc_expiration_datacap);
        System.out.println("CC expiration dates formatted successfully\n------------------------------------------------------------");

        System.out.println("Checking masked CC");
        String[] formattedMaskedCC = helpers.checkccNumber(ccNumber_datcap);
        System.out.println("CC numbers checked successfully\n------------------------------------------------------------");

        System.out.println("Matching auth codes...\n------------------------------------------------------------");

        ArrayList<String> results = new ArrayList<>();
        String dataCapAuthString = "";

        for (int j = 1; j < authCode_datacap.length; j++) {
            dataCapAuthString = authCode_datacap[j];
            for (int i = 0; i < authCode_members.length; i++) {
                if (authCode_members[i].equals(dataCapAuthString)) {
                    results.add(authCode_datacap[j]);
                    results.add(datacap_token_datacap[j]);
                    results.add(firstName_members[i]);
                    results.add(lastName_members[i]);
                    results.add(email_members[i]);
                    results.add(formattedPhoneNumbers[i]);
                    results.add("");
                    results.add("");
                    results.add(formattedNBD[i]);
                    results.add(rfid_members[i]);
                    results.add("");
                    results.add(originalPlanName_members[i]);
                    results.add(originalPlanPrice_members[i]);
                    results.add("");
                    results.add(address1_members[i]);
                    results.add(city_members[i]);
                    results.add(state_members[i]);
                    results.add(zipcode_members[i]);
                    results.add(licensePlate_members[i]);
                    results.add(datacap_token_datacap[j]);
                    results.add("");
                    results.add(ccTypeID[j]);
                    results.add(formattedCCExp[j]);
                    results.add(formattedMaskedCC[j]);
                    results.add(formattedLBD[i]);
                    results.add("");
                    results.add(status_members[i]);
                    results.add("7");
                    results.add("999");
                    results.add("0");
                    results.add("0");
                    results.add("0");
                    results.add("0");
                    results.add("");
                }
            }
        }

        System.out.println("Adding payment authorizer type...\n------------------------------------------------------------");
        System.out.println("Adding balance...\n------------------------------------------------------------");
        System.out.println("Adding trial default data...\n------------------------------------------------------------");

        helpers.createOutputFile(results, helpers.csvPath("output.csv"));

        System.out.println("Press any key and enter to quit");
        keyboard.nextLine();
        System.exit(0);
    }
}
