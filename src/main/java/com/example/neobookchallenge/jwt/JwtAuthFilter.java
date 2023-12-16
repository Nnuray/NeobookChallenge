package com.example.neobookchallenge.jwt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component // управляемый контейнер спринг
@RequiredArgsConstructor

public class JwtAuthFilter extends OncePerRequestFilter { // добавили фильтр для одного раза для каждого запроса
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(  // проверка токена для авторизации
                                      @NonNull HttpServletRequest request, // предоставить запрос на сервер
                                      @NonNull HttpServletResponse response, // предоставить ответ для клиента
                                      @NonNull FilterChain filterChain // создержит цепочку дургих фильтров
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); // заголовок
        final String jwt;
        final String userEmail;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){  // проверка заголовка
            filterChain.doFilter(request,response); // переведет по цепочке следуещему фильтру
            return;
        }
        jwt = authHeader.substring(7); // bearer 6 букв и оно удаляяется
        userEmail = jwtService.extractUsername(jwt);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) { // проверка токена
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
