package com.lucky_home.dao.impl;

import com.lucky_home.dao.JimgDao;
import com.lucky_home.domain.jimg;
import com.lucky_home.util.JDBCUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JimgDaoUImpl implements JimgDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtil.getDataSource());

    /**
     * 根据tid查找jimg
     * @param tid_int
     * @return
     */
    @Override
    public List<jimg> findBytidJimg(int tid_int) {
        String sql="SELECT * FROM jimg WHERE tid=?";
        List<jimg> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<jimg>(jimg.class), tid_int);
        return query;
    }

    @Override
    public jimg findJid(int jid_one) {
        String sql="select * from jimg where jid=?";
        jimg jimg = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<jimg>(jimg.class), jid_one);
        return jimg;
    }

    @Override
    public int findIfshoucang(int id, int jid_one) {
        String sql="SELECT jid FROM collect WHERE uid=? AND jid=?";
        Integer integer = 0;
        try {
            integer = jdbcTemplate.queryForObject(sql, Integer.class, id, jid_one);
        } catch (DataAccessException e) {
        }
        return integer;
    }

    @Override
    public int addjimg(int id, int jid_one) {
        String sql="INSERT INTO collect VALUES(?,?)";
        int update = 0;
        try {
            update = jdbcTemplate.update(sql, id, jid_one);
        } catch (DataAccessException e) {}
        return update;
    }
}
