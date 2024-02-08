package com.bassit.bookstore.View;

import com.bassit.bookstore.Services.MembersService;
import java.util.Scanner;
import static com.bassit.bookstore.Services.HelperFunctions.header;

public class MembersMainMenu {

    private final Scanner keyboard = new Scanner(System.in);
    private final MembersService membersService = new MembersService();

    public void displayOptions(){
        int userResponse;
        do {
            header("Manage Membership Information");
            System.out.println("1. Create Membership\n2. Find Member By Username\n3. Update Member Firstname\n4. Update Member Lastname\n5. Update Member Phone Number\n6. Update Member Email \n7. Update Member Username\n8. Update Member Password\n9. Update Member Plan\n10. Update Member Status\n11. Update Member Price\n12. Update Member Expiration\n13. Main Menu\n14. Quit");
            userResponse = keyboard.nextInt();

            switch (userResponse){
                case 1:
                    membersService.createMember_User();
                    break;
                case 2:
                    membersService.getMemberByUsername_User();
                    break;
                case 3:
                    membersService.updateMemberFirstName_User();
                    break;
                case 4:
                    membersService.updateMemberLastName_User();
                    break;
                case 5:
                    membersService.updateMemberPhoneNumber_User();
                    break;
                case 6:
                    membersService.updateMemberEmail_User();
                    break;
                case 7:
                    membersService.updateMemberUsername_User();
                    break;
                case 8:
                    membersService.updateMemberPassword_User();
                    break;
                case 9:
                    membersService.updateMemberPlan_User();
                    break;
                case 10:
                    membersService.updateMemberStatus_User();
                    break;
                case 11:
                    membersService.updateMemberPrice_User();
                    break;
                case 12:
                    membersService.updateMemberExp_User();
                    break;
                case 13:
                    MainMenu.menu();
                    break;
            }
        }while (userResponse != 14);
        System.exit(0);
    }
}
