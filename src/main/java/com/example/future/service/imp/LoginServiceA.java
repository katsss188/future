package com.example.future.service.imp;


import com.example.future.mapper.LaccountMapper;
import com.example.future.pojo.Account;
import com.example.future.pojo.Result;
import com.example.future.service.LoginService;
import com.example.future.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceA implements LoginService {
    @Autowired
    private LaccountMapper laccountMapper;
    @Override
    public Object Login(String contact, String password) {

        HashMap<String,Object> temp = laccountMapper.AccountJud(contact);

        if(temp != null){
            if(temp.get("password").equals(password) ){
                Map<String,Object> ret = new HashMap<>();
                Map<String, Object> claims = new HashMap<>();
                //ret.put("account",infor);

                claims.put("account",contact);
                claims.put("type","user");
                String jwt = JwtUtils.generateJwt(claims,1);


                ret.put("code",0);
                ret.put("msg","登陆成功");
                ret.put("jwt",jwt);
                ret.put("avatar", temp.get("avatar"));
                ret.put("name", temp.get("name"));
                return ret;
            }
            else
                return  Result.other(2,"密码错误");
        }
        else
            return Result.other(1,"账号不存在");

    }

    @Override
    public Object LoginM(String contact, String password) {
        HashMap<String,Object> temp = laccountMapper.AccountJudM(contact);

        if(temp != null){
            if(temp.get("password").equals(password) ){
                Map<String,Object> ret = new HashMap<>();
                Map<String, Object> claims = new HashMap<>();
                //ret.put("account",infor);

                claims.put("account",contact);
                claims.put("type","manager");
                String jwt = JwtUtils.generateJwt(claims,2);

                ret.put("code",0);
                ret.put("msg","登陆成功");
                ret.put("jwt",jwt);
                ret.put("avatar", temp.get("avatar"));
                ret.put("name", temp.get("name"));
                return ret;
            }
            else
                return  Result.other(2,"密码错误");
        }
        else
            return Result.other(1,"账号不存在");
    }
}
