package com.mmall.demo2.service;

import com.mmall.demo2.mapper.UserMapper;
import com.mmall.demo2.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        //查user的时候将role/permission一并查出来
        return userMapper.findByUsername(username);
    }
}
