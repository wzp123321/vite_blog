package com.java.blog.blogserver;

import com.auth0.jwt.interfaces.Claim;
import com.java.blog.utils.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class BlogserverApplicationTests {

    @Test
    void contextLoads() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiIyMzIzIiwiaXNzIjoiU2VydmljZSIsImV4cCI6MTYyMDM1MzA5MiwiaWF0IjoxNjIwMjY2NjkyfQ.v47m2o6-DMML0r06L_swptCUwyjg-oIiGLnm0UzTiDk";
        Map<String, Claim> map = TokenUtils.verifyToken(token);
        System.out.println("map" + map.get("user_id").asInt() + "---" + map.get("user_name").asString());
    }

}
