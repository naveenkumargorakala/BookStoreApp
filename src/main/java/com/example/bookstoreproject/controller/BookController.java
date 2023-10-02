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
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService iBookService;
    @Autowired
    Response response;

    //insert details into book
    @PostMapping("/insert")
    public ResponseEntity<Response> insert(@RequestBody BookDto bookDto){
        BookModel bookModel = iBookService.insert(bookDto);
        response.setMessage("Insert Book Data");
        response.setObject(bookModel);
        log.info("Inserting Book Details");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8080/book/getallbooks
    //all users
    @GetMapping("/getallbooks")
    public ResponseEntity<Response> getAllBooks(){
        response.setObject(iBookService.getAllBooks());
        response.setMessage("All Books Details");
        log.info("your are requested to get all books data ");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //get book details by id
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Response> getById(@PathVariable int id){
        response.setMessage(id+"book details");
        response.setObject(iBookService.getById(id));
        log.info("Requested to get book details by id");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Delete book details
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Response> deleteBook(@PathVariable int id){
        String message = iBookService.deleteBook(id);
        response.setMessage("Delete Book ");
        response.setObject(message);
        log.info("Delete book details by id");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //get book details by book name
    @GetMapping("/getbybookname/{bookName}")
    public ResponseEntity<Response> searchBook(@PathVariable String bookName){
        response.setObject(iBookService.getBookByName(bookName));
        response.setMessage(bookName+" Book Details");
        log.info("Search book by name");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //update book by id
    @PutMapping("/updatebook/{id}")
    public ResponseEntity<Response> updateBook(@RequestBody BookDto bookDto, @PathVariable int id){
        BookModel bookModel = iBookService.updateBook(bookDto,id);
        response.setObject(bookModel);
        response.setMessage("update book details");
        log.info("update book details by id");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //update Quantity
    @PutMapping("/updatequantity/{id}")
    public ResponseEntity<Response> updateQuantity(@PathVariable int id, @RequestParam int quantity){
        BookModel book = iBookService.updateQuantity(id,quantity);
        response.setMessage("update Quantity of Book");
        response.setObject(book);
        log.info("Update quantity by id");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Ascending Order
    @GetMapping("/ascendingorderbynames")
    public ResponseEntity<Response> sortAscendingOrder(){
        List<BookModel> ascendingOrderList = iBookService.ascendingOrder();
        response.setObject(ascendingOrderList);
        response.setMessage("Ascending Order");
        log.info("sort books in Ascending order");
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    //Descending Order
    @GetMapping("/descendingorder")
    public ResponseEntity<Response> sortDescendingOrder(){
        List<BookModel> descendingOrderList = iBookService.descendingOrder();
        response.setMessage("Descending Order");
        response.setObject(descendingOrderList);
        log.info("sort books in Descending Order");
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
}
