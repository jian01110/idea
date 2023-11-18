package com.software.springboot.vo;

import lombok.Data;

/**
 * @Author: hugx
 * @Date: 2023-10-30 15:11
 */
@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;
}
