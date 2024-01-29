package com.bassit.bookstore.Controllers;

import com.bassit.bookstore.Models.Books;
import com.bassit.bookstore.Repo.BooksRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
public class BookController {

    @Autowired
    private BooksRepo booksRepo;

    @PostMapping("/addBook")
    public String addBook(@RequestBody Books book){
        booksRepo.save(book);
        log.info("Book successfully added from API endpoint with ID:" + book.getId());
        return "Book Successfully Added";
    }

    @GetMapping("/bookList")
    public List<Books> welcome(){
        log.info("List of All Books successfully returned from API endpoint");
        return booksRepo.findAll();
    }

    @PostMapping("/updatePrice/{id}")
    public String updateNullPrice(@RequestBody Books price, @PathVariable long id){
        booksRepo.priceUpdate(price.getPrice(), id);
        log.info("Book with ID: " + id + " successfully updated from API endpoint to: $" + price.getPrice());
        return "Price Updated for Book with ID: " + id;
    }

    @DeleteMapping("/deleteBookById/{id}")
    public String deleteBookById(@PathVariable long id){
        booksRepo.delete(booksRepo.findById(id).get());
        log.info("Book with ID: " + id + " successfully deleted from API endpoint.");
        return "Book Successfully Deleted";
    }
    //sort endpoints
    @GetMapping("/yearSortDesc")
    public List<Books> yearSortDesc(){
        log.info("Release year sorted list(Newest) successfully returned from API endpoint");
        return booksRepo.findAllByOrderByReleaseYearDesc();
    }

    @GetMapping("/yearSortAsc")
    public List<Books> yearSortAsc(){
        log.info("Release year sorted list(Oldest) successfully returned from API endpoint");
        return booksRepo.findAllByOrderByReleaseYearAsc();
    }

    @GetMapping("/authorSort")
    public List<Books> authorSort(){
        log.info("Author sorted list successfully returned from API endpoint");
        return booksRepo.findAllByOrderByAuthor();
    }

    @GetMapping("/isbnSort")
    public List<Books> isbnSort(){
        log.info("ISBN sorted list successfully returned from API endpoint");
        return booksRepo.findAllByOrderByIsbn();
    }

    @GetMapping("/titleSort")
    public List<Books> titleSort(){
        log.info("Title sorted list successfully returned from API endpoint");
        return booksRepo.findAllByOrderByTitle();
    }

    @GetMapping("/priceSortAsc")
    public List<Books> priceSortAsc(){
        log.info("Price sorted list successfully returned from API endpoint");
        return booksRepo.findAllByOrderByPriceAsc();
    }

    @GetMapping("/priceSortDesc")
    public List<Books> priceSortDesc(){
        log.info("Price sorted list(Highest) successfully returned from API endpoint");
        return booksRepo.findAllByOrderByPriceDesc();
    }

    //filter endpoints
    @GetMapping("/booksByYear/{year}")
    public List<Books> titleById(@PathVariable String year){
        log.info("Price sorted list(Lowest) successfully returned from API endpoint");
        return booksRepo.findBooksByReleaseYear(year);
    }

    @GetMapping("/booksByAuthor/{author}")
    public List<Books> booksByAuthor(@PathVariable String author){
        log.info("Filtered by Author list successfully returned from API endpoint");
        return booksRepo.findAllByAuthor(author);
    }

    @GetMapping("/booksByTitle/{title}")
    public List<Books> booksByTitle(@PathVariable String title){
        log.info("Filtered by Title list successfully returned from API endpoint");
        return booksRepo.findAllByTitle(title);
    }

    @GetMapping("/booksByISBN/{isbn}")
    public List<Books> booksByISBN(@PathVariable String isbn){
        log.info("Filtered by ISBN list successfully returned from API endpoint");
        return booksRepo.findAllByIsbn(isbn);
    }

    @GetMapping("/booksByPriceLess/{price}")
    public List<Books> booksByPriceLess(@PathVariable double price){
        log.info("Filtered by Price(Less Than) list successfully returned from API endpoint");
        return booksRepo.findAllByPriceLessThanEqualOrderByPriceAsc(price);
    }

    @GetMapping("/booksByPriceGreater/{price}")
    public List<Books> booksByPriceGreater(@PathVariable double price){
        log.info("Filtered by Price(More Than) list successfully returned from API endpoint");
        return booksRepo.findAllByPriceGreaterThanEqualOrderByPriceAsc(price);
    }


}
