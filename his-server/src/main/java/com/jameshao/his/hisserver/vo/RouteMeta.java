package com.jameshao.his.hisserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteMeta {
    String title;
    String icon;
    boolean hidden;
    boolean alwaysShow;
    String params;
}
