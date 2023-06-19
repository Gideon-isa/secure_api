package com.gideon.spring_security_jwt.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gideon.spring_security_jwt.security.SecurityUser;


public interface UserRepository extends JpaRepository<SecurityUser, Long>{

    Optional<SecurityUser> findByEmail(String useString);

    Optional<SecurityUser> findByUsername(String username);

    List<SecurityUser> findAll();
    
}
