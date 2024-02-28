package com.bassit.bookstore.View;

import com.bassit.bookstore.Services.CustomersService;
import java.util.Scanner;
import static com.bassit.bookstore.Services.HelperFunctions.header;

public class CustomerMainMenu {

    private final Scanner keyboard = new Scanner(System.in);
    private final CustomersService customersService = new CustomersService();

    public void displayOptions(){
        int userResponse;
        do {
            header("Manage Customer Information");
            System.out.println("1. Create Customer\n2. Find Customer By First and Last Name\n3. Find Customer By Email\n4. Find Customer By Phone Number\n5. Update Customer First Name\n6. Update Customer Last Name\n7. Update Customer Email\n8. Update Customer Phone Number\n9. Main Menu\n10. Quit");
            userResponse = keyboard.nextInt();

            switch (userResponse){
                case 1:
                    //customersService.createdCustomer_User();
                    break;
                case 2:
                    //customersService.findCustomerByFirstAndLastName_User();
                    break;
                case 3:
                    //customersService.findCustomerByEmail_User();
                    break;
                case 4:
                    //customersService.findCustomerByPhoneNumber_User();
                    break;
                case 5:
                    //customersService.updateCustomerFirstName_User();
                    break;
                case 6:
                    //customersService.updateCustomerLastName_User();
                    break;
                case 7:
                    //customersService.updateCustomerEmail_User();
                    break;
                case 8:
                    //customersService.updateCustomerPhoneNumber_User();
                    break;
                case 9:
                    MainMenu.menu();
                    break;
            }
        }while (userResponse != 10);
        System.exit(0);
    }
}
