package com.wang.service.Impl;

import com.wang.dao.UserDao;
import com.wang.domain.Role;
import com.wang.domain.Users;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;//加密类
    @Override
    public List<Users> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(Users user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public Users findById(String id)  throws Exception{
        Users users=userDao.findById(id);
        return users;
    }

    @Override
    public List<Role> findOtherRole(String id) throws Exception {
        return userDao.findOtherRole(id);
    }

    @Override
    public void addRoleToUser(String id, String[] ids) throws Exception {
        for (String rid : ids) {
            userDao.addRoleToUser(id,rid);
        }
    }

    /**
     * 加密方法
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Users byUsername =null;
        try {
             byUsername=userDao.findByEmail(email);//执行查询方法,获取登录对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        //这个user是spring-security的类,第一参数:用户名,二:密码,三~六:查看中文源码,七:权限名
        User user=new User(byUsername.getUsername(),byUsername.getPassword(),byUsername.getStatus()==1?true:false,true,true,true,getAuthority((byUsername.getRoles())));
        return user;
    }
    //作用就是返回一个List集合，集合中装入的是角色描述(获取登录用户拥有的权限)
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }


}
