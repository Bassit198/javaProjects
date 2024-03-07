package com.bassit.bookstore.Services;

import com.bassit.bookstore.Models.Transactions;
import lombok.extern.java.Log;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.bassit.bookstore.Services.HelperFunctions.*;
import static com.bassit.bookstore.Services.HelperFunctions.apiUpdateTransaction;

@Log
public class TransactionsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Scanner keyboard = new Scanner(System.in);

    //user end
    //create transaction
    public void createTransaction_User(String  isbn, String  ccNumber){
        createTransaction_DB(isbn, ccNumber);
    }

    //get transaction
    //get transaction by transaction number
    public List<Transactions> getTransactionUsingTransactionNumber_User(long transactionNumber){
        return getTransactionUsingTransactionNumber_DB(transactionNumber);
    }

    //get list of transactions between specific dates (LocalDateTime format: yyyy-MM-dd-HH-mm-ss)
    public List<Transactions> getTransactionUsingDateRange_User(String lowerLimitDateString, String upperLimitDateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);
        LocalDate lowerLimitDate = LocalDate.parse(lowerLimitDateString, formatter);
        LocalDate upperLimitDate = LocalDate.parse(upperLimitDateString, formatter);
        return getTransactionUsingDateRange_DB(lowerLimitDate, upperLimitDate);
    }
    //get transaction by last 4 of cc


    //update transaction status
    //refund
    public void refundTransaction_User(long transactionNumber){
        refundTransaction_DB(transactionNumber);
    }

    //cancel
    public void cancelTransaction_User(long transactionNumber){
        cancelTransaction_DB(transactionNumber);
    }



    //db end
    //create transaction
    private void createTransaction_DB(String isbn, String maskedCC){
        final String uri = "http://localhost:8080/transaction/create";
        Map<String, String> map = new HashMap<>();
        map.put("purchasedIsbn", isbn);
        map.put("maskedCC", maskedCC);
        restTemplate.postForEntity(uri,map, Void.class);
        log.info("Transaction successfully created from service layer");


    }
    //get transaction
    //get transaction by transaction number
    private List<Transactions> getTransactionUsingTransactionNumber_DB(long transactionNumber){
        return apiGetTransaction("http://localhost:8080/transaction/getTransaction/", String.valueOf(transactionNumber), restTemplate);
    }

    //get list of transactions between specific dates (LocalDateTime format: yyyy-MM-dd)
    private List<Transactions> getTransactionUsingDateRange_DB(LocalDate lowerLimit, LocalDate upperLimit){
        return apiGetTransaction("http://localhost:8080/transaction/getTransactionBetweenDate/", (lowerLimit + "/" + upperLimit), restTemplate);
    }

    //get transaction by last 4 of cc


    //update transaction status
    //refund
    private void refundTransaction_DB(long transactionNumber){
        apiUpdateTransaction("http://localhost:8080/transaction/refund/", String.valueOf(transactionNumber), "refunded", restTemplate);
    }

    //cancel
    private void cancelTransaction_DB(long transactionNumber){
        apiUpdateTransaction("http://localhost:8080/transaction/cancel/", String.valueOf(transactionNumber), "cancelled", restTemplate);
    }


}
