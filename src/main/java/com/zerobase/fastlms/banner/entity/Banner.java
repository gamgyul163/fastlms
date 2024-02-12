package com.zerobase.fastlms.banner.entity;

import com.zerobase.fastlms.banner.entity.type.ATarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String bannerName;
    String aHref;
    String imageAlt;
    Long sortValue;
    Boolean showingYn;

    @Enumerated(EnumType.STRING)
    ATarget aTarget;

    LocalDateTime regDt;

    String fileName;
    String urlFileName;
}
