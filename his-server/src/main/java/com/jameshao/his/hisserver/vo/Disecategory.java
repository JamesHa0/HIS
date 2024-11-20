package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName disecategory
 */
@Data
public class Disecategory implements Serializable {
    private Integer id;

    private String dicacode;

    private String dicaname;

    private Integer sequenceno;

    private Integer dicatype;

    private Integer delmark;

    private static final long serialVersionUID = 1L;
}