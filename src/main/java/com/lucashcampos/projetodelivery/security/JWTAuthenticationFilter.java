package com.lucashcampos.projetodelivery.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucashcampos.projetodelivery.domain.Usuario;
import com.lucashcampos.projetodelivery.dto.CredenciaisDTO;
import com.lucashcampos.projetodelivery.dto.UsuarioResponseDTO;
import com.lucashcampos.projetodelivery.repositories.UsuarioRepository;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private UsuarioRepository usuarioRepository;

    private JWTUtil jwtUtil;

    // Construtor principal com as dependências necessárias
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UsuarioRepository usuarioRepository) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            CredenciaisDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getEmail(), creds.getSenha(), new ArrayList<>());

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String username = ((UserSS) auth.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        
        res.addHeader("Authorization", "bearer " + token);
        res.addHeader("access-control-expose-headers", "Authorization");

        Usuario cli = usuarioRepository.findByEmail(username);       

        if (cli == null) {
            throw new UsernameNotFoundException(username);
        }        
        
        System.out.println(cli.getLojas().size());
       

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        Map<String, Object> responseBody = new HashMap<>();
        
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(cli, token);
        
        responseBody.put("userDetails", usuarioResponseDTO);
        

        res.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", " +
                    "\"status\": 401, " +
                    "\"error\": \"Não autorizado\", " +
                    "\"message\": \"Email ou senha inválidos\", " +
                    "\"path\": \"/login\"}";
        }
    }
}
