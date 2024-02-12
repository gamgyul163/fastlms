package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.history.entity.LoginHistory;
import com.zerobase.fastlms.history.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String userId = userDetails.getUsername();
        String userAgent = request.getHeader("User-Agent");
        String ipAddress = request.getRemoteAddr();

        loginHistoryService.saveLoginHistory(LoginHistory.builder()
                .userId(userId)
                .userAgent(userAgent)
                .ipAddress(ipAddress)
                .loginDt(LocalDateTime.now())
                .build());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
