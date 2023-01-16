package com.country.countryside.common;

import com.country.countryside.context.JwtPayload;
import com.country.countryside.utils.JwtUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 公共工具类
 * @author zhoukunlong
 * @date 2023/01/16
 * @since
 * @see
 */
public class CommonUtils {

    /**
     * 获取用户Id
     * @param request
     * @return
     */
    public static String getUserId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        String token = "";
        for(Cookie cookie : cookies){
            if(JwtUtils.HEADER_STRING.equals(cookie.getName())){
                token = cookie.getValue();
                break;
            }
        }
        if(StringUtils.isEmpty(token)){
            return null;
        }
        JwtPayload jwtPayload = null;
        try {
            jwtPayload = (JwtPayload) JwtUtils.getAuth(token);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return jwtPayload.getUserId();
    }

    /**
     * 获取用户名
     * @param request
     * @return
     */
    public static String getUserName(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        String token = "";
        for(Cookie cookie : cookies){
            if(JwtUtils.HEADER_STRING.equals(cookie.getName())){
                token = cookie.getValue();
                break;
            }
        }
        if(StringUtils.isEmpty(token)){
            return null;
        }
        JwtPayload jwtPayload = null;
        try {
            jwtPayload = (JwtPayload) JwtUtils.getAuth(token);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return jwtPayload.getUserName();
    }
}
