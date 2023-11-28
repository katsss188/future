package com.example.future.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

//预留的读表格式，大概率不会用
@Data
@AllArgsConstructor
public class Record {
    private Integer id;
    private String contact;
    private String type;
    private Timestamp intime;
}
