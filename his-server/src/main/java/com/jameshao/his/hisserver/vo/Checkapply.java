package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName checkapply
 */
@Data
public class Checkapply implements Serializable {
    private Integer id;

    private Integer medicalid;

    private Integer registid;

    private Integer itemid;

    private String name;

    private String objective;

    private String position;

    private Integer isurgent;

    private Integer num;

    private Date creationtime;

    private Integer doctorid;

    private Integer checkoperid;

    private Integer resultoperid;

    private Date checktime;

    private String result;

    private Date resulttime;

    private Integer state;

    private Integer recordtype;

    private static final long serialVersionUID = 1L;
}