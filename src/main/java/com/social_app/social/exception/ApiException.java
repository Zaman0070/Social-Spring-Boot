package com.social_app.social.exception;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }

    public ApiException(){
        super();
    }

}
