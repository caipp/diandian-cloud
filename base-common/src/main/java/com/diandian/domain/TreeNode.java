package com.diandian.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-08
 */
@Getter
@Setter
public class TreeNode implements Serializable,Comparable<TreeNode> {

    private String id;

    private String parent_id;

    private Boolean enable = true;

    private Integer depth;
    
    private int orderNum ;

    private Set<TreeNode> children = new TreeSet<>();

	@Override
	public int compareTo(TreeNode o) {
		// TODO Auto-generated method stub
		if(this.orderNum == o.orderNum) {
			return this.getId().compareTo(o.getId());
		}
		return this.orderNum - o.orderNum;//升序，反之降序
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof TreeNode) {
			return this.orderNum == ((TreeNode)obj).orderNum ? true : false ;
		}
		return false  ;
	}
    
}
