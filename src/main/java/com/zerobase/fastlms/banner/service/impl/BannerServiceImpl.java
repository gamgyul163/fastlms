package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public List<BannerDto> list(BannerParam parameter) {
        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public BannerDto getById(Long bannerId) {
        return bannerRepository.findById(bannerId).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean set(BannerInput parameter) {
        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if (!optionalBanner.isPresent()) {
            return false;
        }
        bannerRepository.save(Banner.builder()
                .id(parameter.getId())
                .bannerName(parameter.getBannerName())
                .fileName(parameter.getFileName())
                .urlFileName(parameter.getUrlFileName())
                .aHref(parameter.getAHref())
                .imageAlt(parameter.getImageAlt())
                .sortValue(parameter.getSortValue())
                .showingYn(parameter.getShowingYn())
                .aTarget(parameter.getATarget())
                .build());
        return true;
    }

    @Override
    public boolean add(BannerInput parameter) {
        bannerRepository.save(Banner.builder()
                .id(parameter.getId())
                .bannerName(parameter.getBannerName())
                .fileName(parameter.getFileName())
                .urlFileName(parameter.getUrlFileName())
                .aHref(parameter.getAHref())
                .imageAlt(parameter.getImageAlt())
                .sortValue(parameter.getSortValue())
                .showingYn(parameter.getShowingYn())
                .aTarget(parameter.getATarget())
                .regDt(LocalDateTime.now())
                .build());
        return true;
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }
                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<BannerDto> listToShow() {
        return bannerMapper.selectListToShow();
    }
}
