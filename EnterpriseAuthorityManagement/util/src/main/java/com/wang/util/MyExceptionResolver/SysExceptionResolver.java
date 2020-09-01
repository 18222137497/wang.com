package com.wang.util.MyExceptionResolver;

import com.wang.util.MyExceptionResolver.MyException.SysException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理器
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        SysException exception=null;
//        if (ex!=null){
//            exception=(SysException) ex;
//        }else{
//            exception=new SysException("服务器繁忙");
//        }
        SysException exception=new SysException("服务器繁忙");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error500",exception.getMessage());//出现500状态码就返回error500,报出服务器繁忙
        modelAndView.setViewName("error500");
        return modelAndView;
    }
}
