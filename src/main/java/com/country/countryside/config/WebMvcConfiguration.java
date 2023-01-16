package com.country.countryside.config;

import com.alibaba.fastjson.JSONObject;
import com.country.countryside.config.enums.ErrorCodeEnum;
import com.country.countryside.context.Auth;
import com.country.countryside.context.JwtPayload;
import com.country.countryside.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 配置类
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    @Qualifier(value = "loginInterceptor")
    private HandlerInterceptor loginInterceptor;

    /**
     * 添加文件映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 资源映射路径 addResourceHandler：访问映射路径 addResourceLocations：资源绝对路径
         */
        /*registry.addResourceHandler("/upload/**").addResourceLocations(nginxUrl+"upload/");
        WebMvcConfigurer.super.addResourceHandlers(registry);*/
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**/*.do").excludePathPatterns("/**/getFile.do");
    }

    /**
     * 添加拦截器，拦截未登入用户
     * @return
     */
    @Bean(name = "loginInterceptor")
    public HandlerInterceptor loginInterceptor(){
        return new HandlerInterceptor() {
            /**
             * 登录验证
             */
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                Class<?> clazz = method.getDeclaringClass();
                if(clazz.isAnnotationPresent(Auth.class) || method.isAnnotationPresent(Auth.class)){
                    Cookie[] cookies = request.getCookies();
                    if(cookies == null || cookies.length <= 0){
                        returnJson(ErrorCodeEnum.ERROR_0xbdc30002, response);
                        return false;
                    }
                    for(Cookie cookie : cookies){
                        if(JwtUtils.HEADER_STRING.equalsIgnoreCase(cookie.getName())){
                            JwtPayload jwtPayload = (JwtPayload) JwtUtils.getAuth(cookie.getValue());
                            if(StringUtils.isNotBlank(jwtPayload.getUserId())){
                                return true;
                            }
                        }
                        returnJson(ErrorCodeEnum.ERROR_0xbdc30002, response);
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                                   ModelAndView modelAndView) throws Exception {
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
                    throws Exception {
            }

            /**
             * 判断是否有访问权限
             * @param request
             * @return false:无访问权限，true:可以访问
             */
            private boolean isAccessAuth(HttpServletRequest request){
                return true;
            }
        };
    }

    /**
     * response 返回字符串到前端
     * @param errorCodeEnum
     * @param response
     */
    private void returnJson(ErrorCodeEnum errorCodeEnum, HttpServletResponse response){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.println(JSONObject.toJSONString(errorCodeEnum));
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
