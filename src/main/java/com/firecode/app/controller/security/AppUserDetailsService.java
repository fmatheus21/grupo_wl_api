package com.firecode.app.controller.security;

import com.firecode.app.model.entity.UserEntity;
import com.firecode.app.model.service.UserService;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = usuarioService.findByUsername(username);
        UserEntity usuario = optional.orElseThrow(() -> new UsernameNotFoundException(null));
        return new UserSecurity(usuario, this.authorities(usuario));
    }

    private Collection<? extends GrantedAuthority> authorities(UserEntity usuario) {
        Set<SimpleGrantedAuthority> authoritys = new HashSet<>();
        usuario.getPermissions().forEach(p -> authoritys.add(new SimpleGrantedAuthority(p.getName().toUpperCase())));
        return authoritys;
    }

}
