package com.example.bookstoreproject.controller;

import com.example.bookstoreproject.dto.BookDto;
import com.example.bookstoreproject.model.BookModel;
import com.example.bookstoreproject.response.Response;
import com.example.bookstoreproject.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService iBookService;

    //insert details into book
    @PostMapping("/insert")
    public ResponseEntity<Response> insert(@RequestBody BookDto bookDto){
        BookModel bookModel = iBookService.insert(bookDto);
        Response bookResponse = new Response(bookModel,"Insert Book Data");
        log.info("Inserting Book Details");
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    //all users
    @GetMapping("/getallbooks")
    public ResponseEntity<List<Response>> getAllBooks(){
        List<BookModel> bookList = iBookService.getAllBooks();
        Response bookResponse = new Response(bookList,"All Books","You BookStore Details");
        log.info("your are requested to get all books data ");
        return new ResponseEntity<>(bookResponse.getList(),HttpStatus.FOUND);
    }

    //get book details by id
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Response> getById(@PathVariable int id){
        BookModel book = iBookService.getById(id);
        Response bookResponse = new Response(book,id+"book details");
        log.info("Requested to get book details by id");
        return new ResponseEntity<>(bookResponse,HttpStatus.FOUND);
    }

    //Delete book details
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        String message = iBookService.deleteBook(id);
        Response bookResponse = new Response(message,"Delete Book ");
        log.info("Delete book details by id");
        return new ResponseEntity<>(bookResponse.toString(),HttpStatus.OK);
    }

    //get book details by book name
    @GetMapping("/getbybookname/{bookName}")
    public ResponseEntity<List<Response>> searchBook(@PathVariable String bookName){
        List<BookModel> book = iBookService.getBookByName(bookName);
        Response bookResponse = new Response(book,bookName+"book details","BookNames");
        log.info("Search book by name");
        return new ResponseEntity<>(bookResponse.getList(),HttpStatus.FOUND);
    }

    //update book by id
    @PutMapping("/updatebook/{id}")
    public ResponseEntity<Response> updateBook(@RequestBody BookDto bookDto, @PathVariable int id){
        BookModel bookModel = iBookService.updateBook(bookDto,id);
        Response bookResponse = new Response(bookModel,"update Book details");
        log.info("update book details by id");
        return new ResponseEntity<>(bookResponse,HttpStatus.OK);
    }

    //update Quantity
    @PutMapping("/updatequantity/{id}")
    public ResponseEntity<Response> updateQuantity(@PathVariable int id, @RequestParam int quantity){
        BookModel book = iBookService.updateQuantity(id,quantity);
        Response bookResponse = new Response(book,"update Quantity");
        log.info("Update quantity by id");
        return new ResponseEntity<>(bookResponse,HttpStatus.OK);
    }

    //Ascending Order
    @GetMapping("/ascendingorderbynames")
    public ResponseEntity<List<Response>> sortAscendingOrder(){
        List<BookModel> ascendingOrderList = iBookService.ascendingOrder();
        Response bookResponse = new Response(ascendingOrderList,"Ascending Order","Order");
        log.info("sort books in Ascending order");
        return new ResponseEntity<>(bookResponse.getList(),HttpStatus.FOUND);
    }

    //Descending Order
    @GetMapping("/descendingorder")
    public ResponseEntity<List<Response>> sortDescendingOrder(){
        List<BookModel> descendingOrderList = iBookService.descendingOrder();
        Response bookResponse = new Response(descendingOrderList,"Descending Order","Order");
        log.info("sort books in Descending Order");
        return new ResponseEntity<>(bookResponse.getList(),HttpStatus.FOUND);
    }
}
