package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName medicalrecord
 */
@Data
public class Medicalrecord implements Serializable {
    private Integer id;

    private String casenumber;

    private Integer registid;

    private String readme;

    private String present;

    private String presenttreat;

    private String history;

    private String allergy;

    private String physique;

    private String proposal;

    private String careful;

    private String checkresult;

    private String diagnosis;

    private String handling;

    private Integer casestate;

    private static final long serialVersionUID = 1L;
}