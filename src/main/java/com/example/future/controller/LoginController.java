package com.example.future.controller;


import com.example.future.pojo.Account;
import com.example.future.pojo.Result;
import com.example.future.service.LoginService;
import com.example.future.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//如名，登陆部分
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping("/user")
    public Object login(@RequestBody Map<String,Object> param) throws Exception{
        String contact = (String)param.get("id");
        String password = (String)param.get("password");
        Object infor = loginService.Login(contact,password);

        if(infor != null){
            return infor;
         /*   }else
            {
                claims.put("account",contact);
                claims.put("type","manager");
                String jwt = JwtUtils.generateJwt(claims,2);
                ret.put("jwt",jwt);
                return new Result(1,"manager",ret);
            }*/

        }
        return Result.other(3,"未知异常");
    }

    @RequestMapping("/manager")
    public Object loginM(@RequestBody Map<String,Object> param) throws Exception{
        String contact = (String)param.get("id");
        String password = (String)param.get("password");
        Object infor = loginService.LoginM(contact,password);

        if(infor != null){
            return infor;
         /*   }else
            {

            }*/

        }
        return Result.other(3,"未知异常");
    }
}
