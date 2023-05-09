package io.mxm.testers.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.mxm.testers.domains.Identity;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class JwtUtils {

    private final String SECRET = "CT3DUq9CZg3xHJ0P1@3iC3&sFLhiWRhq";

    public String generateToken(Identity user) {
        // after 15 minutes expires
        long EXPIRED = 900_000L;

        return "Bearer " + Jwts.builder().setSubject(user.getSubject())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

    }


    public String generateToken(Identity user,Long EXPIRED) {
        // after one year expires
         //(900_000*4*26*365)34164000000

        return "Bearer " + Jwts.builder().setSubject(user.getSubject())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

    }

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();

    }

}
//
//class m {
//    public static void main(String[] args) {
//        System.out.println((900_000L*4L*26L*365L));
//    }
//}