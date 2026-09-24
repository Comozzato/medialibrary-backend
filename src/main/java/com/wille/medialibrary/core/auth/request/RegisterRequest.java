package com.wille.medialibrary.core.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class RegisterRequest {
    
    @NotBlank(message="username is required")
    private String username;
    
    @NotBlank(message="Password is required")
    @Size (min=8, message="Password must have at least 8 characters")
    private String password;

    @NotBlank(message="Email is required")
    @Email (message="Invalid email")
    private String email;

}   


