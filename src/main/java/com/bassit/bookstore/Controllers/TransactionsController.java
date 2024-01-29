package com.bassit.bookstore.Controllers;

import com.bassit.bookstore.Models.Transactions;
import com.bassit.bookstore.Repo.TransactionsRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        newTransaction.setTransactionNumber(new Random().nextLong(10000));
        newTransaction.setPurchaseDate(LocalDateTime.now());
        newTransaction.setPurchasedIsbn(transactionDetails.getPurchasedIsbn());
        newTransaction.setMaskedCC(transactionDetails.getMaskedCC());
        newTransaction.setTransactionStatus(transactionDetails.getTransactionStatus());
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

    //update(refund) transaction
    @PostMapping("/transaction/refund/{transactionNumber}")
    public String updateTransactionRefund(@PathVariable long transactionNumber){
        Transactions transactionToUpdate = transactionsRepo.findByTransactionNumber(transactionNumber);
        transactionToUpdate.setTransactionStatus("REFUNDED");
        transactionToUpdate.setModifiedDate(LocalDateTime.now());
        transactionsRepo.save(transactionToUpdate);
        log.info("Transaction successfully refunded with transaction number: " + transactionNumber);
        return "Transaction Updated Successfully";
    }

    //update(cancel) transaction
    @PostMapping("/transaction/cancel/{transactionNumber}")
    public String updateTransactionCancel(@PathVariable long transactionNumber){
        Transactions transactionToUpdate = transactionsRepo.findByTransactionNumber(transactionNumber);
        transactionToUpdate.setTransactionStatus("CANCELLED");
        transactionToUpdate.setModifiedDate(LocalDateTime.now());
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
