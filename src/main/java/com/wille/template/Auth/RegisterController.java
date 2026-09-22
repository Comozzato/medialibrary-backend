package com.wille.template.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wille.template.Auth.Request.RegisterRequest;
import com.wille.template.User.IUserRepository;
import com.wille.template.User.UserDTO;
import com.wille.template.User.UserMapper;
import com.wille.template.User.UserModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class RegisterController {

    private final UserMapper userMapper;
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(
            UserMapper userMapper,
            IUserRepository repository,
            PasswordEncoder passwordEncoder
    ) {
        this.userMapper = userMapper;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        
        System.out.println("ENTROU NO CONTROLLER");
        System.out.println("Username: " + request.getUsername());
        System.out.println("Email: " + request.getEmail());

        UserDTO dto = new UserDTO();

        dto.setUsername(request.getUsername());
        dto.setPassword(request.getPassword());
        dto.setEmail(request.getEmail());
        
        UserModel user = userMapper.toEntity(dto);
        user.setPassword(
            passwordEncoder.encode(dto.getPassword())
        );

        UserModel newUser = repository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDTO(newUser));
    }
}