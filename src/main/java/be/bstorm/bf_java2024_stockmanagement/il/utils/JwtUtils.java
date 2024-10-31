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

/**
 * Utility class for generating, parsing, and validating JWT tokens in the stock management system.
 * This class uses a secret key for HMAC SHA-256 signing and validates tokens for user authentication.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code SECRET} - The secret key used for signing JWT tokens.</li>
 * <li>{@code EXPIRATION_TIME} - The token expiration time in seconds (1 day).</li>
 * <li>{@code builder} - The {@link JwtBuilder} for constructing JWT tokens.</li>
 * <li>{@code parser} - The {@link JwtParser} for parsing and validating tokens.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #generateToken(User)} - Generates a JWT token for a given user.</li>
 * <li>{@link #getUsername(String)} - Extracts the username from a token.</li>
 * <li>{@link #getUserId(String)} - Extracts the user ID from a token.</li>
 * <li>{@link #isValid(String)} - Validates the token’s issuance and expiration dates.</li>
 * </ul>
 * </p>
 */
@Component
public class JwtUtils {

    /**
     * The secret key used for signing JWT tokens.
     */
    private static final String SECRET = "Yabadabadooooooooooooooooooooooooooooooooooooooooooooo";

    /**
     * The token expiration time in seconds (1 day).
     */
    private static final long EXPIRATION_TIME = 86400L; // 1 Day

    private final JwtBuilder builder;
    private final JwtParser parser;

    /**
     * Constructs a {@code JwtUtils} instance, initializing the JWT builder and parser with the secret key.
     */
    public JwtUtils() {
        SecretKey secretKey = new SecretKeySpec(SECRET.getBytes(), "HmacSHA256");
        this.builder = Jwts.builder().signWith(secretKey);
        this.parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    /**
     * Generates a JWT token for a given user, embedding the user’s ID and username, with an expiration date.
     *
     * @param user The {@link User} for whom the token is generated.
     * @return A JWT token as a {@link String}.
     */
    public String generateToken(User user) {
        return this.builder
                .setSubject(user.getUsername())
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000))
                .compact();
    }

    /**
     * Parses a JWT token and retrieves its claims.
     *
     * @param token The JWT token to parse.
     * @return The {@link Claims} extracted from the token.
     */
    private Claims parseToken(String token) {
        return this.parser.parseClaimsJws(token).getBody();
    }

    /**
     * Extracts the username from the provided token.
     *
     * @param token The JWT token from which to extract the username.
     * @return The username as a {@link String}.
     */
    public String getUsername(String token) {
        return parseToken(token).getSubject();
    }

    /**
     * Extracts the user ID from the provided token.
     *
     * @param token The JWT token from which to extract the user ID.
     * @return The user ID as a {@link UUID}.
     */
    public UUID getUserId(String token) {
        return parseToken(token).get("id", UUID.class);
    }

    /**
     * Validates the token’s issuance and expiration dates.
     *
     * @param token The JWT token to validate.
     * @return {@code true} if the token is currently valid; {@code false} otherwise.
     */
    public boolean isValid(String token) {
        Claims claims = parseToken(token);
        Date now = new Date();
        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}
