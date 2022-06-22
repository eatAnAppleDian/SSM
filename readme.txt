创建ssm项目流程：
    1.引入依赖
        a.工具类
            测试
            lombok
            数据校验，validator
            日志框架，slf4j,logback
        b.dao层
            数据源
            连接
            mybatis框架
            分页插件
        c.service层
            spring框架
            事务
            aop
            整合mybatis
        d.controller层
            springmvc框架
            jackson
            jsr310
            servlet
    2.配置类
        a.MybatisConfig
            配置数据源
            mapper扫描
        b.SpringConfig
            配置事务
            配置sqlSessionFactory,分页插件
        c.SpringmvcConfig，继承WebMvcConfig
            处理跨域，拦截器，formatter（处理from表达的日期），消息转换器（处理json格式的日期）。
        d.WebConfig
            处理没有xml文件的配置类。
            初始化Spring容器和SpringMVC容器，用于替代web.xml
        注：大多数构建 Web 应用程序的 Spring 用户都需要注册 Spring 的DispatcherServlet
    3.配置文件
        a.mybatis的自动代码生成generatorConfig.xml
        b.数据库连接配置druid.properties
        c.日志文件logback.xml
        d.数据校验配置ValidationMessages.properties
    4.环境搞定开写



