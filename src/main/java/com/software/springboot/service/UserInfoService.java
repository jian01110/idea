package com.software.springboot.service;

import com.software.springboot.entity.UserInfo;
import com.software.springboot.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: hugx
 * @Date: 2023-10-26 9:03
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository repository;

    public List<UserInfo> getUserInfoList(String userName) {
        List<UserInfo> userInfoList = repository.findAll();
        return userInfoList;
    }

    public UserInfo getUserInfoById(String userId) {
        Optional<UserInfo> optional = repository.findById(userId);
        UserInfo userInfo = optional.get();
        return userInfo;
    }
}
