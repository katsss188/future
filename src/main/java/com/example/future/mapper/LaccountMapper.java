package com.example.future.mapper;


import com.example.future.pojo.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;

@Mapper
public interface LaccountMapper {

    @Select("select password ,avatar,name from table_total t,student_confirm s where t.id = s.id and " +
            " s.id = #{contact} ")
    public HashMap<String,Object> AccountJud(String contact);

    @Select("select password ,avatar,name  from manager s where  s.id = #{contact} ")
    public HashMap<String,Object> AccountJudM(String contact);
    /*@Select("select count(*)  from table_total where id = #{contact} ")
    public Account loginTry(String contact, String password);*/



}
