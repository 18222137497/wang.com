package com.wang.Controller;

import com.wang.domain.Permission;
import com.wang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 权限控制器
 */
@Controller
@RequestMapping("/permissionController")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    @RequestMapping("/permissionFindAll.do")
    public ModelAndView permissionFindAll() throws Exception{
        List<Permission> all = permissionService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionFindAll",all);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    /**
     * 保存权限
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/permissionSave")
    public String permissionSave(Permission permission)throws Exception{
        permissionService.save(permission);
        return "redirect:permission-list";
    }

}
