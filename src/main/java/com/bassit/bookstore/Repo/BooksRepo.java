package com.bassit.bookstore.Repo;

import com.bassit.bookstore.Models.Books;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.LongSummaryStatistics;

public interface BooksRepo extends JpaRepository<Books, Long> {

    List<Books> findAllByOrderByReleaseYearDesc();

    List<Books> findAllByOrderByReleaseYearAsc();

    List<Books> findAllByOrderByAuthor();

    List<Books> findAllByOrderByIsbn();

    List<Books> findAllByOrderByTitle();

    List<Books> findAllByOrderByPriceAsc();

    List<Books> findAllByOrderByPriceDesc();

    List<Books> findAllByAuthor(String author);

    List<Books> findAllByTitle(String title);

    List<Books> findAllByIsbn(String isbn);

    List<Books> findAllByPriceLessThanEqualOrderByPriceAsc(double price);

    List<Books> findAllByPriceGreaterThanEqualOrderByPriceAsc(double price);

//    @Query("SELECT b FROM Books b where b.releaseYear = :year")
//    List<Books> findBooksByReleaseYear(@Param("year") String year);

    List<Books> findBooksByReleaseYear(String year);

    @Modifying
    @Transactional
    @Query("UPDATE Books SET price = :price WHERE id = :id")
    void priceUpdate(@Param("price") Double price, @Param("id") long id);


}
