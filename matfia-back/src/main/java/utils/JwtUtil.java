package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import static common.Constants.SECRET;

public class JwtUtil {
    // 보통 곱하기로 표현한다.
    private static final int EXPIRATION_TIME = 30 * 60 * 1000;

    public static String generateToken(String id,int idx) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("idx", idx);

        //클라이언트에게 전달할 토큰
        String token = Jwts.builder()
                //sub
                .setClaims(claims)
                //만든 시간
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                //암호화 방식 key 는 216 bit 이상
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return token;
    }

    //클라이언트에게 전달받은 토큰이 정상적인지 확인하는 코드
    public static boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("ExpiredJwtException");
            return false;
        }

        return true;
    }

    public static int get(String token) {
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.get("idx", Integer.class);
        }catch (ExpiredJwtException e){
            System.out.println("ExpiredJwtException");
        }

        return 0;
    }
}
