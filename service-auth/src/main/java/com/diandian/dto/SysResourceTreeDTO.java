package com.diandian.dto;


import com.diandian.domain.TreeNode;
import com.diandian.enums.ResourceType;
import lombok.Data;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-08
 */
@Data
public class SysResourceTreeDTO extends TreeNode {

    private SysResourceTreeDTO parent;

    private String code;

    private ResourceType type;

    private String address;

    private String icon;

    private String name;

    private String comment;
}
