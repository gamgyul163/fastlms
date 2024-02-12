package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.entity.type.ATarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BannerDto {
    Long id;
    String bannerName;
    String aHref;
    ATarget aTarget;
    String imageAlt;
    Long sortValue;
    Boolean showingYn;
    LocalDateTime regDt;

    String fileName;
    String urlFileName;

    Long totalCount;
    Long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .fileName(banner.getFileName())
                .urlFileName(banner.getUrlFileName())
                .aHref(banner.getAHref())
                .imageAlt(banner.getImageAlt())
                .sortValue(banner.getSortValue())
                .showingYn(banner.getShowingYn())
                .aTarget(banner.getATarget())
                .regDt(banner.getRegDt())
                .build();
    }

}
