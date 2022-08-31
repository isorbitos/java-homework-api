package com.homework.restapi.service;

import com.homework.restapi.model.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {
    private final JwtUserService jwtUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JwtUser user = jwtUserService.getJwtUserByEmail(email);

        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, user.getAuthorities());
    }
}
