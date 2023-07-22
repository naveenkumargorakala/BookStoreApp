package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.BookDto;
import com.example.bookstoreproject.exception.ExceptionClass;
import com.example.bookstoreproject.model.BookModel;
import com.example.bookstoreproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    BookRepository bookRepository;

    //insert book details
    public BookModel insert(BookDto bookDto){
        BookModel book = new BookModel(bookDto);
        return bookRepository.save(book);
    }

    //get all books
    public List<BookModel> getAllBooks(){
        List<BookModel> bookList = bookRepository.findAll();
        return bookList;
    }

    //get book by id
    public BookModel getById(int id){
        BookModel book = bookRepository.findById(id).get();
        if(book!=null) {
            return book;
        }else
            throw new ExceptionClass(id+"id is not available to get data");
    }

    //delete book by id
    public String deleteBook(int id){
        boolean isBook= bookRepository.existsById(id);
        if(isBook){
            bookRepository.deleteById(id);
            return "book details are removed";
        }
        else
            throw new ExceptionClass("there is  no book with id "+id+" to delete");
    }

    //get book by book name
    public List<BookModel> getBookByName(String bookName){
        List<BookModel> book = bookRepository.findBYBookName(bookName);
        if(book!=null)
            return book;
        else
            throw new ExceptionClass(bookName+" to get bookdetails");
    }

    //update book by id
    public BookModel updateBook(BookDto bookDto,int id){
        BookModel book = bookRepository.findById(id).get();
        if(book != null){
//            BookModel bookModel = new BookModel(id,bookDto);
            book.setBookName(bookDto.getBookName());
            book.setAuthorName(bookDto.getAuthorName());
            book.setBookDescription(bookDto.getBookDescription());
            book.setBookImg(bookDto.getBookImg());
            book.setPrice(bookDto.getPrice());
            book.setQuantity(bookDto.getQuantity());
            bookRepository.save(book);
            return book;
        }
        else
            throw new ExceptionClass("id is Not Available to Update");
    }

    //update Quantity
    public BookModel updateQuantity(int id,int quantity){
        BookModel book = bookRepository.findById(id).get();
        if(book!=null) {
            book.setQuantity(quantity);
            bookRepository.save(book);
            return book;
        }
        else
            throw new ExceptionClass(id+"id not available to update quantity");
    }

    //Ascending order
    public List<BookModel> ascendingOrder(){
        List<BookModel> list = bookRepository.findAllByOrderByBookName();
        return list;
    }

    //Descending Order
    public List<BookModel> descendingOrder(){
        List<BookModel> list = bookRepository.findAllByDescendingOrderByBookName();
        return list;
    }
}
