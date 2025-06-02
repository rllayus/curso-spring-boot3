package com.upb.modulo_01.service;

import com.upb.modulo_01.entity.MyUser;

import java.util.Optional;

public interface UserService {
    Optional<MyUser> findById(Long id);
    Optional<MyUser> findByUserIdToValidateSession(Long id);
    Optional<MyUser> findByUsername(String username);
}
