package com.jiahe.jiahecloudspringboot.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    public static String createToken(Map map){
        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");
        String token = "";
        token = JWT.create()
                .withHeader(header)
                .withClaim("logId", map.get("logId").toString())
                .withClaim("logName", map.get("logName").toString())
                .sign(Algorithm.HMAC256("jiahe"));
        return token;
    }

    public static String getLogIdFromToken(String token, String type){
        Algorithm algorithm = Algorithm.HMAC256("jiahe");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        if("logId".equals(type))
            return jwt.getClaim("logId").asString();
        else
            return jwt.getClaim("logName").asString();
    }
}
