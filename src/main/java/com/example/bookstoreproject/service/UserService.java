package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.LoginDto;
import com.example.bookstoreproject.dto.UserDto;
import com.example.bookstoreproject.exception.ExceptionClass;
import com.example.bookstoreproject.model.UserModel;
import com.example.bookstoreproject.repository.UserRepository;
import com.example.bookstoreproject.util.JMSMailSender;
import com.example.bookstoreproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository repository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    JMSMailSender jmsMailSender;

    //registered user into bookstore
    public String register(UserDto dto) {
        UserModel user = new UserModel(dto);
        repository.save(user);
        String token = tokenUtil.encodeToken(user.getUserID());
        jmsMailSender.mailSender(user.getEmail(),"Registerd to BookStore","Hello "+user.getFirstName()+"\n Your details are Uploaded into BookStore..... \n You can check it by click on below link\n http://localhost:8080/user/retrieve/"+token);
        return token;
    }


    //get user details by email
    public UserModel getByEmail(String email, String token){
            int id = tokenUtil.decodeToken(token);
            UserModel userModel = repository.findById(id).get();
            if(userModel.getEmail().equals(email)) {
                UserModel user = repository.findByEmail(email);
                return user;
            }
            else
                throw new ExceptionClass(email+" email is not available to update user details");
        }



    //get all users details
    public List<UserModel> allUsers() {
        List<UserModel> listOfUsers = repository.findAll();
        return listOfUsers;
    }

    //retrieve user details
    public UserModel retrieve(String token){
        int user_id = tokenUtil.decodeToken(token);
        UserModel user = repository.findById(user_id).get();
        return user;
    }



    //delete user details
    public String deleteUser(int id, String token){
        int userId = tokenUtil.decodeToken(token);
        if(userId == id ){
            UserModel user = repository.findById(id).get();
            jmsMailSender.mailSender(user.getEmail(),"Remove Details","Hello "+user.getFirstName()+"\n Your details are Deleted from BookStore..... \n You can check it by click on below link\n http://localhost:8080/user/retrieve/"+token);
            repository.deleteById(id);
            return id+" Details are removed";
        }
        else {
            throw new ExceptionClass("id and details are not matched...");
        }
    }


    //login
    public String login(LoginDto loginDto){
        UserModel user = repository.findByEmail(loginDto.getEmail());
        if(user.getPassword().equals(loginDto.getPassword())){
            jmsMailSender.mailSender(user.getEmail(),"Login","Hello" +user.getFirstName()+"\n You are logged into BookStore..... ");
            return "Login successfully...";

        }
        else
            throw new ExceptionClass("Not Valid details...");
    }


    //forgot password
    public String forgotPassword( String email,String newPassword) {
        boolean isEmail = repository.existsByEmail(email);
        if(isEmail) {
            UserModel user = repository.findByEmail(email);
            user.setPassword(newPassword);
            repository.save(user);
            jmsMailSender.mailSender(user.getEmail(), "Forgot Password ", "Hello " + user.getFirstName() + "\n You are changed Password to " +user.getPassword());
            return "password changed Successfully...";
        }
        else
            throw new ExceptionClass(email+" is not available");
    }

    //update user details
    public UserModel updateUser(UserDto userDto, String email, String token) {
        int id = tokenUtil.decodeToken(token);
        UserModel user1 = repository.findById(id).get();
        if (user1.getEmail().equals(email)) {
            UserModel user = repository.findByEmail(email);
            System.out.println(user + " this");
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setAddress(userDto.getAddress());
            user.setDate(userDto.getDate());
            user.setEmail(userDto.getEmail());
            repository.save(user);
            jmsMailSender.mailSender(user.getEmail(), "You details are Updated", "Hello " + user.getFirstName() + "\n Your details are Updated into BookStore..... \n You can check it by click on below link\n http://localhost:8080/user/retrieve/" + token);
            return user;
        }
        else
            throw new ExceptionClass(email+"email is not available to update");
    }

    //change Password
    public String changePassword(String email, String oldPassword, String newPassword){
        UserModel user =repository.findByEmail(email);
        if(user != null){
            if(user.getPassword().equals(oldPassword)){
                user.setPassword(newPassword);
                repository.save(user);
                jmsMailSender.mailSender(user.getEmail(),"Password changed Successfully","Hello "+user.getFirstName()+"\n You are changing password is Successfully \n");
                return "password Changed SuccessFully";
            }
            else
                return "Password and Email are Not Matched";
        }
        else
            throw new ExceptionClass(email+"Email does not exist");
    }
}
