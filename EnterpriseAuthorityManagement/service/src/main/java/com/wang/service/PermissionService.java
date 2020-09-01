package com.wang.service;

import com.wang.domain.Permission;

import java.util.List;
/**
 * 权限业务层
 */
public interface PermissionService {
    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    List<Permission> findAll() throws Exception;

    /**
     * 添加权限
     * @param permission
     * @throws Exception
     */
    void save(Permission permission) throws Exception;
}
