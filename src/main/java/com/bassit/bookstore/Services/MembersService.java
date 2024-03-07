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
    public void createMember_User(String firstName, String lastName, String email, String phoneNumber, String username, String password, String plan){
        createMembers_DB(firstName, lastName, email, phoneNumber, username, password, plan);
    }

    //read member by username
    public List<Members> getMemberByUsername_User(String username){
        return getMemberByUsername_DB(username);
    }

    //update memberFirstName
    public void updateMemberFirstName_User(String username, String newFirstName){
        updateMemberFirstName_DB(username, newFirstName);


    }

    //update memberLastName
    public void updateMemberLastName_User(String username, String newLastName){
        updateMemberLastName_DB(username, newLastName);
    }

    //update memberPhoneNumber
    public void updateMemberPhoneNumber_User(String username, String newPhoneNumber){
        updateMemberPhoneNumber_DB(username, newPhoneNumber);
    }

    //update memberEmail
    public void updateMemberEmail_User(String username, String newEmail){
        updateMemberEmail_DB(username, newEmail);
    }

    //update username
    public void updateMemberUsername_User(String username, String newUsername){
        updateMemberUsername_DB(username, newUsername);
    }

    //update member password
    public void updateMemberPassword_User(String username, String newPassword){
        updateMemberPassword_DB(username, newPassword);
    }

    //update membershipPlan
    public void updateMemberPlan_User(String username, String newPlan){
        updateMemberPlan_DB(username, newPlan);
    }

    //update membershipStatus
    public void updateMemberStatus_User(String username, String newStatus){
        updateMemberStatus_DB(username, newStatus);
    }

    //update membershipPrice
    public void updateMemberPrice_User(String username, String newPrice){
        updateMemberPrice_DB(username, newPrice);
    }

    //update membershipExpiration
    public void updateMemberExp_User(String username, String newExpDate){
        updateMemberExpiration_DB(username, LocalDate.parse(newExpDate));
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
