package com.bassit.bookstore;

import com.bassit.bookstore.Models.Transactions;
import com.bassit.bookstore.Services.BooksService;
import com.bassit.bookstore.Services.CustomersService;
import com.bassit.bookstore.Services.MembersService;
import com.bassit.bookstore.Services.TransactionsService;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static com.bassit.bookstore.Services.HelperFunctions.header;

@SpringBootApplication
@Log
public class BookStoreApplication implements CommandLineRunner {

    private final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        log.info("Application Started Successfully");
//        BooksService booksService = new BooksService();
//        header("\t\t\t\t  Main Menu");
//
//        int stop = 0;
//        while(stop == 0){
//            mainMenu();
//            int selection = keyboard.nextInt();
//            if(selection == 1){
//                booksService.displayBooksOptions();
//                stop = 1;
//            } else if (selection == 2) {
//                System.out.println("Option 2 Selected");
//                stop = 1;
//            } else if (selection == 3) {
//                System.out.println("Option 3 Selected");
//                stop = 1;
//            } else if (selection == 4) {
//                System.out.println("Option 4 Selected");
//                stop = 1;
//            }else{
//                System.out.println("\n*** Invalid Input Please Try Again ***\n");
//                header("\t\t\t\t  Main Menu");
//            }
//        }

        TransactionsService transactionsService = new TransactionsService();
        transactionsService.getTransactionUsingDateRange_User();


    }


    public void mainMenu(){
        System.out.println("Select an Option to Continue\n1. List Books \n2. Manage Customer Information \n3. Manage Membership Information \n4. Manage Transactions");
        System.out.print("Input corresponding number to your choice: ");
    }




}
