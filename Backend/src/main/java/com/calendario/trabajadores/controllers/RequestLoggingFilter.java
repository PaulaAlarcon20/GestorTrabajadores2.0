package com.calendario.trabajadores.controllers;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)  // Se ejecuta antes que otros filtros
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String body = new BufferedReader(new InputStreamReader(req.getInputStream()))
                .lines().collect(Collectors.joining("\n"));

        System.out.println("Cuerpo recibido en la solicitud: " + body); // Para verificar si llega vacío

        chain.doFilter(request, response);
    }
}
