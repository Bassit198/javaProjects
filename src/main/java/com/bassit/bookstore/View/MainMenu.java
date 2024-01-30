package com.bassit.bookstore.View;

import java.util.Scanner;
import static com.bassit.bookstore.Services.HelperFunctions.header;


public class MainMenu {

    private static final Scanner keyboard = new Scanner(System.in);
    private static final BookMainMenu bookMainMenu = new BookMainMenu();
    private static final CustomerMainMenu customerMainMenu = new CustomerMainMenu();
    private static final MembersMainMenu membersMainMenu = new MembersMainMenu();
    private static final TransactionMainMenu transactionsMainMenu = new TransactionMainMenu();



    public static void menu(){
        int response_mainMenu;

        do {
            header("\t\t\t\t  Main Menu");
            System.out.println("Select an Option to Continue\n1. List Books \n2. Manage Customer Information \n3. Manage Membership Information \n4. Manage Transactions\n5. Quit");
            System.out.print("Input corresponding number to your choice: ");
            response_mainMenu = keyboard.nextInt();

            switch (response_mainMenu){
                case 1:
                    bookMainMenu.displayOptions();
                    break;
                case 2:
                    customerMainMenu.displayOptions();
                    break;
                case 3:
                    membersMainMenu.displayOptions();
                    break;
                case 4:
                    transactionsMainMenu.displayOptions();
                    break;
            }
        }while (response_mainMenu != 5);
        System.exit(0);
    }
}
