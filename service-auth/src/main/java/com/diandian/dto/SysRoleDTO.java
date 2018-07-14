package com.diandian.dto;

import com.diandian.domain.BaseDTO;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2018-03-23
 */
@Data
public class SysRoleDTO extends BaseDTO{

    private String code;

    private String name;

    private String comment;
}
