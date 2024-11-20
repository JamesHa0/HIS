package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName fmeditem
 */
@Data
public class Fmeditem implements Serializable {
    private Integer id;

    private String itemcode;

    private String itemname;

    private String format;

    private BigDecimal price;

    private Integer expclassid;

    private Integer deptid;

    private String mnemoniccode;

    private Date creationdate;

    private Date lastupdatedate;

    private Integer recordtype;

    private Integer delmark;

    private static final long serialVersionUID = 1L;
}