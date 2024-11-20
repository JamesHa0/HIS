package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName constanttype
 */
@Data
public class Constanttype implements Serializable {
    private Integer id;

    private String constanttypecode;

    private String constanttypename;

    private Integer delmark;

    private static final long serialVersionUID = 1L;
}