package com.software.springboot.controller;

import com.software.springboot.entity.Book;
import com.software.springboot.entity.User;
import com.software.springboot.entity.UserInfo;
import com.software.springboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: hugx
 * @Date: 2023-10-23 14:54
 */
// RestController = Controller + ResponseBody
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println(user.getUsername()+":"+user.getPassword());
        return "success";
    }
    @GetMapping("/userList")
    public List<UserInfo> getUserInfoList(String userName) {
        return userInfoService.getUserInfoList(userName);
    }
    @GetMapping("/getUserInfoById")
    public UserInfo getUserInfoById(String userId) {
        return userInfoService.getUserInfoById(userId);
    }
}
