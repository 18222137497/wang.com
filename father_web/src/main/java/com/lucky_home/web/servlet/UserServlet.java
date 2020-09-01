package com.lucky_home.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky_home.domain.ResultInfo;
import com.lucky_home.domain.Type;
import com.lucky_home.domain.User;
import com.lucky_home.service.UserService;
import com.lucky_home.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userservice = new UserServiceImpl();
    /**
     * 激活方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取激活码
        String code = request.getParameter("code");
        if (code!=null) {
            //2.调用service方法完成激活
            boolean flag = userservice.active(code);
            //3.判断标记
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            } else {
                //激活失败
                msg = "激活失败,请联系管理员！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
    /**
     * 打印名字的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User attribute =(User) request.getSession().getAttribute("user");
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(),attribute);
    }

    /**
     * 登录方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断验证码是否正确
        String put_vcode = request.getParameter("put_vcode");
        String attribute =(String) request.getSession().getAttribute("CHECKCODE_SERVER");
        ResultInfo resultInfo=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        if (attribute.equalsIgnoreCase(put_vcode)){
            //获取用户名和密码
            Map parameterMap = request.getParameterMap();
            //封装user对象
            User user=new User();
            try {
                BeanUtils.populate(user,parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //调用service查询
            User u = userservice.login(user);
            if(u==null){
                //用户名或者密码错误
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("用户名或密码错误");
            }
            if (u!=null&&!"Y".equals(u.getStatus())){
                //账号正确但是没有激活
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("该账号没有激活，请去激活");
            }
            if (u!=null&&"Y".equals(u.getStatus())){
                request.getSession().setAttribute("user",u);
                resultInfo.setFlag(true);
            }
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getWriter(),resultInfo);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getWriter(),resultInfo);
        }
    }

    /**
     * 注册方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void sign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.判断验证码是否正确
        String put_vcode = request.getParameter("put_vcode");
        String attribute =(String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        ResultInfo resultInfo = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();
        if (put_vcode!=null&&attribute.equalsIgnoreCase(put_vcode)){
            //1.获取数据
            Map parameterMap = request.getParameterMap();
            //2.封装对象
            User user=new User();
            try {
                BeanUtils.populate(user,parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //3.调用service完成注册

            boolean flag=userservice.sign(user);
            //4.响应结果
            if(flag){
                //注册成功
                resultInfo.setFlag(true);
            }else {
                //注册失败
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("用户名已存在,注册失败");
            }
            //将resultInfo对象序列化为json
            String json = mapper.writeValueAsString(resultInfo);
            //写回客户端
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误,注册失败");
            String json = mapper.writeValueAsString(resultInfo);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        }
    }

    /**
     * 退出方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //session之最nb删除，直接删除session对象
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/top.html");
    }

    /**
     * 查询type表返回表的数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> typeList=userservice.findType();
        this.writeValue(response,typeList);
    }

}
