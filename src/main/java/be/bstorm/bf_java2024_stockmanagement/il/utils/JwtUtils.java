package be.bstorm.bf_java2024_stockmanagement.il.utils;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    private static final String SECRET = "Yabadabadooooooooooooooooooooooooooooooooooooooooooooo";
    private static final long EXPIRATION_TIME = 86400L; // 1 Day

    private final JwtBuilder builder;
    private final JwtParser parser;

    public JwtUtils() {
        SecretKey secretKey = new SecretKeySpec(SECRET.getBytes(), "HmacSHA256");
        this.builder = Jwts.builder().signWith(secretKey);
        this.parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public String generateToken(User user) {

        return this.builder
                .setSubject(user.getUsername())
                .claim("id",user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000))
                .compact();
    }

    private Claims parseToken(String token) {

        return this.parser.parseClaimsJws(token).getBody();
    }

    public String getUsername(String token) {
        return parseToken(token).getSubject();
    }

    public UUID getUserId(String token) {
        return parseToken(token).get("id", UUID.class);
    }

    public boolean isValid(String token) {
        Claims claims = parseToken(token);
        Date now = new Date();

        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}
