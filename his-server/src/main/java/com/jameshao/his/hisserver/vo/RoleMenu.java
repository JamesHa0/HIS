package com.jameshao.his.hisserver.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName role_menu
 */
@Data
public class RoleMenu implements Serializable {
    private Long roleId;

    private Long menuId;

    private static final long serialVersionUID = 1L;
}