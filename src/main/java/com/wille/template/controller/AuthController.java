package com.wille.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wille.template.dto.request.LoginRequest;
import com.wille.template.security.JwtTokenService;

@RestController 
public class AuthController {
    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/auth/login")
    public String login(@RequestBody LoginRequest loginRequest) {
      // Lógica para validar o usuário (no exemplo, apenas um nome de usuário simples)
      if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
          return jwtTokenService.generateToken(loginRequest.getUsername());
      }
      throw new RuntimeException("Credenciais inválidas");
    }

}

