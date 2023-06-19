package com.gideon.spring_security_jwt.service;

import java.util.List;

import com.gideon.spring_security_jwt.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.gideon.spring_security_jwt.entity.User;

public interface UserService extends UserDetailsService, UserDetailsPasswordService{

    /**
     * @return list User
     */
    List<SecurityUser> getUsers();

    /**
     * @param user user object
     * @return user saved or updated
     */
    SecurityUser save(SecurityUser user);
    
}
