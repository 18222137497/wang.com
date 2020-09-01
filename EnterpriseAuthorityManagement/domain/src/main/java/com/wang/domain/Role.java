package com.wang.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 */
public class Role implements Serializable {
    private String id;//uuid
    private String roleName;//角色名称
    private String roleDesc;//角色描述
    private List<Permission> permissions;//外键关联权限表，一个角色可以有多个权限
    private List<Users> users;//外键关联用户表，一个角色可以有多个用户

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

}
