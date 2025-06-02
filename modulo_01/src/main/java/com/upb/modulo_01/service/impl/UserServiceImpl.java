package com.upb.modulo_01.service.impl;

import com.upb.modulo_01.entity.MyUser;
import com.upb.modulo_01.repository.UserRepository;
import com.upb.modulo_01.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<MyUser> findById(Long id){
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MyUser> findByUserIdToValidateSession(Long id) {
        return this.repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MyUser> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
