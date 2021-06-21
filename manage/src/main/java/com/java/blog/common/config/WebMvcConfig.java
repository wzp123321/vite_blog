package com.java.blog.common.config;

import com.alibaba.fastjson.JSON;
import com.java.blog.common.exception.CommonOperationException;
import com.java.blog.common.exception.FileUploadException;
import com.java.blog.common.exception.TokenCheckException;
import com.java.blog.vo.ReturnResultVo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/", "file:E:/images/"};

    /**
     * 处理全局异常
     *
     * @param exceptionResolvers
     */
    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        System.out.println("exceptionResolvers--------" + exceptionResolvers);
        exceptionResolvers.add((request, response, handler, e) -> {
            ReturnResultVo returnResultVo = new ReturnResultVo();
            System.out.println("e------------" + e);
            if (e instanceof CommonOperationException
                    | e instanceof TokenCheckException | e instanceof FileUploadException | e instanceof MissingServletRequestParameterException
                    | e instanceof MissingServletRequestPartException) {
                returnResultVo = new ReturnResultVo(200, "FAIL", e.getMessage());
            } else {
                returnResultVo = new ReturnResultVo(500, "服务器内部异常！", false);
            }
            responseResult(response, returnResultVo);
            return new ModelAndView();
        });
    }

    public void responseResult(HttpServletResponse response, ReturnResultVo returnResultVo) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(returnResultVo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解决静态资源加载
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
    }
}
