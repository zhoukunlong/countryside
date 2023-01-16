package com.country.countryside.utils;

import com.alibaba.fastjson.JSONObject;
import com.country.countryside.context.JwtPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author zhoukunlong
 * @description:
 * @date 2021/7/23
 */
public class JwtUtils {
    /**
     * 5天
     */
    private static final long EXPIRATION_TIME = 432_000_000L;
    /**
     * JWT密码
     */
    private static final String SECRET = "P@ssw02d";

    /**
     * JWT密码
     */
    private static final String PRODUCT_SECRET = "PJWTP@ssw02d";

    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "Login_token_";
    /**
     * 存放Token的Header Key
     */
    public static final String HEADER_STRING = "Authorization";

    /**
     * 存放access Token的Header Key
     */
    public static final String NEW_HEADER_ACCESS_TOKEN = "Auth";

    private static final String SEPARATOR = "_";

    private static final String TOKEN_PRODUCT = TOKEN_PREFIX + "@product" + SEPARATOR;

    /**
     * JWT生成方法
     */
    public static String addAuth(JwtPayload payload) {
        return Jwts.builder()
                //设置主题
                .setSubject(HEADER_STRING)
                .claim("payload", JSONObject.toJSONString(payload))
                // 有效期设置
                //.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * JWT验证方法
     */
    public static Object getAuth(String token) {
        // 从Header中拿到token
        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return claims.get("payload");
        }
        return null;
    }

    /**
     * JWT验证方法
     */
    public static Object getProductAuth(String token) {
        // 从Header中拿到token
        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(PRODUCT_SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return claims.get("payload");
        }
        return null;
    }

    public static void main(String[] args){
        /*JwtPayload load = new JwtPayload();
        load.setUserName("zhoukunlong");
        String jwtStr = addAuth(load);
        System.out.println(jwtStr);*/

        /*String result = (String) getAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRob3JpemF0aW9uIiwicGF5bG9hZCI6IntcInVzZXJJZFwiOlwiemhvdWt1bmxvbmdcIixcInVzZXJOYW1lXCI6XCJ6aG91a3VubG9uZzNjOTY3YzRlLTZhNjUtNGQ3Zi1hNzg1LTk1YjM5NjQ4YmQzN1wifSJ9.yetaQk1q_itbdAPX4liCYRw0Dhfhk7RTYDjbhUd6ALayQFsYE6OLpCP3icNZ5QFcKyNMocjLy_6DFs12rnU2pA");
        JwtPayload load1 = JSON.parseObject(result,JwtPayload.class);
        System.out.println(load1.getUserName());*/
    }

}
