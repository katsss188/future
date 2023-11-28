package com.example.future.controller;


import com.example.future.pojo.Result;
import com.example.future.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/table")
    public Object getTable(@RequestBody Map<String,Object> param){


        return userService.getTable((HashMap<String, Object>) param);
    }

    //初始时获取用户信息
    @RequestMapping("/getinfo")//设为get
    public Object getUserInformation(@RequestBody Map<String,Object> param){
        //仅传入一个String id
        String id = (String) param.get("id");
       return  userService.getUserInformation( id);
    }
    @RequestMapping("/updateinfo")//设为get
    public Object updateUserInformation(@RequestBody Map<String,Object> param){
        return  userService.updateUserInformation( param);
    }

    //垃圾功能
    @RequestMapping("/list")//设为get
    public Object getAllTable(){
        return  userService.getAllTable( );
    }

    @RequestMapping("/stat")//设为get
    public Object getStat(@RequestBody Map<String,Object> param){
        return  userService.getStat((HashMap<String, Object>) param);
    }


}
