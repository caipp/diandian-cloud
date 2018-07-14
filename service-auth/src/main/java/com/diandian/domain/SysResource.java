package com.diandian.domain;

import com.diandian.enums.ResourceType;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * @author caipiaoping
 */
@Entity
@Table(name = "sys_resource")
@Data
public class SysResource extends BaseEntity implements Comparable<SysResource> {

    @Column(nullable = false, length = 30)
    private String code;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private SysResource parent;

    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String icon;

    @Column(nullable = false, length = 30)
    private String name;

    private String comment;
    
    /**
     * 排序字段
     */
    private int orderNum ;

    @ManyToMany(targetEntity = SysRole.class, fetch = FetchType.LAZY)
    @JoinTable(name = "m_role_resource", joinColumns = {@JoinColumn(name = "resource_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<SysRole> roles = new HashSet<>();

	@Override
	public int compareTo(SysResource o) {

		if(this.orderNum == o.orderNum) {
			return this.getId().compareTo(o.getId());
		}
        //升序，反之降序
		return this.orderNum - o.orderNum;
	}
	
	@Override
	public boolean equals(Object obj) {

		if(obj instanceof SysResource) {
			return this.orderNum == ((SysResource)obj).orderNum ? true : false ;
		}
		return false  ;
	}
	
}
