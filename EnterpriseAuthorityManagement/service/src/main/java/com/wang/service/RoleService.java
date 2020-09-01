package com.wang.service;

import com.wang.domain.Permission;
import com.wang.domain.Role;

import javax.servlet.jsp.el.ELParseException;
import java.util.List;

/**
 * 角色业务层
 */
public interface RoleService {
    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws Exception;

    /**
     * 添加用户
     * @param role
     * @throws Exception
     */
    void save(Role role) throws Exception;

    /**
     * 查询未拥有的权限
     * @param id
     * @return
     * @throws Exception
     */
    List<Permission> findOtherPermission(String id) throws Exception;

    /**
     * 给角色添加权限
     * @param id
     * @param ids
     * @throws Exception
     */
    void addPermissionToRole(String id, String[] ids) throws Exception;
}
