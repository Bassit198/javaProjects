package com.bassit.bookstore.Services;

import com.bassit.bookstore.Models.Members;
import com.bassit.bookstore.Models.Transactions;
import lombok.extern.java.Log;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public void getTransactionUsingDateRange_User(){
        header("Get Transaction in Date Range");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);

        System.out.print("Enter beginning date (YYYY-MM-DD): ");
        LocalDate lowerLimitDate = LocalDate.parse(keyboard.nextLine(), formatter);

        System.out.print("Enter ending date (YYYY-MM-DD): ");
        LocalDate upperLimitDate = LocalDate.parse(keyboard.nextLine(), formatter);

        printTransaction(getTransactionUsingDateRange_DB(lowerLimitDate, upperLimitDate));
    }
    //get transaction by last 4 of cc


    //update transaction status
    //refund
    public void refundTransaction_User(){
        header("Refund Transaction");
        System.out.print("Enter transaction number to refund: ");
        long transactionNumber = keyboard.nextLong();
        System.out.println(refundTransaction_DB(transactionNumber));
    }

    //cancel
    public void cancelTransaction_User(){
        header("Cancel Transaction");
        System.out.print("Enter transaction number to cancel: ");
        long transactionNumber = keyboard.nextLong();
        System.out.println(cancelTransaction_DB(transactionNumber));
    }



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

    //get list of transactions between specific dates (LocalDateTime format: yyyy-MM-dd)
    private List<Transactions> getTransactionUsingDateRange_DB(LocalDate lowerLimit, LocalDate upperLimit){
        final String uri = "http://localhost:8080/transaction/getTransactionBetweenDate/" + lowerLimit + "/" + upperLimit;
        Transactions[] transactions = restTemplate.getForObject(uri, Transactions[].class);
        assert transactions != null;
        return Arrays.asList(transactions);
    }

    //get transaction by last 4 of cc


    //update transaction status
    //refund
    private String refundTransaction_DB(long transactionNumber){
        final String uri = "http://localhost:8080/transaction/refund/" + transactionNumber;
        restTemplate.postForEntity(uri, null, Void.class);
        return "Transaction refunded successfully";
    }

    //cancel
    private String cancelTransaction_DB(long transactionNumber){
        final String uri = "http://localhost:8080/transaction/cancel/" + transactionNumber;
        restTemplate.postForEntity(uri, null, Void.class);
        return "Transaction cancelled successfully";
    }


}
