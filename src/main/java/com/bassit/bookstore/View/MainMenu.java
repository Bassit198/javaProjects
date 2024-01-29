package com.bassit.bookstore.View;

import com.bassit.bookstore.Services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;


public class MainMenu {

    private final Scanner keyboard = new Scanner(System.in);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BooksMainMenu booksMainMenu;
    private CustomerMainMenu customerMainMenu;
    private MembersMainMenu membersMainMenu;
    private TransactionMainMenu transactionMainMenu;

    public void menu(){
        System.out.println("Select an Option to Continue\n1. List Books \n2. Manage Customer Information \n3. Manage Membership Information \n4. Manage Transactions");
        System.out.print("Input corresponding number to your choice: ");
        BooksService booksService = new BooksService();
        int response_mainMenu = keyboard.nextInt();


    }
}
