package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName inspectitem
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inspectitem implements Serializable {
    private Integer inspectid;

    private String name;

    private BigDecimal price;

    private String del;

    private Date createtime;

    private static final long serialVersionUID = 1L;
}