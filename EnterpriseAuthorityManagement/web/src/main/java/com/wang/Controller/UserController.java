package com.wang.Controller;

import com.wang.domain.Role;
import com.wang.domain.Users;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    @RequestMapping("/userFindAll.do")
    public ModelAndView userFindAll() throws Exception{
        List<Users> users=userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userFindAll",users);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 保存用户
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/userSave.do")
    public String userSave(Users user) throws Exception {
        userService.save(user);
        return "redirect:userFindAll.do";
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/userFindById.do")
    public ModelAndView userFindAllById(String id) throws Exception{
        Users users=userService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userFindById",users);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    /**
     * 查询用户未拥有的角色
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/userFindUserByIdAndAllRole.do")
    public ModelAndView userFindUserByIdAndAllRole(String id) throws Exception{
        List<Role> roles = userService.findOtherRole(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userFindUserByIdAndAllRole",roles);
        modelAndView.addObject("id",id);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId,String[] ids) throws Exception{
        userService.addRoleToUser(userId,ids);
        return "redirect:userFindAll.do";
    }

}
