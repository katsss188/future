package com.example.future.service.imp;

import com.example.future.mapper.UserMapper;
import com.example.future.pojo.Result;
import com.example.future.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.crypto.digest.DigestUtil;

import java.util.Map;

@Service
public class ManagerServiceA implements ManagerService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object deleteInfor(String id) {
       if( userMapper.searchIdByTotal(id) != null){
           userMapper.deleteInfor( id);
           return Result.success();
       }else
            return Result.error("此账号未在主表中激活");
    }

    @Override
    public Object addStudent(Map<String, Object> param) {
        String id = (String)param.get("id");
        String name = (String)param.get("name");
        char gender = (char) param.get("gender");
        String grade = (String) param.get("grade");
        String dept  = (String) param.get("dept");
        String major = (String) param.get("major");
        String clasS = (String) param.get("class");
        if(userMapper.searchIdByStudent(id) != null){
            return Result.error("信息重复");
        }
        else{
            String password = DigestUtil.sha256Hex(id.substring(4));
             userMapper.addStudent(id,name,gender,grade,dept,major,clasS);
             userMapper.addTotal(id,password);
             return Result.success();
        }

    }

    @Override
    public Object updateInfor(Map<String, Object> param) {
        String id = (String)param.get("id");
        String name = (String)param.get("name");
        String avatar = (String) param.get("avatar");
        userMapper.updateManagerInformation(id,name,avatar);
        return Result.success();
    }
}
