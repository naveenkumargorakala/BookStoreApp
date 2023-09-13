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

    @Autowired
    Response response;

    //register user to bookStore
    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody UserDto dto){
        String user = iUserService.register(dto);
        response.setMessage("User Registered");
        response.setObject(user);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //get by email
    @GetMapping("/getbyemail/{email}")
    public ResponseEntity<Response> getByEmail(@PathVariable String email,@RequestHeader String token){
        UserModel user = iUserService.getByEmail(email,token);
        response.setObject(user);
        response.setMessage(" get by email");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //get all persons data
    @GetMapping("/getallusers")
    public ResponseEntity<Response> getAllUsers(){
        List<UserModel> userList= iUserService.allUsers();
        response.setMessage("All Users");
        response.setObject(userList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //retrieve person details by token
    @GetMapping("/retrieve")
    public ResponseEntity<Response> retrieve(@RequestHeader String token){
        UserModel user = iUserService.retrieve(token);
        response.setObject(user);
        response.setMessage("User details");
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    //update user details
    @PutMapping("/updatebytoken/{email}")
    public ResponseEntity<Response> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String email, @RequestHeader String token){
        UserModel user = iUserService.updateUser(userDto,email,token);
        response.setMessage("Update details");
        response.setObject(user);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //delete user details
    @DeleteMapping("/deletebytoken/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable int id, @RequestHeader String token){
        String message = iUserService.deleteUser(id,token);
        response.setObject(message);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //user login
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginDto loginDto){
        String loginUser = iUserService.login(loginDto);
        response.setObject(loginUser);
        response.setMessage("Successfully Loggedin");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    //Forgot password
    @PutMapping("/forgotpassword/{email}")
    public ResponseEntity<Response> forgotPassword(@PathVariable String email, @RequestParam String password){
        String message = iUserService.forgotPassword(email,password);
        response.setMessage("Changed Password Successfully");
        response.setObject(message);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    //Change Password
    @PutMapping("/changepassword")
    public ResponseEntity<Response> changePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword){
        String message = iUserService.changePassword(email,oldPassword,newPassword);
        response.setObject(message);
        response.setMessage("changed Password Successfully");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
