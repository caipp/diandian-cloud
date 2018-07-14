package com.diandian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author caipiaoping
 */
@Entity
@Table(name = "sys_user")
@Data
public class SysUser extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Date lastPasswordReset;

    private String wxOpenId;
    @Column(nullable = false)
    private String nickname;

    private String avatarUrl;
    private String gender;


    @JsonIgnore
    @ManyToMany(targetEntity = SysRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "m_user_roles", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<SysRole> roles = new HashSet<>();

    @Transient
    private Set<GrantedAuthority> authorities = new HashSet<>();


    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> userAuthotities = new HashSet<>();
        for(SysRole role : this.roles){
            for(SysAuthority sysAuthority : role.getAuthorities()){
                userAuthotities.add(new SimpleGrantedAuthority(sysAuthority.getCode()));
            }
        }

        return userAuthotities;
    }

}
