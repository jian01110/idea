package com.software.springboot.controller;

import com.software.springboot.entity.Book;
import com.software.springboot.entity.ResponseObject;
import com.software.springboot.entity.User;
import com.software.springboot.entity.UserInfo;
import com.software.springboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author: hugx
 * @Date: 2023-10-23 14:54
 */
// RestController = Controller + ResponseBody
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println(user.getUsername()+":"+user.getPassword());
        return "success";
    }
    @PostMapping("img")
    public String img(@RequestParam("file") MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        File temp = new File("E:\\file\\");
        // 获取原始文件的索引
        Integer endIndexOf = name.lastIndexOf(".");
        // 上传文件后的后缀名
        String endFile = name.substring(endIndexOf, name.length());
        // 新名字
        String newName = UUID.randomUUID().toString() + endFile;
        String path = "E:\\file\\" + newName;
        File localFile = new File(path);
        file.transferTo(localFile);
        return path;
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
