package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @TableName registlevel
 */
@Data
public class Registlevel implements Serializable {
    private Integer id;

    private String registcode;

    private String registname;

    private Integer sequenceno;

    private BigDecimal registfee;

    private Integer registquota;

    private Integer delmark;

    private static final long serialVersionUID = 1L;
}