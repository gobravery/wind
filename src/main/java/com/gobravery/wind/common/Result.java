package com.gobravery.wind.common;


import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 6288374846131788743L;
    public Result(boolean su){
    	success=su;
    }
    private String message;

    private int statusCode;

    private boolean success=false;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Result() {

    }
}
