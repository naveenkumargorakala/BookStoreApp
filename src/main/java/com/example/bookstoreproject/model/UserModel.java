package com.example.bookstoreproject.model;

import com.example.bookstoreproject.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class UserModel {

    @Id
    @GeneratedValue
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Date date;
    private String password;

    public UserModel(UserDto userDto) {
        this.firstName =userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.address = userDto.getAddress();
        this.date = userDto.getDate();
        this.password = userDto.getPassword();
    }

}
