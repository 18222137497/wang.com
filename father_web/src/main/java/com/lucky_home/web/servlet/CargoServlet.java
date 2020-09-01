package com.lucky_home.web.servlet;


import com.lucky_home.domain.Cargo;
import com.lucky_home.domain.PageBean;
import com.lucky_home.domain.User;
import com.lucky_home.service.CargoService;
import com.lucky_home.service.impl.CargoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/cargo/*")
public class CargoServlet extends BaseServlet{
    private CargoService cargoService=new CargoServiceImpl();
    public void findByCargoAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");//当前页码
        String seek = request.getParameter("seek");
        if (seek!=null&&!"".equals(seek)) {
            seek = URLDecoder.decode(seek, "UTF-8");
        }
        User user =(User) request.getSession().getAttribute("user");
        //2.处理参数
        int user_id=0;
        if (user!=null){
            user_id=user.getId();
        }
        int currentPage=1;
        if (currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }
        int pageSize = 5;//每页显示的条数
        PageBean<Cargo> cargoPageBean =null;
        if (seek==null||"".equals(seek)||"null".equals(seek)) {
            //3.调用service查询PageBean对象
            cargoPageBean = cargoService.pageQuery(user_id, currentPage, pageSize);//查询所有
        }else {
            cargoPageBean=cargoService.seekPageQuery(user_id, currentPage, pageSize, seek);//条件查询
        }
        //4.将查询到的PageBean对象转换为json对象
        writeValue(response, cargoPageBean);
    }
}

