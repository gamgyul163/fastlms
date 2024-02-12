package com.zerobase.fastlms.history.service;

import com.zerobase.fastlms.history.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.history.entity.LoginHistory;

import java.util.List;

public interface LoginHistoryService {
    void saveLoginHistory(LoginHistory build);

    List<LoginHistoryDto> list(MemberParam parameter);
}
