package com.example.future.controller;

import com.example.future.service.ManagerService;
import com.example.future.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/users")//get
    public Object getTable(@RequestBody Map<String,Object> param){


        return userService.getTable((HashMap<String, Object>) param);
    }

    @RequestMapping("/delete")
    public Object deleteInfor(@RequestBody Map<String,Object> param){
        return managerService.deleteInfor((String) param.get("id"));
    }

    @RequestMapping("/add")
    public  Object addStudent(@RequestBody Map<String,Object> param){
        return managerService.addStudent(param);
    }

    @RequestMapping("/updateinfo")
    public Object updateInfor(@RequestBody Map<String,Object> param){
        return managerService.updateInfor(param);
    }
}
