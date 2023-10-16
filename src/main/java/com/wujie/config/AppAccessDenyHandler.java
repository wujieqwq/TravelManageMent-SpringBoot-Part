package com.wujie.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wujie.utils.ResultInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AppAccessDenyHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(false);
        resultInfo.setData("拒绝访问");
        String s = objectMapper.writeValueAsString(resultInfo);
        response.setCharacterEncoding("utf-8");
        objectMapper.writeValue(response.getWriter(),s);
    }
}
