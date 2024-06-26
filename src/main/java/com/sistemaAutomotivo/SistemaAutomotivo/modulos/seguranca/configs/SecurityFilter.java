package com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.repositories.UsuarioRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {
            var userName = tokenService.validarToken(token);
            UserDetails usuario = usuarioRepository.findByNomeUsuario(userName);

            System.out.println("\nnomeUsuario: " +userName+ "\n");
            System.out.println("\nUsuario: " +usuario+ "\n");
            
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            System.out.println("\nAutenticacao: " +authentication+ "\n");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    public String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        System.out.println("\nTOKEN: " +authHeader+ "\n");
        if (authHeader == null) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }

}
