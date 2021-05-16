package com.example.demospringoracle.model;

import com.example.demospringoracle.entity.Role;
import com.example.demospringoracle.entity.User;
import com.example.demospringoracle.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> _grntdAuths = new ArrayList<>() ;

        List<Role> _roles = null;

        if (user!=null) {
            _roles = user.getRoles();
        }

        if (_roles!=null) {
            for (Role rol : _roles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rol.getName());
                _grntdAuths.add(authority);
            }


        }

        return _grntdAuths;



        // Mặc định mình sẽ để tất cả là ROLE_USER. Để demo cho đơn giản.
       // return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}