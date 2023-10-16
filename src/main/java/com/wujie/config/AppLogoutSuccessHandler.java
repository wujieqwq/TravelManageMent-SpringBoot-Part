package com.wujie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wujie.utils.ResultInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AppLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ObjectMapper objectMapper;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setData("登出");
        String s = objectMapper.writeValueAsString(resultInfo);
        response.setCharacterEncoding("utf-8");
        objectMapper.writeValue(response.getWriter(),s);
    }
}
