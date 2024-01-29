package com.bassit.bookstore.Services;

import com.bassit.bookstore.Models.Books;
import com.bassit.bookstore.Models.Customers;
import com.bassit.bookstore.Models.Members;
import lombok.extern.java.Log;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Consumer;

@Log
public class HelperFunctions {



    public static void header(String header){
        System.out.printf("%s\n%s\n%s\n", "------------------------------------------------", header ,"------------------------------------------------");
    }

    public static void printBook(List<Books> bookList){
        int counter=1;
        for(Books book : bookList){
            System.out.printf(counter + "\nTitle: %s\nAuthor: %s\nISBN: %s\nReleaseYear: %s\nPrice: %.2f\n------------------------------------------------\n", book.getTitle(), book.getAuthor(), book.getIsbn(), book.getReleaseYear(), book.getPrice());
            counter++;
        }
    }

    public static  List<Books> apiGetBook(String uri, String endpointName, RestTemplate restTemplate){
        Books[] sortBookList = restTemplate.getForObject(uri, Books[].class);
        log.info(endpointName + " Successfully Fetched from Database");
        assert sortBookList != null;
        log.info(endpointName + " Successfully Returned");
        return Arrays.asList(sortBookList);
    }

    //apiGet method with additional String parameter
    public static  List<Books> apiGetBook(String uri, String param, String endpointName, RestTemplate restTemplate){
        Books[] sortBookList = restTemplate.getForObject((uri + param), Books[].class);
        log.info(endpointName + " Successfully Fetched from Database");
        assert sortBookList != null;
        log.info(endpointName + " Successfully Returned");
        return Arrays.asList(sortBookList);
    }

    public static void printCustomer(List<Customers> customersList){
        int counter=1;
        for(Customers customers : customersList){
            System.out.printf(counter + "\n-----\nFirst Name: %s\nLast Name: %s\nEmail: %s\nPhone Number: %s\n------------------------------------------------\n", customers.getFirstName(), customers.getLastName(), customers.getEmail(), customers.getPhoneNumber());
            counter++;
        }
    }

    public static List<Customers> apiGetCustomer(String uri, String param, String endpointName, RestTemplate restTemplate){
        Customers[] customers = restTemplate.getForObject((uri + param), Customers[].class);
        log.info(endpointName + " Successfully Fetched from Database");
        assert customers != null;
        log.info(endpointName + " Successfully Returned");
        return Arrays.asList(customers);
    }

    public static String apiUpdate(String uriBase, String uriParam, String firstParameter, String value, String endpointName, RestTemplate restTemplate){
        final String uri = uriBase + uriParam;
        Map<String, String> map = new HashMap<>();
        map.put(firstParameter, value);
        restTemplate.postForEntity(uri, map, Void.class);
        log.info(endpointName + " successfully updated by User");
        return endpointName + " successfully updated by User";
    }

    public static void printMembers(List<Members> memberList){
        int counter=1;
        for(Members member : memberList){
            System.out.printf(counter + "\nFirstName: %s\nLastName: %s\nPhone Number: %s\nEmail: %s\nMembership Plan: %s\nMembership Status: %s\nMembership Price: %.2f\nMembership Expiration: %s\nMembership Purchase: %s\n------------------------------------------------\n",member.getMemberFirstName(), member.getMemberLastName(), member.getMemberPhoneNumber(), member.getMemberEmail(), member.getMembershipPlan(), member.getMembershipStatus(), member.getMembershipPrice(), member.getMembershipExpiration().toLocalDate(), member.getMembershipPurchaseDate().toLocalDate());
            counter++;
        }
    }




}
