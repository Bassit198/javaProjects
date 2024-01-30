package com.bassit.bookstore.View;

import com.bassit.bookstore.Services.MembersService;
import com.bassit.bookstore.Services.TransactionsService;

import java.util.Scanner;

import static com.bassit.bookstore.Services.HelperFunctions.header;

public class TransactionMainMenu {

    private final Scanner keyboard = new Scanner(System.in);
    private final TransactionsService transactionsService = new TransactionsService();

    public void displayOptions(){
        int userResponse;
        do {
            header("Manage Transactions");
            System.out.println("1. Create Transaction\n2. Find Transaction by Transaction Number\n3. Find Transaction in Date Range\n4. Refund Transaction\n5. Cancel Transaction\n6. Main Menu\n7. Quit");
            userResponse = keyboard.nextInt();

            switch (userResponse){
                case 1:
                    transactionsService.createTransaction_User();
                    break;
                case 2:
                    transactionsService.getTransactionUsingTransactionNumber_User();
                    break;
                case 3:
                    transactionsService.getTransactionUsingDateRange_User();
                    break;
                case 4:
                    transactionsService.refundTransaction_User();
                    break;
                case 5:
                    transactionsService.cancelTransaction_User();
                    break;
                case 6:
                    MainMenu.menu();
                    break;
            }

        }while (userResponse != 7);
        System.exit(0);
    }
}
