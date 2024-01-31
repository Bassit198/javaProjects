package com.bassit.bookstore.Services;

import com.bassit.bookstore.Models.Members;
import lombok.extern.java.Log;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
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
    public void getMemberByUsername_User(){
        header("Find Member By Username");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        printMembers(getMemberByUsername_DB(username));
    }

    //update memberFirstName
    public void updateMemberFirstName_User(){
        header("Update Member First Name");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member updated first name: ");
        String newFirstName = keyboard.nextLine();
        System.out.println(updateMemberFirstName_DB(username, newFirstName));


    }

    //update memberLastName
    public void updateMemberLastName_User(){
        header("Update Member Last Name");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member updated last name: ");
        String newLastName = keyboard.nextLine();
        System.out.println(updateMemberLastName_DB(username, newLastName));
    }

    //update memberPhoneNumber
    public void updateMemberPhoneNumber_User(){
        header("Update Member Phone Number");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member updated phone Number (xxx-xxx-xxxx): ");
        String newPhoneNumber = keyboard.nextLine();
        System.out.println(updateMemberPhoneNumber_DB(username, newPhoneNumber));
    }

    //update memberEmail
    public void updateMemberEmail_User(){
        header("Update Member Email");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member updated email: ");
        String newEmail = keyboard.nextLine();
        System.out.println(updateMemberEmail_DB(username, newEmail));
    }

    //update username
    public void updateMemberUsername_User(){
        header("Update Member Username");
        System.out.print("Enter member old username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member new username: ");
        String newUsername = keyboard.nextLine();
        System.out.println(updateMemberUsername_DB(username, newUsername));
    }

    //update member password
    public void updateMemberPassword_User(){
        header("Update Member Password");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member new password: ");
        String newPassword = keyboard.nextLine();
        System.out.println(updateMemberPassword_DB(username, newPassword));
    }

    //update membershipPlan
    public void updateMemberPlan_User(){
        header("Update Member Plan");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member new plan: ");
        String newPlan = keyboard.nextLine();
        System.out.println(updateMemberPlan_DB(username, newPlan));
    }

    //update membershipStatus
    public void updateMemberStatus_User(){
        header("Update Member Status");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member new status: ");
        String newStatus = keyboard.nextLine();
        System.out.println(updateMemberStatus_DB(username, newStatus));
    }

    //update membershipPrice
    public void updateMemberPrice_User(){
        header("Update Member Price");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member new price: ");
        String newPrice = keyboard.nextLine();
        System.out.println(updateMemberPrice_DB(username, newPrice));
    }

    //update membershipExpiration
    public void updateMemberExp_User(){
        header("Update Member Expiration Date");
        System.out.print("Enter member username: ");
        String username = keyboard.nextLine();
        System.out.print("Enter member new expiration date: ");
        String newExpDate = keyboard.nextLine();
        System.out.println(updateMemberExpiration_DB(username, LocalDate.parse(newExpDate)));
    }

    //delete member
    public void deleteMember_User(){
        header("Delete Member");
        System.out.print("Enter member ID: ");
        long id = keyboard.nextLong();
        System.out.println(deleteMemberByID_DB(id));
    }


    //db end
    //create member
    private String createMembers_DB(String firstName, String lastName, String email, String phoneNumber, String username, String password, String plan){
        final String uri = "http://localhost:8080/addMember";
        Map<String, String> map = new HashMap<>();
        map.put("memberFirstName", firstName);
        map.put("memberLastName", lastName);
        map.put("memberEmail", email);
        map.put("memberPhoneNumber", phoneNumber);
        map.put("username", username);
        map.put("password", password);
        map.put("membershipPlan", plan);
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
    private String updateMemberFirstName_DB(String username, String firstName){
        return apiUpdate("http://localhost:8080/updateMember/firstName/", username, "memberFirstName", firstName, "Member First Name", restTemplate);
    }

    //update memberLastName
    private String updateMemberLastName_DB(String username, String lastName){
        return apiUpdate("http://localhost:8080/updateMember/lastName/", username, "memberLastName", lastName, "Member Last Name", restTemplate);
    }

    //update memberPhoneNumber
    private String updateMemberPhoneNumber_DB(String username, String phoneNumber){
        return apiUpdate("http://localhost:8080/updateMember/phoneNumber/", username, "memberPhoneNumber", phoneNumber, "Member Phone Number", restTemplate);
    }

    //update memberEmail
    private String updateMemberEmail_DB(String username, String email){
        return apiUpdate("http://localhost:8080/updateMember/email/", username, "memberEmail", email, "Member Email", restTemplate);
    }

    //update username
    private String updateMemberUsername_DB(String username, String newUsername){
        return apiUpdate("http://localhost:8080/updateMember/username/", username, "username", newUsername, "Member Username", restTemplate);
    }

    //update member password
    private String updateMemberPassword_DB(String username, String password){
        return apiUpdate("http://localhost:8080/updateMember/password/", username, "password", password, "Member Password", restTemplate);
    }

    //update membershipPlan
    private String updateMemberPlan_DB(String username, String plan){
        return apiUpdate("http://localhost:8080/updateMember/membership/", username, "membershipPlan", plan, "Member Plan", restTemplate);
    }

    //update membershipStatus
    private String updateMemberStatus_DB(String username, String status){
        return apiUpdate("http://localhost:8080/updateMember/memberStatus/", username, "membershipStatus", status, "Member Status", restTemplate);
    }

    //update membershipPrice
    private String updateMemberPrice_DB(String username, String price){
        return apiUpdate("http://localhost:8080/updateMember/memberPrice/", username, "membershipPrice", price, "Member Price", restTemplate);
    }

    //update membershipExpiration
    private String updateMemberExpiration_DB(String username, LocalDate expiration){
        final String uri = "http://localhost:8080/updateMember/memberExp/" + username +"/" + expiration;
        restTemplate.postForEntity(uri, null, Void.class);
        return "Expiration successfully updated";
    }

    //delete member
    private String deleteMemberByID_DB(long id){
        final String uri = "http://localhost:8080/deleteMember/" + id;
        restTemplate.delete(uri, id);
        log.info("Member Deleted Successfully With ID: " + id);
        return "Member with ID: " + id + " Successfully Deleted";
    }
}
