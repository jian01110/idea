package com.software.springboot.controller;

import cn.hutool.crypto.digest.MD5;
import com.software.springboot.entity.Book;
import com.software.springboot.entity.ResponseObject;
import com.software.springboot.entity.User;
import com.software.springboot.entity.UserInfo;
import com.software.springboot.service.OpenAIService;
import com.software.springboot.service.UserInfoService;
import com.software.springboot.utils.ResultVOUtil;
import com.software.springboot.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/call-openai")
    public String callOpenAI(String prompt) {
        return openAIService.callOpenAI(prompt);
    }
    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {
        System.out.println("账户为"+user.getUsername()+":"+user.getPassword());
        String key = user.getUsername() + UUID.randomUUID();
        String token = cn.hutool.crypto.digest.MD5.create().digestHex16(key);
        return ResultVOUtil.success(token);
    }
    @PostMapping("img")
    public Result<String> img(@RequestParam("file") MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        File temp = new File("E:\\file\\");
        // 获取原始文件的索引
        Integer endIndexOf = name.lastIndexOf(".");
        // 上传文件后的后缀名
        String endFile = name.substring(endIndexOf, name.length());
        // 新名字
        String newName = UUID.randomUUID().toString() + endFile;
        String path = "E:\\file\\" + newName;
        System.out.println(path);
        File localFile = new File(path);
        file.transferTo(localFile);
        return ResultVOUtil.success(path);
    }
    @GetMapping ("ioimg")
    public void getImage(HttpServletResponse response) throws IOException {

            File files = new File("E:\\file\\cd26304e-e349-4160-a77d-1a40c26ccfe2.png");
            if (files.exists()) {
                // 设置响应头，告诉浏览器这是一个图片文件
                response.setContentType("image/jpeg"); // 根据文件类型设置正确的Content-Type
                // 以流的方式将文件内容写入响应
                FileInputStream inputStream = new FileInputStream(files);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.flush();
            }
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
