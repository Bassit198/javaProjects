package com.bassit.bookstore.Services;

import com.bassit.bookstore.Models.Books;
import lombok.extern.java.Log;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.bassit.bookstore.Services.HelperFunctions.*;

@Log
public class BooksService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Scanner keyboard = new Scanner(System.in);

    //methods for user section
    public void addBookToDB_User(){
        header("Add Book to Database");
        System.out.println("Enter the book Information:\nTitle: ");
        String title = keyboard.next();
        System.out.println("Author: ");
        String author = keyboard.next();
        System.out.println("ISBN: ");
        String isbn = keyboard.next();
        System.out.println("Year: ");
        String year = keyboard.next();
        System.out.println("Price: ");
        Double price = keyboard.nextDouble();
        System.out.println(addBookToDB(title, author, isbn, year, price));
    }

    public void updatePriceOfBook_User(){
        header("Update Price of A Book");
        System.out.println("Enter the book Information:\nBook ID: ");
        int id = keyboard.nextInt();
        System.out.println("New Price: ");
        Double price = keyboard.nextDouble();
        System.out.println(updatePriceOfBookDB(id, price));
    }

    public void deleteBookByID_User(){
        header("Delete Book");
        System.out.println("Enter Book ID to delete: ");
        int id = keyboard.nextInt();
        System.out.println(deleteBookByIDDB(id));
    }

    public void listAllBooks_User(){
        header("List of all Books");
        printBook(listAllBooksBD());
    }

    public void sortBooksByNewest_User(){
        header("List of All Books By Newest Releases");
        printBook(sortBooksByNewestDB());
    }

    public void sortBooksByOldest_User(){
        header("List of All Books By Oldest Releases");
        printBook(sortBooksByOldestDB());
    }

    public void sortBooksByAuthor_User(){
        header("List of All Books By Author Names");
        printBook(sortBooksByAuthorDB());
    }

    public void sortBooksByISBN_User(){
        header("List of All Books By ISBN");
        printBook(sortBooksByIsbnDB());
    }

    public void sortBooksByTitle_User(){
        header("List of All Books By Title");
        printBook(sortBooksByTitleDB());
    }

    public void sortBooksByPriceLowToHigh_User(){
        header("List of All Books Lowest to Highest Price");
        printBook(sortBooksByPriceLowToHighDB());
    }

    public void sortBooksByPriceHighToLow_User(){
        header("List of All Books Highest to Lowest Price");
        printBook(sortBooksByPriceHighToLowDB());
    }

    public void searchByYear_User(String year){
        header("List Of All Books For " + year);
        printBook(searchByYearDB(year));
    }

    public void searchByAuthor_User(String author){
        header("List Of All Books By: " + author);
        printBook(searchByAuthorDB(author));
    }

    public void searchByTitle_User(String title){
        header("List Of All Books With Name: " + title);
        printBook(searchByTitleDB(title));
    }

    public void searchByISBN_User(String isbn){
        header("List Of All Books With ISBN: " + isbn);
        printBook(searchByISBNDB(isbn));
    }

    //methods for sending requests to API
    private String addBookToDB(String title, String author, String isbn, String year, Double price){
        final String uri = "http://localhost:8080/addBook";
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("author", author);
        map.put("isbn", isbn);
        map.put("releaseYear", year);
        map.put("price", String.valueOf(price));
        //ResponseEntity<Void> response = restTemplate.postForEntity(uri, map, Void.class);
        restTemplate.postForEntity(uri, map, Void.class);
        log.info("Book Added to Database Successfully");
        return "Book Added Successfully";
    }

    private String updatePriceOfBookDB(long id, Double price){
        final String uri = "http://localhost:8080/updatePrice/" + id;
        Map<String, Double> mapBody = new HashMap<>();
        mapBody.put("price", price);
        //ResponseEntity<Void> response = restTemplate.postForEntity(uri, mapBody, Void.class);
        restTemplate.postForEntity(uri, mapBody, Void.class);
        log.info("Price updated successfully to: " + price + " for book with id: " + id);
        return "Price Successfully Updated for Book with ID: " + id;
    }

    private String deleteBookByIDDB(long id){
        final String uri = "http://localhost:8080/deleteBookById/" + id;
        restTemplate.delete(uri, id);
        log.info("Book Deleted Successfully With ID: " + id);
        return "Book with ID: " + id + " Successfully Deleted";
    }

    private List<Books> listAllBooksBD(){
        final String uri = "http://localhost:8080/bookList";
        Books[] bookList = restTemplate.getForObject(uri, Books[].class);
        log.info("Book List Successfully Fetched from Database");
        assert bookList != null;
        log.info("Book List Successfully Returned");
        return Arrays.asList(bookList);
    }

    private List<Books> sortBooksByNewestDB(){
        return apiGetBook("http://localhost:8080/yearSortDesc","Sort By Newest", restTemplate);
    }

    private List<Books> sortBooksByOldestDB(){
        return apiGetBook("http://localhost:8080/yearSortAsc","Sort By Oldest", restTemplate);
    }

    private List<Books> sortBooksByAuthorDB(){
        return apiGetBook("http://localhost:8080/authorSort", "Sort By Author Names", restTemplate);
    }

    private List<Books> sortBooksByIsbnDB(){
        return apiGetBook("http://localhost:8080/isbnSort", "Sort By ISBN", restTemplate);
    }

    private List<Books> sortBooksByTitleDB(){
        return apiGetBook("http://localhost:8080/titleSort", "Sort By Title", restTemplate);
    }

    private List<Books> sortBooksByPriceLowToHighDB(){
        return apiGetBook("http://localhost:8080/priceSortAsc", "Sort By Lowest To Highest Price", restTemplate);
    }

    private List<Books> sortBooksByPriceHighToLowDB(){
        return apiGetBook("http://localhost:8080/priceSortDesc", "Sort By Highest To Lowest Price", restTemplate);
    }

    private List<Books> searchByYearDB(String year){
        return apiGetBook("http://localhost:8080/booksByYear/", year, "Search By Year", restTemplate);
    }

    private List<Books> searchByAuthorDB(String author){
        return apiGetBook("http://localhost:8080/booksByAuthor/", author, "Search By Author", restTemplate);
    }

    private List<Books> searchByTitleDB(String title){
        return apiGetBook("http://localhost:8080/booksByTitle/", title, "Search By Title", restTemplate);
    }

    private List<Books> searchByISBNDB(String isbn){
        return apiGetBook("http://localhost:8080/booksByISBN/", isbn, "Search By Title", restTemplate);
    }











}
