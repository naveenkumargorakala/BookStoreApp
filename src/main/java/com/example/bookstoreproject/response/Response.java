package com.example.bookstoreproject.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@Component
public class Response {
    private String message;
    private Object object;

}
