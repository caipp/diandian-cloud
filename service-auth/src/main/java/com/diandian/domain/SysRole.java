package com.diandian.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * @author caipiaoping
 */
@Entity
@Table(name = "sys_role")
@Data
public class SysRole extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String code;

    @Column(nullable = false, length = 30)
    private String name;

    private String comment;

    @ManyToMany(targetEntity = SysAuthority.class,fetch = FetchType.LAZY)
    private Set<SysAuthority> authorities = new HashSet<>();

    @ManyToMany(targetEntity = SysResource.class, fetch = FetchType.LAZY)
    @JoinTable(name = "m_role_resource", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    private Set<SysResource> resources = new HashSet<>();

}
