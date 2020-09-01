package com.lucky_home.service;

import com.lucky_home.domain.Type;
import com.lucky_home.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 注册用户的方法
     * @param user
     * @return
     */
    boolean sign(User user);
    /**
     * 激活用户
     * @param code
     * @return
     */
    boolean active(String code);
    //查询用户是否存在的登录方法
    User login(User user);

    /**
     * 查询type表，返回内容
     * @return
     */
    List<Type> findType();
}
