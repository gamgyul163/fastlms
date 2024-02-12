package com.zerobase.fastlms.banner.model;

import com.zerobase.fastlms.banner.entity.type.ATarget;
import lombok.Data;

@Data
public class BannerInput {

    Long id;
    String bannerName;
    String imageSrc;
    String aHref;
    String imageAlt;
    Long sortValue;
    Boolean showingYn;

    ATarget aTarget;
    
    //삭제를 위한
    String idList;
    
    
    //ADD
    String fileName;
    String urlFileName;

}
