package com.bassit.bookstore;

import com.bassit.bookstore.View.MainMenu;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
