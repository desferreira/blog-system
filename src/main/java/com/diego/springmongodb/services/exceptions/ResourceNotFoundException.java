package com.diego.springmongodb.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String id){
        super("Resource not found for id "+id);
    }

}
