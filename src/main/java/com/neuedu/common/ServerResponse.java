package com.neuedu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse <T>{
    private int status;
    private T data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    //无参构造
    public ServerResponse() {
    }
    //
    public ServerResponse(int status) {
        this.status = status;
    }
    public ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
    public ServerResponse(int status,  String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServerResponse(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }


/*
 *调用接口成功时回调
 * */
    public static ServerResponse serverResponseBySuccess(){
        return new ServerResponse(ResponseCode.SUCESS);
    }
    public static <T> ServerResponse serverResponseBySuccess(T data){
        return new ServerResponse(ResponseCode.SUCESS,data);
    }
    public static <T> ServerResponse serverResponseBySuccess(T data ,String msg){
        return new ServerResponse(ResponseCode.SUCESS,data,msg);
    }


/**
 * 调用接口失败*/


    public static ServerResponse serverResponseByError(){
        return new ServerResponse(ResponseCode.ERROR);
    }
    public static ServerResponse serverResponseByError(String msg){
        return new ServerResponse(ResponseCode.ERROR,msg);
    }
    public static ServerResponse serverResponseByError(int status){
        return new ServerResponse(status);
    }
    public static ServerResponse serverResponseByError(int status,String msg){
        return new ServerResponse(status,msg);
    }


/**
 * 判断接口是否正确返回*/
    @JsonIgnore
    public boolean isSsuccess(){
        return this.status==ResponseCode.SUCESS;
    }

}
