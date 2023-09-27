package com.wujie.service;

import com.github.pagehelper.PageInfo;
import com.wujie.pojo.SysLog;

public interface SysLogService {

    PageInfo<SysLog> list(Integer pageNum);

    void addLog(SysLog sysLog);
}
