package com.example.bookstoreproject.controller;

import com.example.bookstoreproject.dto.LoginDto;
import com.example.bookstoreproject.dto.UserDto;
import com.example.bookstoreproject.model.UserModel;
import com.example.bookstoreproject.response.Response;
import com.example.bookstoreproject.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    IUserService iUserService;

    //register user to bookStore
    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody UserDto dto){
        String user = iUserService.register(dto);
        Response response = new Response(user,"registered");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //get by email
    @GetMapping("/getbyemail/{email}/{token}")
    public ResponseEntity<Response> getByEmail(@PathVariable String email, @PathVariable String token){
        UserModel user = iUserService.getByEmail(email,token);
        Response response = new Response(user,"Data");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //get all persons data
    @GetMapping("/getallusers")
    public ResponseEntity<List<Response>> getAllUsers(){
        List<UserModel> userList= iUserService.allUsers();
        Response response = new Response(userList,"All Users in BookStore");
        return new ResponseEntity<>(response.getList(), HttpStatus.OK);
    }

    //retrieve person details by token
    @GetMapping("/retrieve/{token}")
    public ResponseEntity<Response> retrieve(@PathVariable String token){
        UserModel user = iUserService.retrieve(token);
        Response response = new Response(user,"User details, get by token");
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    //update user details
    @PutMapping("/updatebytoken/{email}/{token}")
    public ResponseEntity<Response> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String email, @PathVariable String token){
        UserModel user = iUserService.updateUser(userDto,email,token);
        Response response = new Response(user,"Details are Updated");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //delete user details
    @DeleteMapping("/deletebytoken/{id}/{token}")
    public ResponseEntity<Response> deleteUser(@PathVariable int id, @PathVariable String token){
        String message = iUserService.deleteUser(id,token);
        Response response = new Response(message,"Remove User Details");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //user login
    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<Response> login(@RequestBody LoginDto loginDto){
        String loginUser = iUserService.login(loginDto);
        Response response = new Response(loginUser,"Login");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    //Forgot password
    @PutMapping("/forgotpassword/{email}")
    public ResponseEntity<Response> forgotPassword(@PathVariable String email, @RequestParam String password){
        String message = iUserService.forgotPassword(email,password);
        Response response = new Response(message,"Forgot Password");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    //Change Password
    @PutMapping("/changepassword/{email}/{oldPassword}")
    public ResponseEntity<Response> changePassword(@PathVariable String email, @PathVariable String oldPassword, @RequestParam String newPassword){
        String message = iUserService.changePassword(email,oldPassword,newPassword);
        Response response = new Response(message,"Password Change");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
