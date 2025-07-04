package com.fpt.backend.jpa;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpt.backend.entity.User;

public interface UserJpa  extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email AND u.id <> :userId")
    boolean existsByEmailAndNotUserId( Integer userId, String email);
    

}
