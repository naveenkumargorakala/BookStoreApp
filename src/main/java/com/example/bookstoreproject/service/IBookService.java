package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.BookDto;
import com.example.bookstoreproject.model.BookModel;

import java.util.List;

public interface IBookService {
    BookModel insert(BookDto bookDto);

    List<BookModel> getAllBooks();

    BookModel getById(int id);

    String deleteBook(int id);

    List<BookModel> getBookByName(String bookName);

    BookModel updateBook(BookDto bookDto,int id);

    BookModel updateQuantity(int id,int quantity);

    List<BookModel> ascendingOrder();

    List<BookModel> descendingOrder();
}
