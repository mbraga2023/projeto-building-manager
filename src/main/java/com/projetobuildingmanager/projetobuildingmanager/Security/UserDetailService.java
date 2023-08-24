package com.projetobuildingmanager.projetobuildingmanager.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService extends UserDetailsService {
    
    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
