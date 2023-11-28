package com.example.future.service;

import com.example.future.pojo.Result;

import java.util.HashMap;
import java.util.Map;

public interface UserService {
    public Object getTable(HashMap<String,Object> param);
    public  Object getUserInformation(String id);
    public Object updateUserInformation(Map<String,Object> hashMap);
    public Object getAllTable();
    public Object getStat(HashMap<String,Object> hashMap);
}
