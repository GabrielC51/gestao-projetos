package com.gestaoprojetos.gestaoprojetos.security;

import com.gestaoprojetos.gestaoprojetos.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, 
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;

        System.out.println("=== JWT Filter Debug ===");
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Auth Header: " + authHeader);

        // Verificar se o header Authorization contém um token Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Remove "Bearer " do início
            System.out.println("Token extraído: " + token.substring(0, Math.min(20, token.length())) + "...");
            
            try {
                email = jwtUtil.getEmailFromToken(token);
                System.out.println("Email extraído do token: " + email);
            } catch (Exception e) {
                System.out.println("Erro ao extrair email: " + e.getMessage());
                logger.error("Erro ao extrair email do token JWT", e);
            }
        }

        // Se temos um email e não há autenticação no contexto
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("Tentando autenticar usuário: " + email);
            
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                System.out.println("UserDetails carregado. Authorities: " + userDetails.getAuthorities());
                
                // Validar o token
                boolean isValid = jwtUtil.validateToken(token);
                boolean isNotExpired = !jwtUtil.isTokenExpired(token);
                System.out.println("Token válido: " + isValid);
                System.out.println("Token não expirado: " + isNotExpired);
                
                if (isValid && isNotExpired) {
                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("Autenticação configurada com sucesso!");
                } else {
                    System.out.println("Token inválido ou expirado");
                }
            } catch (Exception e) {
                System.out.println("Erro na autenticação: " + e.getMessage());
                logger.error("Erro na autenticação JWT", e);
            }
        } else {
            System.out.println("Email nulo ou autenticação já existe no contexto");
        }

        System.out.println("=== Fim JWT Filter Debug ===");
        filterChain.doFilter(request, response);
    }
}
