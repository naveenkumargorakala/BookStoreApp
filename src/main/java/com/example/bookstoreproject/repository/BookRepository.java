package com.example.bookstoreproject.repository;
import com.example.bookstoreproject.model.BookModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<BookModel,Long> {

    //find by Book Name
    @Query(value = "select * from book_table where book_name LIKE %:bookName% ",nativeQuery = true)
    List<BookModel> findBYBookName(String bookName);

    //sort book data in Ascending order
    @Query(value = "select * from book_table ORDER BY book_name",nativeQuery = true)
    List<BookModel> findAllByOrderByBookName();

    //sort book data in Descending order
    @Query(value = "select * from book_table ORDER BY book_name DESC ",nativeQuery = true)
    List<BookModel> findAllByDescendingOrderByBookName();
}
