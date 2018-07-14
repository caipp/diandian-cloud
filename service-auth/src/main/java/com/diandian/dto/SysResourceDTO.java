package com.diandian.dto;

import com.diandian.domain.BaseDTO;
import com.diandian.enums.ResourceType;
import lombok.Data;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2018-03-23
 */
@Data
public class SysResourceDTO extends BaseDTO{

    private SysResourceDTO parent;

    private ResourceType type;

    private String code;

    private String address;

    private String icon;

    private String name;

    private String comment;
}
