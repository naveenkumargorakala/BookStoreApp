package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.LoginDto;
import com.example.bookstoreproject.dto.UserDto;
import com.example.bookstoreproject.model.UserModel;

import java.util.List;

public interface IUserService {
    String register(UserDto dto);
    UserModel getByEmail(String email, String token);
    List<UserModel> allUsers();

    UserModel retrieve(String token);

    UserModel updateUser(UserDto userDto, String email, String token);

    String deleteUser(int id, String token);

    String login(LoginDto loginDto);

    String forgotPassword(String email,String password);

    String changePassword(String email, String oldPassword, String newPassword);
}
