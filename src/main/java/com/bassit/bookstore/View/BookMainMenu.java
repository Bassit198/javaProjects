package com.bassit.bookstore.View;

import com.bassit.bookstore.Services.BooksService;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import static com.bassit.bookstore.Services.HelperFunctions.header;


public class BookMainMenu {

    private final Scanner keyboard = new Scanner(System.in);
    private final BooksService booksService = new BooksService();

    public void displayOptions(){
        int userResponse;
        do {
            header("List Of Books");
            System.out.println("1. List All Books\n2. Search By Year\n3. Search By Title\n4. Search By Author\n5. Search By ISBN\n6. Search Books Less Than Price \n7. Search Books Greater Than Price\n8. Sort Books By Title\n9. Sort Books By Author Name\n10. Sort Books By ISBN\n11. Sort Books By Price (Lowest To Highest)\n12. Sort Books By Price (Highest To Lowest)\n13. Main Menu\n14. Quit");
            userResponse = keyboard.nextInt();

            switch (userResponse){
                case 1:
                    booksService.listAllBooks_User();
                    break;
                case 2:
                    booksService.searchByYear_User();
                    break;
                case 3:
                    booksService.searchByTitle_User();
                    break;
                case 4:
                    booksService.searchByAuthor_User();
                    break;
                case 5:
                    booksService.searchByISBN_User();
                    break;
                case 6:
                    System.out.println("needs to fix");
                    //booksService.searchByPriceLessThan_User();
                    break;
                case 7:
                    System.out.println("needs to fix");
                    //booksService.searchByPriceGreaterThan_User();
                    break;
                case 8:
                    booksService.sortBooksByTitle_User();
                    break;
                case 9:
                    booksService.sortBooksByAuthor_User();
                    break;
                case 10:
                    booksService.sortBooksByISBN_User();
                    break;
                case 11:
                    booksService.sortBooksByPriceLowToHigh_User();
                    break;
                case 12:
                    booksService.sortBooksByPriceHighToLow_User();
                    break;
                case 13:
                    MainMenu.menu();
            }

        }while (userResponse != 14);
        System.exit(0);
    }
}
