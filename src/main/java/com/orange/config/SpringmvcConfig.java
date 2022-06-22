package com.orange.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@Configuration
// 开启springmvc定制配置功能
@EnableWebMvc
@ComponentScan("com.orange.controller")
public class SpringmvcConfig implements WebMvcConfigurer {

    private static final String TIME_ZONE = "GMT+8";
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";


    /**
     * 多部分数据解析
     */
    @Bean
    public MultipartResolver multipartResolver() {
        /* CommonsMultipartResolver */
        return new StandardServletMultipartResolver();
    }

    /**
     * 方法参数或返回值校验
     */
    @Bean
    public MethodValidationPostProcessor validationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    /**
     * 配置日期
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //todo 配置日期格式转换（MVC来处理，只能处理表单提交）
        //配置Date
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.setPattern("yyyy-MM-dd HH:mm:ss");
        registry.addFormatter(dateFormatter);

        // 配置JDK8的新日期API
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm:ss"));
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        registrar.registerFormatters(registry);
    }

    /**
     * 配置数据校验
     */
    @Override
    public Validator getValidator() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // 读取配置文件的编码格式
        messageSource.setDefaultEncoding("utf-8");
        // 缓存时间，-1表示不过期
        messageSource.setCacheMillis(-1);
        // 配置文件前缀名，设置为Messages,那你的配置文件必须以Messages.properties/Message_en.properties...
        messageSource.setBasename("classpath:ValidationMessages");

        // 校验器工厂对象
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        // 可以不设置，默认会自动查找校验实现类
        factoryBean.setProviderClass(HibernateValidator.class);
        // 消息源
        factoryBean.setValidationMessageSource(messageSource);
        return factoryBean;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 在系统默认注册完成后，才会在这里注册HttpMessageConverter
        // 这里不能直接创建新的MappingJackson2HttpMessageConverter对象添加，会影响swagger-ui使用
        for (HttpMessageConverter<?> converter : converters) {
            //todo Jackson来处理（json方式）日期格式转换
            if (converter instanceof MappingJackson2HttpMessageConverter) {
//                ObjectMapper 提供了从基本 POJO（普通旧 Java 对象）或从通用 JSON 树模型 (JsonNode) 读取
//                和写入 JSON 的功能，以及执行转换的相关功能。它也是高度可定制的，既可以处理不同风格的 JSON 内容，
//                也可以支持更高级的对象概念，例如多态性和对象标识。 ObjectMapper 还充当更高级的 ObjectReader
//                和 ObjectWriter 类的工厂。
//                也就是说objectMapper对象用来处理对象和json的操作；还可以作为工厂，以获得更多的，完备的功能
                ObjectMapper objectMapper = new ObjectMapper();
                // 指定时区
                objectMapper.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
                // 日期类型字符串处理
                objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATETIME_PATTERN));
                //todo 设置参数为null时不序列化
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                // Java8日期日期处理
                JavaTimeModule javaTimeModule = new JavaTimeModule();
                javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN)));
                javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
                javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
                javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN)));
                javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
                javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
                //todo 解决JavaScript无法表示Long类型的问题，因为Long超出了JavaScript中number类型的范围导致精度丢失
                javaTimeModule.addSerializer(Long.class, ToStringSerializer.instance);
                objectMapper.registerModule(javaTimeModule);
                ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper);
            }
        }
    }
}
