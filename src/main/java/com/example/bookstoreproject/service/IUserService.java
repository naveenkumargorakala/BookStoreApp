package com.example.bookstoreproject.service;

import com.example.bookstoreproject.dto.LoginDto;
import com.example.bookstoreproject.dto.UserDto;
import com.example.bookstoreproject.model.UserModel;

import java.util.List;

public interface IUserService {
    UserModel register(UserDto dto);
    UserModel getByEmail(String email);
    List<UserModel> allUsers();

    long retrieve(String token);

    UserModel updateUser(UserDto userDto, String email, String token);

    String deleteUser(long id);

    String login(LoginDto loginDto);

    String forgotPassword(String email,String password);

    String changePassword(String email, String oldPassword, String newPassword);
}
