package com.diandian.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-11-20
 */
@Entity
@Table(name = "sys_authority")
@Data
public class SysAuthority extends BaseEntity {
    @Column(nullable = false, length = 30)
    private String code;

    @Column(nullable = false, length = 30)
    private String name;

    private String comment;
}
