package com.bassit.bookstore.Controllers;

import com.bassit.bookstore.Models.Customers;
import com.bassit.bookstore.Repo.CustomersRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Log
@RestController
public class CustomersController {

    @Autowired
    private CustomersRepo customersRepo;

    //create customer
    @PostMapping("/addCustomer")
    public Customers welcome(@RequestBody Customers customerInfo){
        log.info("Customer added successfully from API endpoint");
        return customersRepo.save(customerInfo);
    }

    //read customer admin_find by id
    @GetMapping("/getCustomerById/{id}")
    public List<Customers> getCustomerById(@PathVariable long id){
        log.info("Customer found by ID successfully from API endpoint with ID: " + id);
        return customersRepo.findAllById(id);
    }

    //read customer by first name and last name
    @GetMapping("/getCustomer/{firstName}/{lastName}")
    public List<Customers> getCustomerByFirstAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        log.info("Customer found by firstname and lastname successfully from API endpoint");
        return customersRepo.findAllByFirstNameAndLastName(firstName, lastName);
    }

    //read customer by email
    @GetMapping("/getCustomer/email/{email}")
    public List<Customers> getCustomerByEmail(@PathVariable String email){
        log.info("Customer found by email successfully from API endpoint");
        return customersRepo.findAllByEmail(email);
    }

    //read customer by phone
    @GetMapping("/getCustomer/phone/{phone}")
    public List<Customers> getCustomerByPhoneNumber(@PathVariable String phone){
        log.info("Customer found by phone number successfully from API endpoint");
        return customersRepo.findAllByPhoneNumber(phone);
    }

    //update customer first name
    @PostMapping("/updateCustomerFirstName/{firstName}/{lastName}")
    public String updateCustomerFirstName(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Customers newCustomerInfo){
        List<Customers> customersToUpdate = customersRepo.findAllByFirstNameAndLastName(firstName, lastName);
        for (Customers customer : customersToUpdate) {
            customer.setFirstName(newCustomerInfo.getFirstName());
            customersRepo.save(customer);
        }
        log.info("Customer Firstname updated successfully from API endpoint");
        return "Customer First Name successfully updated";
    }

    //update customer last name
    @PostMapping("/updateCustomerLastName/{firstName}/{lastName}")
    public String updateCustomerLastName(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Customers newCustomerInfo){
        List<Customers> customersToUpdate = customersRepo.findAllByFirstNameAndLastName(firstName, lastName);
        for (Customers customer : customersToUpdate) {
            customer.setLastName(newCustomerInfo.getLastName());
            customersRepo.save(customer);
        }
        log.info("Customer lastname updated successfully from API endpoint");
        return "Customer Last Name successfully updated";
    }

    //update customer email
    @PostMapping("/updateCustomerEmail/{firstName}/{lastName}")
    public String updateCustomerEmail(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Customers newCustomerInfo){
        List<Customers> customersToUpdate = customersRepo.findAllByFirstNameAndLastName(firstName, lastName);
        for (Customers customer : customersToUpdate) {
            customer.setEmail(newCustomerInfo.getEmail());
            customersRepo.save(customer);
        }
        log.info("Customer email updated successfully from API endpoint");
        return "Customer Email successfully updated";
    }

    //update customer phone
    @PostMapping("/updateCustomerPhone/{firstName}/{lastName}")
    public String updateCustomerPhone(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Customers newCustomerInfo){
        List<Customers> customersToUpdate = customersRepo.findAllByFirstNameAndLastName(firstName, lastName);
        for (Customers customer : customersToUpdate) {
            customer.setPhoneNumber(newCustomerInfo.getPhoneNumber());
            customersRepo.save(customer);
        }
        log.info("Customer phone number updated successfully from API endpoint");
        return "Customer Phone Number successfully updated";
    }

    //delete customer (admin_delete by id)
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable long id){
        customersRepo.deleteById(id);
        log.info("Customer deleted successfully from API endpoint with ID: " + id);
        return "Customer Deleted Successfully with ID: " + id;
    }


}
