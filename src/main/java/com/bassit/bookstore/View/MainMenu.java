package com.bassit.bookstore.View;

import com.bassit.bookstore.Services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.bassit.bookstore.Services.HelperFunctions.header;


public class MainMenu {

    private static final Scanner keyboard = new Scanner(System.in);
    private static final BookMainMenu bookMainMenu = new BookMainMenu();



    public static void menu(){
        header("\t\t\t\t  Main Menu");
        System.out.println("Select an Option to Continue\n1. List Books \n2. Manage Customer Information \n3. Manage Membership Information \n4. Manage Transactions");
        System.out.print("Input corresponding number to your choice: ");
        int response_mainMenu = keyboard.nextInt();
        if(response_mainMenu == 1){
            bookMainMenu.displayOptions();
        }


    }
}
