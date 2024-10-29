package com.blog_application.app.BlogApplication.utlis;

public class ApiResponse<T>{
    private String message;
    private T responseObject;

    public ApiResponse(String _message, T _responseObject){
        this.message = _message;
        this.responseObject = _responseObject;
    }


    // Getters and Setters
    public String getMessage(){
        return message;
    }

    public void setMessage(String _message){
        this.message = _message;
    }

    public T getData(){
        return responseObject;
    }

    public void setData(T _responseObject){
        this.responseObject = _responseObject;
    }
}

