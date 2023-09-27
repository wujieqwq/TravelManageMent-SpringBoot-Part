package com.wujie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wujie.mapper.SysLogMapper;
import com.wujie.pojo.SysLog;
import com.wujie.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public PageInfo<SysLog> list(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<SysLog> list = sysLogMapper.list();
        return new PageInfo<>(list);
    }

    @Override
    public void addLog(SysLog sysLog) {
        sysLogMapper.addLog(sysLog);
    }
}
