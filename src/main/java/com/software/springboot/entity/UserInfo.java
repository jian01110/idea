package com.software.springboot.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: hugx
 * @Date: 2023-10-26 8:54
 * @Content: UserInfo 对应表是user_info
 */
@Entity
//@Table(name = "user_info")
@Data
public class UserInfo {
    @Id
    private String userId;
    //@Column(name = "user_name")
    private String userName;
    private String password;
    private String avatar;
}
