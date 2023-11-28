package com.example.future.service;


import com.example.future.pojo.Account;
import com.example.future.pojo.Result;

public interface LoginService {
    public Object Login(String contact, String password);
    public Object LoginM(String contact, String password);
}
