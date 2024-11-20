package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName department
 */
@Data
public class Department implements Serializable {
    private Integer id;

    private String deptcode;

    private String deptname;

    private Integer deptcategoryid;

    private Integer depttype;

    private Integer delmark;

    private static final long serialVersionUID = 1L;
}