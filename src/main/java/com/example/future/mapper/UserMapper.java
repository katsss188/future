package com.example.future.mapper;


import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*name,

*/
@Mapper
public interface UserMapper {

    @Select("select " +
            "name, avatar,gender,grade,dept,major,class,goneType,gone,wechat,qq,phone,mail,others,comments " +
            "from table_total,student_confirm " +
            " where table_total.id = student_confirm.id ")
    public List<HashMap<String,Object>> getTable(int page,int size);

    @Select("select " +
            " name,avatar,gender,grade,dept,major,class,goneType,gone,wechat,qq,phone,mail,others,comments " +
            "from table_total,student_confirm " +
            " where table_total.id = student_confirm.id " +
            "order by grade desc" +
            " limit #{page},#{size}")
    public List<HashMap<String,Object>> getTableDesc(int page,int size);



    @Select("select " +
            " name,avatar,gender,grade,dept,major,class,goneType,gone,wechat,qq,phone,mail,others,comments " +
            "from table_total,student_confirm " +
            " where table_total.id = student_confirm.id " +
            "order by grade asc " +
            " limit #{page},#{size} ")
    public List<HashMap<String,Object>> getTableAsc(int page,int size);

    @Select("select count(*) from table_total,student_confirm " +
            " where table_total.id = student_confirm.id ")
    public Integer getTableNum();

    @Select("select " +
            "count(*) " +
            "from table_total ,student_confirm  " +
            " where table_total.id = student_confirm.id and (student_confirm.name like concat('%',#{search},'%') " +
            " or table_total.goneType like concat('%',#{search},'%')  ) ")
    public Integer getTableSearchNum(String search);


    @Select("select " +
            "name, avatar,gender,grade,dept,major,class,goneType,gone,wechat,qq,phone,mail,others,comments " +
            "from table_total ,student_confirm  " +
            " where table_total.id = student_confirm.id and (student_confirm.name like concat('%',#{search},'%') " +
            " or table_total.goneType like concat('%',#{search},'%')  ) " +
            "order by grade asc " +
            " limit #{page},#{size} ")
    public List<HashMap<String,Object>> getTableSearchAsc(Integer page, Integer size,String search);

    @Select("select " +
            "name, avatar,gender,grade,dept,major,class,goneType,gone,wechat,qq,phone,mail,others,comments " +
            "from table_total,student_confirm " +
            " where table_total.id = student_confirm.id and (student_confirm.name like concat('%',#{search},'%') " +
            " or table_total.goneType like concat('%',#{search},'%')  ) " +
            "order by grade desc" +
            " limit #{page},#{size}")
    public List<HashMap<String,Object>> getTableSearchDesc(Integer page, Integer size,String search);


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
    @Select("select name,gender,grade,dept,major,class,graduated,goneType,gone,comments," +
            " mail,phone,wechat,qq,others,avatar from table_total,student_confirm " +
            " where table_total.id = student_confirm.id and table_total.id = #{id}")
    public HashMap<String,Object>getUserInformation(String id);

    @Update("update student_confirm,table_total" +
            " set name = #{name}," +
            " gender = #{name}," +
            " grade = #{grade}," +
            " dept = #{dept}," +
            " major = #{major}," +
            " clasS = #{clasS}," +
            " graduated = #{graduated}," +
            " goneType = #{goneType}," +
            " gone = #{gone}," +
            " comments = #{comments}," +
            " mail = #{mail}," +
            " phone = #{phone}, " +
            " wechat = #{wechat}, " +
            " qq = #{qq}," +
            " others = #{others}, " +
            " avatar = #{avatar}," +
            " password = #{password} " +
            "where student_confirm.id = table_total.id " +
            "and student_confirm.id = #{id}")
    void updateUserInformation(String id, String name, char gender, String grade,
                               String dept, String major, String clasS,
                               Integer graduated, Integer goneType, String gone,
                               String comments, String mails, String phone,
                               String wechat, String qq, String others, String avatar,
                               String password);


    @Select("select grade from student_confirm " +
            " group by grade")
    ArrayList<String> getGrade();

    @Select("select dept from  student_confirm " +
            "group by  dept")
    ArrayList<String> getDept();

    @Select("select major from student_confirm " +
            "where  dept = #{str} " +
            " group by major")
    List<String> getMajor(String str);

    @Select("select goneType,count(table_total.id) from table_total,student_confirm " +
            " where  table_total.id = student_confirm.id " +
            "and grade = #{grade} and dept = #{dept} and major = #{major}" +
            " group by goneType")
    Object getGoneType(String grade, String dept, String major);

    @Select("select gone,count(table_total.id) from table_total,student_confirm " +
            " where  table_total.id = student_confirm.id " +
            "and grade = #{grade} and dept = #{dept} and major = #{major}" +
            " group by gone")
    Object getGone(String grade, String dept, String major);



    @Select("select count(*) from table_total where id = #{id}")
    Integer searchIdByTotal(String id);

    @Delete("delete from table_total where id = #{id} ")
    void deleteInfor(String id);

    @Insert("insert into student_confirm values (#{id},#{name},#{gender}, " +
            " #{grade},#{dept},#{major},#{clasS})")
    void addStudent(String id, String name, char gender, String grade, String dept, String major, String clasS);

    @Insert("insert into table_total(id,password) values (#{id},#{password})")
    void addTotal(String id, String password);


    @Select("select count(*) from student_confirm where id = #{id}")
    Integer searchIdByStudent(String id);

    @Update("update manager set name = #{name},avatar = #{avatar} where id = #{id}")
    void updateManagerInformation(String id, String name, String avatar);
}
