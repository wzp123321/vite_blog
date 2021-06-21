package com.java.blog.utils;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.java.blog.common.exception.TokenCheckException;
import org.springframework.ui.ModelMap;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    /**
     * token秘钥，请勿泄露，请勿随便修改 backups:zpwan_blog_manager
     */
    public static final String SECRET = "zpwan_blog_manager";
    /**
     * token 过期时间: 1小时
     */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 1;

    /**
     * JWT生成Token.<br/>
     *
     * @param user_id 登录成功后用户user_id, 参数user_id不可传空
     */
    public static String createToken(Integer user_id, String name) {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "Service") // payload
                .withClaim("aud", "APP").withClaim("user_id",
                        null == user_id ? null : user_id)
                .withClaim("user_name",
                        null == name ? null : name)
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature
        return token;
    }

    /**
     * 解密Token
     */
    public static Map<String, Claim> verifyToken(String token) {
        if (null == token) {
            // token 校验失败, 抛出Token验证非法异常
            throw new TokenCheckException("token不存在或已失效！");
        }
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // token 校验失败, 抛出Token验证非法异常
            throw new TokenCheckException("token不存在或已失效！");
        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取user_id
     *
     * @param token
     * @return user_id
     */
    public static ModelMap getTokenInfo(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim user_id_claim = claims.get("user_id");
        Claim user_name_claim = claims.get("user_name");
        if (null == user_id_claim) {
            throw new TokenCheckException("token不存在或已失效！");
        }
        if (null == user_name_claim || StringUtils.isEmpty(user_name_claim.asString())) {
            throw new TokenCheckException("token不存在或已失效！");
        }
        String newToken = createToken(user_id_claim.asInt(), user_name_claim.asString());
        ModelMap modelMap = new ModelMap();
        modelMap.put("user_id", user_id_claim.asInt());
        modelMap.put("user_name", user_name_claim.asString());
        modelMap.put("token", newToken);
        return modelMap;
    }
}
