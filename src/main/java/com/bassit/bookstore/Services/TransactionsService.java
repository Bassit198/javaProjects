package com.bassit.bookstore.Services;

import com.bassit.bookstore.Models.Transactions;
import lombok.extern.java.Log;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Log
public class TransactionsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Scanner keyboard = new Scanner(System.in);

    //user end
    //create transaction
    public void createTransaction_User(){
        System.out.print("Enter ISBN: ");
        String isbn = keyboard.nextLine();
        System.out.print("Enter credit card number: ");
        String ccNumber = keyboard.nextLine();
        System.out.println(createTransaction_DB(isbn, ccNumber));
    }

    //get transaction
    //get transaction by transaction number
    //get list of transactions between specific dates (LocalDateTime format: yyyy-MM-dd-HH-mm-ss)
    //get transaction by last 4 of cc
    //update transaction


    //db end
    //create transaction
    private String createTransaction_DB(String isbn, String maskedCC){
        Transactions transaction = new Transactions();
        final String uri = "http://localhost:8080/transaction/create";
        Map<String, String> map = new HashMap<>();
        map.put("purchasedIsbn", isbn);
        map.put("maskedCC", maskedCC);
        restTemplate.postForEntity(uri,map, Void.class);
        log.info("Transaction successfully created from service layer");
        return "Transaction Successfully Created";


    }
    //get transaction
    //get transaction by transaction number
    //get list of transactions between specific dates (LocalDateTime format: yyyy-MM-dd-HH-mm-ss)
    //get transaction by last 4 of cc
    //update transaction

    public void printTransaction(Transactions transactions){
        System.out.println("Transaction Number: " + transactions.getTransactionNumber());
        System.out.println("Purchased ISBN: " + transactions.getPurchasedIsbn());
        System.out.println("Purchase Date: " + transactions.getPurchaseDate());
        System.out.println("Transaction Status: " + transactions.getTransactionStatus());
    }
}
