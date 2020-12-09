package com.firecode.app.controller.security;

import com.firecode.app.model.entity.UserEntity;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserSecurity extends User {

    private final UserEntity user;

    public UserSecurity(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

}
