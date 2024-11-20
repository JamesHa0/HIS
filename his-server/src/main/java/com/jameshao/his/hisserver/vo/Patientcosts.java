package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName patientcosts
 */
@Data
public class Patientcosts implements Serializable {
    private Integer id;

    private Integer registid;

    private Integer invoiceid;

    private Integer itemid;

    private Integer itemtype;

    private String name;

    private BigDecimal price;

    private BigDecimal amount;

    private Integer deptid;

    private Date createtime;

    private Integer createoperid;

    private Date paytime;

    private Integer registerid;

    private Integer feetype;

    private Integer backid;

    private static final long serialVersionUID = 1L;
}