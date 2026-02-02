package com.viajes_transporte.proyecto.config.filter;

import java.io.IOException;
import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.viajes_transporte.proyecto.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {

            jwtToken = jwtToken.substring(7);

            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            String username = jwtUtils.extractUsername(decodedJWT);

            String authorities =
                    jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

            Collection<GrantedAuthority> authoritiesList =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authoritiesList);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
