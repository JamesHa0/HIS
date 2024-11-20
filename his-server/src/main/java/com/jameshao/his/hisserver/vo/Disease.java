package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName disease
 */
@Data
public class Disease implements Serializable {
    private Integer id;

    private String diseasecode;

    private String diseasename;

    private String diseaseicd;

    private Integer disecategoryid;

    private Integer delmark;

    private static final long serialVersionUID = 1L;
}