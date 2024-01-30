package com.bassit.bookstore;

import com.bassit.bookstore.Models.Transactions;
import com.bassit.bookstore.Services.BooksService;
import com.bassit.bookstore.Services.CustomersService;
import com.bassit.bookstore.Services.MembersService;
import com.bassit.bookstore.Services.TransactionsService;
import com.bassit.bookstore.View.MainMenu;
import com.sun.tools.javac.Main;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.print.Book;

import static com.bassit.bookstore.Services.HelperFunctions.header;


@SpringBootApplication
@Log
public class BookStoreApplication implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Application Started Successfully");
        MainMenu.menu();




    }






}
