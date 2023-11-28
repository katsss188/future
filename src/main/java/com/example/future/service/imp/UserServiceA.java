package com.example.future.service.imp;

import com.example.future.mapper.UserMapper;
import com.example.future.pojo.Result;
import com.example.future.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceA implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Override
    public Object getTable(HashMap<String, Object> param) {
        Integer page = (Integer) param.get("page");
        Integer itemsPerPage  = (Integer) param.get("itemsPerPage");
        HashMap<String,String> sortBy = (HashMap<String, String>) param.get("sortBy");
        String search = (String) param.get("search");

        System.out.println(page.toString()+"666");
        System.out.println(itemsPerPage.toString()+"666");

        if(page != null && itemsPerPage != null){
            if(search == null) {
                if (sortBy.get("order").equals("grade") && sortBy.get("key").equals("asc")) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("code", 1);
                    hashMap.put("data", userMapper.getTableAsc(page, itemsPerPage));
                    hashMap.put("pageCount", Math.round(userMapper.getTableNum() * 1.0 / itemsPerPage) );
                    return hashMap;
                } else if (sortBy.get("order").equals("grade") && sortBy.get("key").equals("desc")) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("code", 1);
                    hashMap.put("data", userMapper.getTableDesc(page, itemsPerPage));
                    hashMap.put("pageCount", Math.round(userMapper.getTableNum() * 1.0 / itemsPerPage));
                    return hashMap;
                }
            }
            else {
                if (sortBy.get("order").equals("grade") && sortBy.get("key").equals("asc")) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("code", 1);
                    hashMap.put("data", userMapper.getTableSearchAsc(page, itemsPerPage,search));
                    hashMap.put("pageCount", Math.round(userMapper.getTableSearchNum(search) * 1.0 / itemsPerPage));
                    return hashMap;
                } else if (sortBy.get("order").equals("grade") && sortBy.get("key").equals("desc")) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("code", 1);
                    hashMap.put("data", userMapper.getTableSearchDesc(page, itemsPerPage,search));
                    hashMap.put("pageCount", Math.round(userMapper.getTableSearchNum(search) * 1.0 / itemsPerPage));
                    return hashMap;
                }
            }
        }
         return Result.error("数据缺失");
    }

    //获取个人信息
    @Override
    public Object getUserInformation(String id) {
      HashMap<String,Object> param = userMapper.getUserInformation(id);
        if(param.size() > 0){
            ArrayList<String> list = new ArrayList<>();
            list.add("考研已上岸");
            list.add("考研准备中");
            list.add("推免");
            list.add("考公已上岸");
            list.add("考公准备中");
            list.add("求职中");
            list.add("企业就业");
            list.add("出国出境深造");
            list.add("其它");
            param.put("goneTypeList",list);
            return Result.success(param);
        }

        return Result.error("查无此人");
    }

    //●name 姓名
    //●gender 性别
    //●grade 年级
    //●dept 院系
    //●major 专业
    //●class 班级
    //●graduated 是否已毕业，true为是，false为否
    //●goneType 去向类型
    //●goneTypeList 去向类型列表，可以按照总表中给出的去向类型来提供
    //●gone 去向单位
    //●comments 备注
    //●mail 邮箱
    //●phone 电话
    //●wechat 微信
    //●qq
    //●others 其他联系方式
    //●avatar 头像（一个URL）
    @Override
    public Object updateUserInformation(Map<String, Object> hashMap) {
        String id = (String)hashMap.get("id");
        String name = (String) hashMap.get("name");
        char gender = (char) hashMap.get("gender");
        String grade = (String) hashMap.get("grade");
        String dept = (String) hashMap.get("dept");
        String major = (String) hashMap.get("major");
        String clasS = (String)  hashMap.get("class");//bug警告
        Integer graduated = (Integer) hashMap.get("graduated");//待定
        Integer goneType = (Integer) hashMap.get("goneType");
       // List<Object>goneTypeList = (List<Object>) hashMap.get("goneTypeList");
        String gone = (String) hashMap.get("gone");
        String comments = (String) hashMap.get("comments");
        String mails  = (String) hashMap.get("mails");
        String phone = (String) hashMap.get("phone");
        String wechat = (String) hashMap.get("wechat");
        String qq = (String) hashMap.get("qq");
        String others = (String) hashMap.get("others");
        String avatar = (String) hashMap.get("avatar");
        String password = (String) hashMap.get("password");

       if( !userMapper.getUserInformation(id).isEmpty() ) {
           userMapper.updateUserInformation(id,name,gender,grade,dept,major,clasS,graduated,goneType,gone,comments,mails,phone,wechat,qq,others,avatar,password);
       }

        return Result.error("查无此人");
    }

    @Override
    public Object getAllTable() {
        HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("grade",userMapper.getGrade());
        ArrayList<String> list = userMapper.getDept();
        HashMap<String,List<String> > hashMap1 = new HashMap<>();
        for (String str: list
             ) {
          hashMap1.put(str,userMapper.getMajor(str));
        }
        hashMap.put("dept",hashMap1);
        return Result.success(hashMap);
    }

    @Override
    public Object getStat(HashMap<String,Object> hashMap) {

        if((Integer) hashMap.get("mode") == 1){
           HashMap<String,String> hashMap1 = (HashMap<String, String>) hashMap.get("data");
           String grade = hashMap1.get("grade");
           String dept  = hashMap1.get("dept");
           String major = hashMap1.get("major");
           HashMap<String,Object> hashMap2 = new HashMap<>();
           hashMap2.put("type",userMapper.getGoneType(grade,dept,major));
           hashMap2.put("gone",userMapper.getGone(grade,dept,major));
           return Result.success(hashMap2);
        }
        return Result.error("模式异常");
    }


}
