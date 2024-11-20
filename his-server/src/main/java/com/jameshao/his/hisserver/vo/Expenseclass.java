package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName expenseclass
 */
@Data
public class Expenseclass implements Serializable {
    private Integer id;

    private String expcode;

    private String expname;

    private Integer delmark;

    private static final long serialVersionUID = 1L;
}