package com.wang.Controller;

import com.wang.domain.Syslog;
import com.wang.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 日志的控制器
 */
@Controller
@RequestMapping("/aopLogController")
public class aopLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有日志
     * @return
     * @throws Exception
     */
    @RequestMapping("/logFindAll.do")
    public ModelAndView logFindAll() throws Exception{
        List<Syslog> syslog=sysLogService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("logFindAll",syslog);//进行转发
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }
}
