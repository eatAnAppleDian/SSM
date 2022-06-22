package com.orange.controller;

import com.orange.dao.UserMapper;
import com.orange.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

@RestController
public class UserController {
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/test")
    public Integer addUser() throws Exception {
        List<User> users = Arrays.asList(new User(11L, "lisi01", "123", LocalDateTime.now(), LocalDateTime.now())
                , new User(100L, "lisi02", "123", LocalDateTime.now(), LocalDateTime.now()));
        int i = userMapper.batchAddUser(users);
        return i;
    }
}
