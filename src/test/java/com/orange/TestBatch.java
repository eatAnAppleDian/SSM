package com.orange;

import com.orange.config.SpringConfig;
import com.orange.config.WebConfig;
import com.orange.dao.UserMapper;
import com.orange.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class})
public class TestBatch {
    @Autowired
    private SqlSessionFactoryBean sqlSessionFactoryBean;
    @Test
    public void test01() throws Exception {
        SqlSession sqlSession = sqlSessionFactoryBean.getObject().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = Arrays.asList(new User(11L, "lisi01", "123", LocalDateTime.now(), LocalDateTime.now())
                , new User(100L, "lisi02", "123", LocalDateTime.now(), LocalDateTime.now()));
        int i = mapper.batchAddUser(users);
        System.out.println(i);
    }
}
