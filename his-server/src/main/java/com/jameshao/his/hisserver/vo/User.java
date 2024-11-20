package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName user
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String realname;

    private Integer usetype;

    private Integer doctitleid;

    private String isscheduling;

    private Integer deptid;

    private Integer registleid;

    private Integer delmark;

    private static final long serialVersionUID = 1L;
}