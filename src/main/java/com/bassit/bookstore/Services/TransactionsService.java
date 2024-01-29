package com.bassit.bookstore.Services;

import com.bassit.bookstore.Models.Members;
import com.bassit.bookstore.Models.Transactions;
import lombok.extern.java.Log;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.bassit.bookstore.Services.HelperFunctions.header;
import static com.bassit.bookstore.Services.HelperFunctions.printTransaction;

@Log
public class TransactionsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Scanner keyboard = new Scanner(System.in);

    //user end
    //create transaction
    public void createTransaction_User(){
        header("Create Transaction");
        System.out.print("Enter ISBN: ");
        String isbn = keyboard.nextLine();
        System.out.print("Enter credit card number: ");
        String ccNumber = keyboard.nextLine();
        System.out.println(createTransaction_DB(isbn, ccNumber));
    }

    //get transaction
    //get transaction by transaction number
    public void getTransactionUsingTransactionNumber_User(){
        header("Get Transaction by Transaction Number");
        System.out.print("Enter transaction number: ");
        long transactionNumber = keyboard.nextLong();
        printTransaction(getTransactionUsingTransactionNumber_DB(transactionNumber));
    }
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
    private List<Transactions> getTransactionUsingTransactionNumber_DB(long transactionNumber){
        final String uri = "http://localhost:8080/transaction/getTransaction/" + transactionNumber;
        Transactions[] transactions = restTemplate.getForObject(uri, Transactions[].class);
        assert transactions != null;
        return Arrays.asList(transactions);
    }
    //get list of transactions between specific dates (LocalDateTime format: yyyy-MM-dd-HH-mm-ss)
    //get transaction by last 4 of cc
    //update transaction


}
