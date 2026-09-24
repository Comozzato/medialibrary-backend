package com.wille.medialibrary.core.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    Optional <UserModel> findByUsername(String username);   
}
