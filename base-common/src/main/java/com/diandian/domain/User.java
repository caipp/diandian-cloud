package com.diandian.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.*;


/**
 * @author caipiaoping
 */
@Setter
@Getter
public class User implements UserDetails {
    public String id;

    public String username;

    private String password;

    private Date lastPasswordReset;

    private String wxOpenId;

    private String nikeName;

    private String avatarUrl;

    private String gender;

    private boolean enabled;

    private Set<GrantedAuthority> authorities;

    public User(
            String id,
            String username,
            String password,
            Date lastPasswordReset,
            String wxOpenId,
            String nikeName,
            String avatarUrl,
            String gender,
            boolean enabled ,
            Set<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastPasswordReset = lastPasswordReset;
        this.wxOpenId = wxOpenId;
        this.nikeName = nikeName;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return enabled;
    }
}
