package be.bstorm.bf_java2024_stockmanagement.il.configs;

import be.bstorm.bf_java2024_stockmanagement.il.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * A filter that intercepts HTTP requests to authenticate users based on a JWT token.
 * This filter is executed once per request and validates the token present in the Authorization header.
 * If the token is valid, it retrieves the user details and sets the security context.
 *
 * <p>Dependencies:
 * <ul>
 * <li>{@code JwtUtils} - Utility class for validating and extracting information from JWT tokens.</li>
 * <li>{@code UserDetailsService} - Service for loading user details by username.</li>
 * </ul>
 * </p>
 *
 * @see JwtUtils
 * @see UserDetailsService
 */
@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    /**
     * Utility for JWT token validation and information extraction.
     */
    private final JwtUtils jwtUtils;

    /**
     * Service for retrieving user details.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Filters each HTTP request to check for a JWT in the Authorization header.
     * If a valid JWT token is found, it authenticates the user and sets the security context.
     *
     * @param request The HTTP request being filtered.
     * @param response The HTTP response associated with the request.
     * @param filterChain The filter chain allowing the request to proceed.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs during filtering.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);

            if (jwtUtils.isValid(token)) {
                String username = jwtUtils.getUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken upt = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getUsername(),
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(upt);
            }
        }

        filterChain.doFilter(request, response);
    }
}
