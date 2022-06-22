package com.orange.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.io.File;
import java.util.EnumSet;

/**
 * 初始化Spring容器和SpringMVC容器，用于替代web.xml
 * 这个类不是Spring配置类，它被支持Servlet3+的Web容器在启动时加载
 * @author orange
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {


    /**
     * 注册Web组件，这里以字符集过滤器为例
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 首先要触发父类的onStartup，保证先初始化父类中的Web组件
        super.onStartup(servletContext);

        // 创建字符集过滤器对象
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        // 设置字符集
        encodingFilter.setEncoding("UTF-8");
        // 添加到Web容器（这不是Spring的ioc容器，而是ServletContainer）
        //FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("characterEncodingFilter", encodingFilter);
        FilterRegistration.Dynamic filterRegistration = registerServletFilter(servletContext, encodingFilter);
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        filterRegistration.addMappingForUrlPatterns(disps, true, "/*");
    }

    /**
     * 在DispatchServlet初始化完成后，可以更改设置
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);

        //配置对multipart的支持
        // 临时文件的路径
        String location = "G:\\temp";
        File file = new File(location);
        if(!file.exists() && !file.isDirectory()){
            file.mkdir();
        }
        // 上传参数
        long maxFileSize = 2097152;
        long maxRequestSize = 4194304;
        int fileSizeThreshold = 0;
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location,
                maxFileSize, maxRequestSize, fileSizeThreshold);
        registration.setMultipartConfig(multipartConfigElement);

        //设置404异常处理的参数，即有404异常时让DispatchServlet抛出throwExceptionIfNoHandlerFound
        registration.setInitParameter("throwExceptionIfNoHandlerFound","true");
    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringmvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
