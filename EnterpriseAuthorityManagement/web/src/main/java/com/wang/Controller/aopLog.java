package com.wang.Controller;

import com.wang.service.SysLogService;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import com.wang.domain.Syslog;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志类
 */
@Component
@Aspect//声明这个是aop
public class aopLog {
    @Autowired
    private HttpServletRequest request;//通过设置一个RequestContextListener的监听器，可以在容器中存入一个request对象
    @Autowired
    private SysLogService sysLogService;//日志业务层

    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法

    @Before("execution(* com.wang.Controller.*.*(..))")//作用于所有Controller包下的所有方法
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {//前置通知
        startTime=new Date();//获取开始执行方法的时间
        executionClass=jp.getTarget().getClass();//获取访问的类
        String methodName=jp.getSignature().getName();//获取访问方法的名字
        Object[] args = jp.getArgs();//获取访问的方法的参数
        if (args==null||args.length==0){//如果为无参方法
            executionMethod=executionClass.getMethod(methodName);//获取执行的方法的class对象
        }else {
            Class[] classArgs=new Class[args.length];// 创建一个Class容器
            for (int i=0;i<args.length;i++){
                classArgs[i]=args[i].getClass();//将所有参数的Class对象存入容器中
            }
            executionMethod=executionClass.getMethod(methodName,classArgs);//获得有参的方法
        }

    }

    @After("execution(* com.wang.Controller.*.*(..))")//作用于所有Controller包下的所有方法
    public void doAfter(JoinPoint jp) throws Exception{//后置通知
        if (executionClass!=null&&executionMethod!=null&&executionClass!=aopLog.class) {//如果获取到了方法才会执行
            RequestMapping annotation =(RequestMapping) executionClass.getAnnotation(RequestMapping.class);//获取类上的RequestMapping注解
            if (annotation!=null){//是否获取到
                RequestMapping annotation1 = executionMethod.getAnnotation(RequestMapping.class);//获取方法上的RequestMapping注解
                if (annotation1!=null){//是否获取到
                    String url=annotation.value()[0]+annotation1.value()[0];//获取RequestMapping注解的value，拼接
                    String ip = request.getRemoteAddr();//获取访问者的ip
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录用户名
                    User user =(User) context.getAuthentication().getPrincipal();//这个user是spring-security里面的user
                    String username = user.getUsername();//获取用户名
                    //这个获取username可以用获取session获取  方法如下
                    //User spring_security_context = (User)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                    Syslog syslog = new Syslog();
                    syslog.setVisitTime(startTime);//什么时候开始操作的
                    syslog.setExecutionTime(new Date().getTime()-startTime.getTime());//操作方法用了多少时间
                    syslog.setIp(ip);//使用者的ip
                    syslog.setUrl(url);//访问的方法
                    syslog.setUsername(username);
                    syslog.setMethod("调用"+executionClass+"类的"+executionMethod+"方法");
                    sysLogService.save(syslog);//写入日志
                }
            }
        }

    }
}
