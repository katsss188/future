package com.example.future.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Register {
    private String id;
    private String name;
    private String password;
}
