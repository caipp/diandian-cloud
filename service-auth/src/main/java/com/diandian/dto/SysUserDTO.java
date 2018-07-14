package com.diandian.dto;

import com.diandian.domain.BaseDTO;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2018-03-23
 */
@Data
public class SysUserDTO extends BaseDTO{
    @NotBlank(message="用户名不能为空")
    private String username;

    private Date lastPasswordReset;

    private String wxOpenId;

    private String nickname;

    private String avatarUrl;

    private String gender;

}
