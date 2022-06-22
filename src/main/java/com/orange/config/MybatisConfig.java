package com.orange.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:druid.properties")
@MapperScan("com.orange.dao")
public class MybatisConfig {

    @Value("${mysql.driverName}")
    private String DRIVER_NAME;
    @Value("${mysql.url}")
    private String URL;
    @Value("${mysql.username}")
    private String USERNAME;
    @Value("${mysql.password}")
    private String PASSWORD;

//    配置数据源
    @Bean("dataSource")
    public DataSource getDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(DRIVER_NAME);
        druidDataSource.setUrl(URL);
        druidDataSource.setUsername(USERNAME);
        druidDataSource.setPassword(PASSWORD);
        return druidDataSource;
    }
}
