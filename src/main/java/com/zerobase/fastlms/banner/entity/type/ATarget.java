package com.zerobase.fastlms.banner.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ATarget {
    BLANK("_blank"),
    SELF("_self"),
    PARENT("_parent"),
    TOP("_top"),
    ;
    private final String value;
}
