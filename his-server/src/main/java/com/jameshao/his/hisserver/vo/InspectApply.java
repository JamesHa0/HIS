package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @TableName inspect_apply
 */
@Data
public class InspectApply implements Serializable {
    private Integer itemid;

    private Integer inspectid;

    private String name;

    private Integer registid;

    private BigDecimal price;

    private String status;

    private String result;

    private static final long serialVersionUID = 1L;
}