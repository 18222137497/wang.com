package com.wang.service.Impl;

import com.wang.dao.RoleDao;
import com.wang.domain.Permission;
import com.wang.domain.Role;
import com.wang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findOtherPermission(String id) throws Exception {
        return roleDao.findOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(String id, String[] ids) throws Exception {
        for (String pid:ids){
            roleDao.addPermissionToRole(id,pid);
        }
    }
}
