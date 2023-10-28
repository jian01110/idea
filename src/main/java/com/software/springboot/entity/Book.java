package com.software.springboot.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: hugx
 * @Date: 2023-10-26 8:13
 */
@Component
//@ConfigurationProperties(prefix = "book")
@PropertySource("classpath:bookinfo.properties")
public class Book {
    @Value("${book.name}")
    private String name;
    @Value("${book.writer}")
    private String writer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
