package com.wujie.controller;

import com.wujie.pojo.SysLog;
import com.wujie.pojo.UserInfo;
import com.wujie.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class SysLogAspects {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;
    @Pointcut("execution(* com.wujie.controller.*.*(..))")
    public void pc(){}


    /*
      `id` VARCHAR(32) -- uuid,
      `visitTime` TIMESTAMP -- 访问时间,
      `username` VARCHAR(20) -- 访问用户名, session.getAttribute("");
      `ip` VARCHAR(20) -- 访问客户端的ip地址, request.getRemoteAddr()
      `url` VARCHAR(50) -- 访问的uri路径, request.getRequestURI()
      `executionTime` INT  -- 执行的时长(结束时间-访问时间),
      `method` VARCHAR(20) -- 访问的方法
     */

    private Date visitTime;
    @Before("SysLogAspects.pc()")
    public void before(){
        visitTime = new Date();
    }

    @AfterReturning("SysLogAspects.pc()")
    public void after(JoinPoint jp){
        Date end = new Date();
        long executionTime = end.getTime() - visitTime.getTime();
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();

//        SecurityContext context = SecurityContextHolder.getContext();
//        String username = context.getAuthentication().getPrincipal().toString();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        String username = userInfo.getUsername();

        Class<?> clazz = jp.getTarget().getClass();
        if(clazz != SysLogController.class){
            String className = clazz.getSimpleName();
            String methodName = jp.getSignature().getName();
            SysLog sysLog = new SysLog();

            sysLog.setId(id);
            sysLog.setVisitTime(visitTime);
            sysLog.setIp(ip);
            sysLog.setExecutionTime(executionTime);
            sysLog.setUrl(uri);
            sysLog.setUsername(username);
            sysLog.setMethod("[类名]"+className+"[方法名]"+methodName);

            sysLogService.addLog(sysLog);
        }

    }
}

