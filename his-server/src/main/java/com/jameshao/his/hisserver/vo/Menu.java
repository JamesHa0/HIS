package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName menu
 */
@Data
public class Menu implements Serializable {
    private Integer id;

    private Long parentId;

    private String treePath;

    private String name;

    private Integer type;

    private String routeName;

    private String routePath;

    private String component;

    private String perm;

    private Integer alwaysShow;

    private Integer keepAlive;

    private Integer visible;

    private Integer sort;

    private String icon;

    private String redirect;

    private Date createTime;

    private Date updateTime;

    private Object params;

    private static final long serialVersionUID = 1L;
}