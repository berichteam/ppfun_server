package com.pipi.ums.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lazyb
 * @create 2019/6/11
 * @desc JWT生成token与解码token
 **/
public class JwtTokenUtils {

    private static String TOKEN_KEY = "keys";

    public static String createToken(String username) throws Exception {
        // Token产生时间点
        DateTime now = DateTime.now();

        // 设置过期时间
        DateTime expireDate = now.plusHours(2);

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("username", username)
                .withExpiresAt(expireDate.toDate())      // 过期时间
                .withIssuedAt(now.toDate())        // 签发时间
                .sign(Algorithm.HMAC256(TOKEN_KEY));        // 加密签名算法
        return token;
    }

    public static Map<String, Claim> vertifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_KEY)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("token无效");
        }
        return jwt.getClaims();
    }

}
