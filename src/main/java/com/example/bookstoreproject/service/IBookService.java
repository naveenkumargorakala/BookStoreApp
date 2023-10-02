package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.BookDto;
import com.example.bookstoreproject.model.BookModel;

import java.util.List;

public interface IBookService {
    BookModel insert(BookDto bookDto);

    List<BookModel> getAllBooks();

    BookModel getById(long id);

    String deleteBook(long id);

    List<BookModel> getBookByName(String bookName);

    BookModel updateBook(BookDto bookDto,long id);

    BookModel updateQuantity(long id,int quantity);

    List<BookModel> ascendingOrder();

    List<BookModel> descendingOrder();
}
