package com.software.springboot.entity;

public class ResponseObject {
    private String status;
    private Object data;

    public ResponseObject(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    // Getters and setters for 'status' and 'data'
}