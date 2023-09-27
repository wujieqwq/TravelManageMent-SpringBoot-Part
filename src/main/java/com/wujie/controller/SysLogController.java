package com.wujie.controller;


import com.github.pagehelper.PageInfo;
import com.wujie.pojo.SysLog;
import com.wujie.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("syslog")
@CrossOrigin
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/logList")
    @ResponseBody
    public PageInfo<SysLog> list(@RequestParam(defaultValue = "1") Integer pageNum){
        return sysLogService.list(pageNum);
    }

}
