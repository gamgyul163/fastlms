package com.zerobase.fastlms.history.service.impl;

import com.zerobase.fastlms.history.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.history.entity.LoginHistory;
import com.zerobase.fastlms.history.repository.LoginHistoryRepository;
import com.zerobase.fastlms.history.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;
    public void saveLoginHistory(LoginHistory loginHistory) {
        loginHistoryRepository.save(loginHistory);
    }

    @Override
    public List<LoginHistoryDto> list(MemberParam parameter) {

        long totalCount = loginHistoryRepository.countByUserId(parameter.getUserId());

        List<LoginHistoryDto> list = loginHistoryRepository.findAllByUserId(parameter.getUserId()).stream()
                .map(LoginHistoryDto::of)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for(LoginHistoryDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }
}
