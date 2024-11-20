package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName medicaldisease
 */
@Data
public class Medicaldisease implements Serializable {
    private Integer id;

    private Integer medicalid;

    private Integer registid;

    private Integer diseaseid;

    private Integer diagnosetype;

    private Date getsiskdate;

    private Integer diagnosecate;

    private static final long serialVersionUID = 1L;
}