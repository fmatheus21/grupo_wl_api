package com.firecode.app.controller.security;

import com.firecode.app.controller.handler.AppExceptionHandler;
import com.firecode.app.model.entity.UserEntity;
import com.firecode.app.model.service.UserService;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    
     private static final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);

    @Autowired
    private UserService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = usuarioService.findByUsername(username);
        UserEntity usuario = optional.orElseThrow(() -> new UsernameNotFoundException(null));
        log.info(username);
        return new UserSecurity(usuario, this.authorities(usuario));
    }

    private Collection<? extends GrantedAuthority> authorities(UserEntity usuario) {
        Set<SimpleGrantedAuthority> authoritys = new HashSet<>();
        usuario.getPermissions().forEach(p -> authoritys.add(new SimpleGrantedAuthority(p.getName().toUpperCase())));
        return authoritys;
    }

}
