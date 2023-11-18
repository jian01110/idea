package com.software.springboot.utils;

import com.software.springboot.vo.Result;

/**
 * @Author: hugx
 * @Date: 2023-10-30 15:22
 */
public class ResultVOUtil {

    public static<T>  Result success(T data) {
        Result<T> result = new Result<T>();
        result.setCode("200");
        result.setMsg("成功");
        result.setData(data);
        return  result;
    }
}
