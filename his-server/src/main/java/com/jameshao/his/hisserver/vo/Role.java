package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName role
 */
@Data
public class Role implements Serializable {
    private Long id;

    private String name;

    private String code;

    private Integer sort;

    private Integer status;

    private Integer dataScope;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}