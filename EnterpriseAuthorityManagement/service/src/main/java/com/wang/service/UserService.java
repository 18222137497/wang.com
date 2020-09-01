package com.wang.service;

import com.wang.domain.Role;
import com.wang.domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户业务层
 */
public interface UserService extends UserDetailsService {

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<Users> findAll() throws Exception;

    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    void save(Users user) throws Exception;

    /**
     * 根据id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    Users findById(String id) throws Exception;

    /**
     * 查询用户未拥有的角色
     * @param id
     * @return
     * @throws Exception
     */
    List<Role> findOtherRole(String id) throws Exception;

    /**
     * 给用户添加角色
     * @param id
     * @param ids
     * @throws Exception
     */
    void addRoleToUser(String id, String[] ids) throws Exception;
}
