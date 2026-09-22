package com.wille.template.Auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wille.template.Auth.Request.LoginRequest;
import com.wille.template.security.JwtTokenService;

@RestController
@RequestMapping ("/auth")
public class LoginController {

    private final JwtTokenService jwtTokenService;

    public LoginController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {

        if ("user".equals(loginRequest.getUsername())
                && "password".equals(loginRequest.getPassword())) {

            return jwtTokenService.generateToken(loginRequest.getUsername());
        }

        throw new RuntimeException("Credenciais inválidas");
    }
}