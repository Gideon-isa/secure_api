package com.gideon.spring_security_jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gideon.spring_security_jwt.entity.User;
import com.gideon.spring_security_jwt.respository.UserRepository;
import com.gideon.spring_security_jwt.security.SecurityUser;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    /**
     * 
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecurityUser> user = getUserByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        detailsChecker.check(user.get());
        return user.get();
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
       return null;
    }

    @Override
    public List<SecurityUser> getUsers() {

        return userRepository.findAll();
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'getUsers'");
    }

    @Override
    public SecurityUser save(SecurityUser user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateKeyException("Email Already exist !!");
        }
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'save'");
        return userRepository.save(user);
    }

    // 
    /**
     * @param usernameValue
     * @return Optional User
     */
    private Optional<SecurityUser> getUserByUsername(String usernameValue) {
        // trim username
        String username = usernameValue.trim();
        if (username.isEmpty()) {
            return Optional.empty();
        }

        return username.contains("@") ? userRepository.findByEmail(username) : userRepository.findByUsername(username);
        
    }
    


}
