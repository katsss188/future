package com.example.future.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Information {
   private String id ;
    String name ;
    char gender;
    String grade;
    String dept ;
    String major ;
    String clasS;
    Integer graduated ;
    Integer goneType ;
    // List<Object>goneTypeList = (List<Object>) hashMap.get("goneTypeList");
    String gone ;
    String comments ;
    String mails ;
    String phone ;
    String wechat ;
    String qq ;
    String others;
    String avatar;


}
