package com.upb.modulo_01.repository;

import com.upb.modulo_01.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<MyUser, Long> {
}
