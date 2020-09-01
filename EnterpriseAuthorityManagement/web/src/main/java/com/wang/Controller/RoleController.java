package com.wang.Controller;

import com.wang.domain.Permission;
import com.wang.domain.Role;
import com.wang.service.RoleService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色控制器
 */
@Controller
@RequestMapping("/roleController")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查寻所有角色
     * @return
     * @throws Exception
     */
    @RequestMapping("/roleFindAll.do")
    public ModelAndView roleFindAll() throws Exception{
        List<Role> roles=roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleFindAll",roles);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    /**
     * 保存角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/roleSave.do")
    public String roleSave(Role role)throws Exception{
        roleService.save(role);
        return "redirect:roleFindAll.do";
    }

    /**
     * 查询角色未拥有的权限
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception{
        List<Permission> permissions=roleService.findOtherPermission(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("findRoleByIdAndAllPermission",permissions);
        modelAndView.addObject("roleId",id);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,String[] ids) throws Exception{
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:roleFindAll.do";
    }

}
