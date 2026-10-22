package com.apigateway_service.apigateway_service.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import com.apigateway_service.apigateway_service.security.JwtService;

import java.util.function.Function;


@Component
public class JwtAuthenticationFilter 
        implements Function<ServerRequest, ServerRequest> {


    private final JwtService jwtService;


    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    public ServerRequest apply(ServerRequest request) {


        String authHeader =
                request.headers()
                .firstHeader("Authorization");


        if(authHeader == null ||
                !authHeader.startsWith("Bearer ")) {


            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Missing JWT token"
            );
        }


        String token =
                authHeader.substring(7);


        if(!jwtService.validateToken(token)) {


            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid JWT token"
            );
        }


        return request;
    }
} 