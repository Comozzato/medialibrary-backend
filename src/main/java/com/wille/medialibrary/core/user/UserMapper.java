package com.wille.medialibrary.core.user;

import org.springframework.stereotype.Component;

@Component 
public class UserMapper {
    

    public UserModel toEntity(UserDTO dto)
    {
        UserModel user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }

    public UserDTO toDTO(UserModel model)
    {
        UserDTO dto = new UserDTO();

        dto.setUsername(model.getUsername());
        dto.setEmail(model.getEmail());

        return dto;
    }
}
