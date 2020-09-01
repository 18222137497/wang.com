package com.lucky_home.dao.impl;

import com.lucky_home.dao.UserDao;
import com.lucky_home.domain.Type;
import com.lucky_home.domain.User;
import com.lucky_home.util.JDBCUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtil.getDataSource());
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        String sql="select * from user where username=?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {}
        return user;
    }
    /**
     * 用户保存
     * @param user
     */
    @Override
    public void save(User user) {
        String sql="insert into user(username,password,name,birthday,sex,phone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getPhone(), user.getEmail(),user.getStatus(),user.getCode());
    }
    /**
     * 根据激活码查找用户
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql="select * from user where code=?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            System.out.println("根据code在数据库没有找到对应对象");
        }
        return user;
    }
    /**
     * 修改用户激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql="update user set status='Y' where id=?";
        jdbcTemplate.update(sql, user.getId());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql="select * from user where username=? and password=?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {
            System.out.println("用户不存在");
        }
        return user;
    }

    @Override
    public List<Type> findType() {
        String sql="select * from type";
        List<Type> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Type>(Type.class));
        return query;
    }

}
