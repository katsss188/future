package com.example.future.service;

import java.util.Map;

public interface ManagerService {

    public Object deleteInfor(String id);
    public Object addStudent (Map<String,Object> param);
    public Object updateInfor(Map<String,Object> param);
}
