package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName constantitem
 */
@Data
public class Constantitem implements Serializable {
    private Integer id;

    private Integer constanttypeid;

    private String constantcode;

    private String constantname;

    private Integer delmark;

    private static final long serialVersionUID = 1L;
}