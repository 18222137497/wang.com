package com.lucky_home.web.servlet;


import com.lucky_home.domain.User;
import com.lucky_home.domain.jimg;
import com.lucky_home.service.JimgService;
import com.lucky_home.service.impl.JimgServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/jimg/*")
public class JimgServlet extends BaseServlet{
    private JimgService jimgService=new JimgServiceImpl();
    public void findBytidJimg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tid = request.getParameter("tid");
        if (tid!=null&&!"".equals(tid)&&!"null".equals(tid)){
            int tid_int=Integer.parseInt(tid);
            List<jimg> list=jimgService.findBytidJimg(tid_int);
            writeValue(response,list);
        }
    }
    public void findJid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jid = request.getParameter("jid");
        if (jid!=null&&!"".equals(jid)&&!"null".equals(jid)&&!"0".equals(jid)){
            int jid_one=Integer.parseInt(jid);
            jimg j=jimgService.findJid(jid_one);
            writeValue(response,j);
        }
    }
    public void findIfshoucang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jid = request.getParameter("jid");
        User user=(User)request.getSession().getAttribute("user");
        if (user!=null&&jid!=null&&!"".equals(jid)&&!"null".equals(jid)&&!"0".equals(jid)){
            int jid_one=Integer.parseInt(jid);
            boolean j=jimgService.findIfshoucang(user.getId(),jid_one);
            writeValue(response,j);
        }
        if (user==null){
            writeValue(response,false);
        }
    }
    public void addjimg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jid = request.getParameter("jid");
        User user=(User)request.getSession().getAttribute("user");
        if (user!=null&&jid!=null&&!"".equals(jid)&&!"null".equals(jid)&&!"0".equals(jid)){
            int jid_one=Integer.parseInt(jid);
            boolean j=jimgService.addjimg(user.getId(),jid_one);
            writeValue(response,j);
        }
        if (user==null){
            writeValue(response,false);
        }
    }
}
