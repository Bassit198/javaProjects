package com.bassit.bookstore.Services;

import com.bassit.bookstore.Models.Members;
import lombok.extern.java.Log;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

import static com.bassit.bookstore.Services.HelperFunctions.*;

@Log
public class MembersService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Scanner keyboard = new Scanner(System.in);

    //user end
    //create member
    public void createMember_User(){
        header("Create Membership");
        System.out.print("Enter member first name: ");
        String firstName = keyboard.nextLine();
        System.out.print("Enter member last name: ");
        String lastName = keyboard.nextLine();
        System.out.print("Enter member email: ");
        String email = keyboard.nextLine();
        System.out.print("Enter member phone number: ");
        String phoneNumber = keyboard.nextLine();
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member password: ");
        String password = keyboard.nextLine();
        System.out.print("Enter member plan: ");
        String plan = keyboard.nextLine();
        System.out.println(createMembers_DB(firstName, lastName, email, phoneNumber, username, password, plan));
    }

    //read member by username
    public List<Members> getMemberByUsername_User(){
        header("Find Member By Username");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        return getMemberByUsername_DB(username);
    }

    //update memberFirstName
    //update memberLastName
    //update memberPhoneNumber
    //update memberEmail
    //update username
    //update member password
    //update membershipPlan
    //update membershipStatus
    //update membershipPrice
    //update membershipExpiration

    //delete member


    //db end
    //create member
    private String createMembers_DB(String firstName, String lastName, String email, String phoneNumber, String username, String password, String plan){
        Double price = switch (plan) {
            case "Middle" -> 20.99;
            case "Basic" -> 15.99;
            default -> 29.99;
        };
        final String uri = "http://localhost:8080/addMember";
        Map<String, String> map = new HashMap<>();
        map.put("memberFirstName", firstName);
        map.put("memberLastName", lastName);
        map.put("memberEmail", email);
        map.put("memberPhoneNumber", phoneNumber);
        map.put("username", username);
        map.put("password", password);
        map.put("membershipPlan", plan);
        map.put("membershipStatus", "Active");
        map.put("membershipPrice", String.valueOf(price));
        map.put("membershipExpiration", String.valueOf(LocalDateTime.now().plusMonths(1)));
        map.put("membershipPurchaseDate", String.valueOf(LocalDateTime.now()));
        restTemplate.postForEntity(uri,map, Void.class);
        log.info("Member successfully created from service layer");
        return "Member successfully created.";
    }

    //read member by username
    private List<Members> getMemberByUsername_DB(String username){
        final String uri = "http://localhost:8080//getMemberByUsername/" + username;
        Members[] memberList = restTemplate.getForObject(uri, Members[].class);
        assert memberList != null;
        return Arrays.asList(memberList);
    }

    //update memberFirstName
    public String updateMemberFirstName_DB(String username, String firstName){
        return apiUpdate("http://localhost:8080/updateMember/firstName/", username, "memberFirstName", firstName, "Member First Name", restTemplate);
    }

    //update memberLastName
    public String updateMemberLastName_DB(String username, String lastName){
        return apiUpdate("http://localhost:8080/updateMember/lastName/", username, "memberLastName", lastName, "Member Last Name", restTemplate);
    }

    //update memberPhoneNumber
    public String updateMemberPhoneNumber_DB(String username, String phoneNumber){
        return apiUpdate("http://localhost:8080/updateMember/phoneNumber/", username, "memberPhoneNumber", phoneNumber, "Member Phone Number", restTemplate);
    }

    //update memberEmail
    public String updateMemberEmail_DB(String username, String email){
        return apiUpdate("http://localhost:8080/updateMember/email/", username, "memberEmail", email, "Member Email", restTemplate);
    }

    //update username
    public String updateMemberUsername_DB(String username, String newUsername){
        return apiUpdate("http://localhost:8080/updateMember/username/", username, "username", newUsername, "Member Username", restTemplate);
    }

    //update member password
    public String updateMemberPassword_DB(String username, String password){
        return apiUpdate("http://localhost:8080/updateMember/password/", username, "password", password, "Member Password", restTemplate);
    }

    //update membershipPlan
    public String updateMemberPlan_DB(String username, String plan){
        return apiUpdate("http://localhost:8080/updateMember/membership/", username, "membershipPlan", plan, "Member Plan", restTemplate);
    }

    //update membershipStatus
    public String updateMemberStatus_DB(String username, String status){
        return apiUpdate("http://localhost:8080/updateMember/memberStatus/", username, "membershipStatus", status, "Member Status", restTemplate);
    }

    //update membershipPrice
    public String updateMemberPrice_DB(String username, String price){
        return apiUpdate("http://localhost:8080/updateMember/memberPrice/", username, "membershipPrice", price, "Member Price", restTemplate);
    }

    //update membershipExpiration
    public String updateMemberExpiration_DB(String username, String expiration){
        return apiUpdate("http://localhost:8080/updateMember/memberPrice/", username, "membershipExpiration", expiration, "Member Expiration", restTemplate);
    }

    //delete member
    private String deleteMemberByID_DB(long id){
        final String uri = "http://localhost:8080/deleteMember/" + id;
        restTemplate.delete(uri, id);
        log.info("Member Deleted Successfully With ID: " + id);
        return "Member with ID: " + id + " Successfully Deleted";
    }
}
