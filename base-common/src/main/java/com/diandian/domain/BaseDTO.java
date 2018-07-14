package com.diandian.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2018-03-22
 */
@Data
public abstract class BaseDTO implements Serializable {

    private String id;

    private String createBy;

    private Date createAt;

    private String updateBy;

    private Date updateAt;

    private Boolean enable;
}
