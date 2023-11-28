package com.example.future.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
* 以下为规定的前后端通信格式
*
*/
@AllArgsConstructor
@Data
public class Result {
    private Integer code;//响应码，1 成功；0 失败
    private String msg;//响应信息
    private Object data;//数据

    public static Result other(int code,Object object){
        return new Result(code,"success",object);
    }
    public static Result success(){
        return new Result(1,"success",null);
    }
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    public static Result error(String msg){
        return new Result(0,msg,null);
    }

}
