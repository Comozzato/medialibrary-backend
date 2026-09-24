package com.wille.medialibrary.core.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wille.medialibrary.core.auth.request.LoginRequest;
import com.wille.medialibrary.core.user.IUserRepository;
import com.wille.medialibrary.core.user.UserModel;
import com.wille.medialibrary.exception.InvalidCredentialsException;
import com.wille.medialibrary.security.JwtTokenService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private IUserRepository userRepository;

    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {

        UserModel user = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Credenciais inválidas"));

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {
            throw new InvalidCredentialsException("Credenciais inválidas");
        }

        return jwtTokenService.generateToken(user.getUsername());
    }
}
