package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName register
 */
@Data
public class Register implements Serializable {
    private Integer id;

    private String casenumber;

    private String realname;

    private Integer gender;

    private String idnumber;

    private Date birthdate;

    private Integer age;

    private String agetype;

    private String homeaddress;

    private Date visitdate;

    private String noon;

    private Integer deptid;

    private Integer userid;

    private Integer registleid;

    private Integer settleid;

    private String isbook;

    private Date registtime;

    private Integer registerid;

    private Integer visitstate;

    private static final long serialVersionUID = 1L;
}