package com.bassit.bookstore.View;

import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.bassit.bookstore.Services.HelperFunctions.header;

@Component
public class BooksMainMenu {

    private final Scanner keyboard = new Scanner(System.in);

    public void displayBooksOptions(){
        //this scanner will read with spaces from input
        Scanner charScanner = new Scanner( System.in ).useDelimiter( "(\\b|\\B)" ) ;
        int response;
        int stop = 0;

        while(stop == 0){
            header("List Of Book Menu");
            System.out.println("Please Select An Option Below: \n1.  List All Books\n2.  List Books By Newest \n3.  List Books By Oldest \n4.  Sort Books By Authors \n5.  Sort Books By ISBN \n6.  Sort Books By Title \n7.  Sort Books By Price (Low to High) \n8.  Sort Books By Price (High to Low) \n9.  Search For Books By Year \n10. Search For Books By Author \n11. Search For Books By Title \n12. Search For Books By ISBN \n");
            System.out.print("Input corresponding number to your choice: ");
            response = keyboard.nextInt();

        }
    }
}
