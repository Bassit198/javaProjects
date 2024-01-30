package com.bassit.bookstore.Services;

import com.bassit.bookstore.Models.Customers;
import lombok.extern.java.Log;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.bassit.bookstore.Services.HelperFunctions.*;

@Log
public class CustomersService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Scanner keyboard = new Scanner(System.in);

    //methods for user section
    //create customer_User
    public void createdCustomer_User(){
        header("Create Customer");
        System.out.print("Enter First Name: ");
        String firstName = keyboard.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = keyboard.nextLine();
        System.out.print("Enter Email: ");
        String email = keyboard.nextLine();
        System.out.print("Enter Phone Number in the Format of xxx-xxx-xxxx: ");
        String phoneNumber = keyboard.nextLine();
        System.out.println(createCustomer_DB(firstName, lastName, email, phoneNumber));
    }

    //read customer admin_find by id
    public void findCustomerById_Admin(){
        header("Find Customer By ID Admin");
        System.out.print("Enter ID of customer: ");
        long id = keyboard.nextLong();
        printCustomer(findCustomerById_Admin(id));

    }

    //read customer by first name and last name_User
    public void findCustomerByFirstAndLastName_User(){
        header("Find Customer By First Name and Last Name");
        System.out.print("Enter first name: ");
        String firstName = keyboard.nextLine();
        System.out.print("Enter last name: ");
        String lastName = keyboard.nextLine();
        printCustomer(findCustomersByFirstAndLastname_DB(firstName, lastName));
    }

    //read customer by email_User
    public void findCustomerByEmail_User(){
        HelperFunctions.header("Find Customer By Email");
        System.out.print("Enter customer email: ");
        String email = keyboard.nextLine();
        printCustomer(findCustomersByEmail_DB(email));
    }

    //read customer by phone_User
    public void findCustomerByPhoneNumber_User(){
        header("Find Customer By Phone Number");
        System.out.print("Enter customer phone number: ");
        String phone = keyboard.nextLine();
        printCustomer(findCustomersByPhone_DB(phone));
    }

    //update customer first name_User
    public void updateCustomerFirstName_User(){
        header("Update Customer First Name");
        System.out.print("Enter old first name: ");
        String firstName = keyboard.nextLine();
        System.out.print("Enter last name: ");
        String lastName = keyboard.nextLine();
        System.out.print("Enter new first name: ");
        String newFirstName = keyboard.nextLine();
        System.out.println(updateCustomerFirstName_DB(firstName, lastName, newFirstName));
    }
    
    //update customer last name_User
    public void updateCustomerLastName_User(){
        header("Update Customer Last Name");
        System.out.print("Enter first name: ");
        String firstName = keyboard.nextLine();
        System.out.print("Enter old last name: ");
        String lastName = keyboard.nextLine();
        System.out.print("Enter new last name: ");
        String newLastName = keyboard.nextLine();
        System.out.println(updateCustomerLastName_DB(firstName, lastName, newLastName));
    }
    
    //update customer email_User
    public void updateCustomerEmail_User(){
        header("Update Customer Email");
        System.out.print("Enter first name: ");
        String firstName = keyboard.nextLine();
        System.out.print("Enter last name: ");
        String lastName = keyboard.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = keyboard.nextLine();
        System.out.println(updateCustomerEmail_DB(firstName, lastName, newEmail));
    }
    
    //update customer phone_User
    public void updateCustomerPhoneNumber_User(){
        header("Update Customer Phone Number");
        System.out.print("Enter first name: ");
        String firstName = keyboard.nextLine();
        System.out.print("Enter last name: ");
        String lastName = keyboard.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhoneNumber = keyboard.nextLine();
        System.out.println(updateCustomerLastName_DB(firstName, lastName, newPhoneNumber));
    }
    
    //delete customer (admin_delete by id)
    public void deleteCustomerById_Admin(){
        header("Delete Customer By ID");
        System.out.print("Enter customer ID: ");
        long id = keyboard.nextLong();
        System.out.println(deleteCustomerByID_DB(id));
    }



    //methods for sending requests to API
    //create customer_DB
    private String createCustomer_DB(String firstName, String lastName, String email, String phoneNumber){
        final String uri = "http://localhost:8080/addCustomer";
        Map<String, String> map = new HashMap<>();
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("email", email);
        map.put("phoneNumber", phoneNumber);
        restTemplate.postForEntity(uri,map, Void.class);
        log.info("Customer successfully created from service layer");
        return "Customer successfully created.";

    }

    //read customer admin_find by id
    private List<Customers> findCustomerById_Admin(long id){
        return apiGetCustomer("http://localhost:8080/getCustomerById/", String.valueOf(id), "Admin_Find customer by ID", restTemplate);
    }

    //read customer by first name and last name_DB
    private List<Customers> findCustomersByFirstAndLastname_DB(String firstName, String lastName){
        return apiGetCustomer("http://localhost:8080/getCustomer/", (firstName +"/"+ lastName), "Get customer by first and last name", restTemplate);
    }

    //read customer by email_DB
    private List<Customers> findCustomersByEmail_DB(String email){
        return apiGetCustomer("http://localhost:8080/getCustomer/email/", email, "Get Customer By Email", restTemplate);
    }

    //read customer by phone_DB
    private List<Customers> findCustomersByPhone_DB(String phoneNumber){
        return apiGetCustomer("http://localhost:8080/getCustomer/phone/", phoneNumber, "Get Customer By Email", restTemplate);
    }

    //update customer first name_DB
    private String updateCustomerFirstName_DB(String firstName, String lastName, String newFirst){
        return apiUpdate("http://localhost:8080/updateCustomerFirstName/", (firstName +"/"+ lastName), "firstName", newFirst, "Customer First Name", restTemplate);
    }

    //update customer last name_DB
    private String updateCustomerLastName_DB(String firstName, String lastName, String newLast){
        return apiUpdate("http://localhost:8080/updateCustomerLastName/", (firstName +"/"+ lastName), "lastName", newLast, "Customer Last Name", restTemplate);
    }

    //update customer email_DB
    private String updateCustomerEmail_DB(String firstName, String lastName, String newEmail){
        return apiUpdate("http://localhost:8080/updateCustomerEmail/", (firstName +"/"+ lastName), "email", newEmail, "Customer Email", restTemplate);
    }

    //update customer phone_DB
    private String updateCustomerPhoneNumber_DB(String firstName, String lastName, String newPhone){
        return apiUpdate("http://localhost:8080/updateCustomerPhone/", (firstName +"/"+ lastName), "phoneNumber", newPhone, "Customer Phone Number", restTemplate);
    }

    //delete customer (admin_delete by id)
    private String deleteCustomerByID_DB(long id){
        final String uri = "http://localhost:8080/deleteCustomer/" + id;
        restTemplate.delete(uri, id);
        log.info("Customer Deleted Successfully With ID: " + id);
        return "Customer with ID: " + id + " Successfully Deleted";
    }

    
}
