package com.wujie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wujie.utils.ResultInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class AppAutheticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
//        String s = objectMapper.writeValueAsString(resultInfo);
        resultInfo.setData(authentication);
        response.setCharacterEncoding("utf-8");
        objectMapper.writeValue(response.getWriter(),resultInfo);
    }
}
