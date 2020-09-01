package com.wang.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 资源权限表
 */
public class Permission implements Serializable {
    private String id;//uuid
    private String permissionName;//权限名
    private String url;//权限路径
    private List<Role> roles;//外键关联角色表   一个权限可以有多个用户

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
