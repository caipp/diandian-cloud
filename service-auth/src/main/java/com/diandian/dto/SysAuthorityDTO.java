package com.diandian.dto;

import com.diandian.domain.BaseDTO;
import lombok.Data;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2018-03-23
 */
@Data
public class SysAuthorityDTO extends BaseDTO{

    private String code;

    private String name;

    private String comment;
}
