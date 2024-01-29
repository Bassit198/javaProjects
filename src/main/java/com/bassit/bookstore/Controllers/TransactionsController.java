package com.bassit.bookstore.Controllers;

import com.bassit.bookstore.Models.Transactions;
import com.bassit.bookstore.Repo.TransactionsRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Log
@RestController
public class TransactionsController {

    @Autowired
    private TransactionsRepo transactionsRepo;

    //create transaction
    @PostMapping("/transaction/create")
    public String createTransaction(@RequestBody Transactions transactionDetails){
        Transactions newTransaction = new Transactions();
//        newTransaction.setPurchaseDate(LocalDate.now());
        newTransaction.setPurchaseDate(transactionDetails.getPurchaseDate());
        newTransaction.setPurchasedIsbn(transactionDetails.getPurchasedIsbn());
        newTransaction.setMaskedCC(transactionDetails.getMaskedCC());
        newTransaction.setTransactionStatus("Active");
        transactionsRepo.save(newTransaction);
        log.info("Transaction created successfully from API endpoint with transaction number: " + newTransaction.getTransactionNumber());
        return "Transaction Completed Successfully";
    }

    //read transaction
    @GetMapping("/transaction/getTransaction/{transactionNumber}")
    public List<Transactions> findTransactionByTranNumber(@PathVariable long transactionNumber){
        log.info("Transaction successfully found with transaction number: " + transactionNumber);
        return transactionsRepo.findAllByTransactionNumber(transactionNumber);
    }

    //find transaction list within date range
    @GetMapping("/transaction/getTransactionBetweenDate3/{lowerLimit}/{upperLimit}")
    public List<Transactions> findTransactionBetweenDateRange(@PathVariable LocalDate lowerLimit, @PathVariable LocalDate upperLimit){
        return transactionsRepo.findAllByPurchaseDateGreaterThanEqualAndPurchaseDateLessThanEqual(lowerLimit, upperLimit);
    }
    //find transaction list with last 4 of cc
    //find transaction list by status

    //update(refund) transaction
    @PostMapping("/transaction/refund/{transactionNumber}")
    public String updateTransactionRefund(@PathVariable long transactionNumber){
        Transactions transactionToUpdate = transactionsRepo.findByTransactionNumber(transactionNumber);
        transactionToUpdate.setTransactionStatus("REFUNDED");
        transactionToUpdate.setModifiedDate(LocalDate.now());
        transactionsRepo.save(transactionToUpdate);
        log.info("Transaction successfully refunded with transaction number: " + transactionNumber);
        return "Transaction Updated Successfully";
    }

    //update(cancel) transaction
    @PostMapping("/transaction/cancel/{transactionNumber}")
    public String updateTransactionCancel(@PathVariable long transactionNumber){
        Transactions transactionToUpdate = transactionsRepo.findByTransactionNumber(transactionNumber);
        transactionToUpdate.setTransactionStatus("CANCELLED");
        transactionToUpdate.setModifiedDate(LocalDate.now());
        transactionsRepo.save(transactionToUpdate);
        log.info("Transaction successfully cancelled with transaction number: " + transactionNumber);
        return "Transaction Updated Successfully";
    }

    //delete transaction by id_Admin
    @DeleteMapping("/transaction/delete/{id}")
    public String deleteTransaction(@PathVariable long id){
        transactionsRepo.deleteById(id);
        log.info("Transaction successfully deleted with ID: " + id);
        return "Successfully Deleted Transaction with ID: " + id;
    }
}
