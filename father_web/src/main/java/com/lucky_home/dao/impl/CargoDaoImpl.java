package com.lucky_home.dao.impl;

import com.lucky_home.dao.CargoDao;
import com.lucky_home.domain.Cargo;
import com.lucky_home.util.JDBCUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CargoDaoImpl implements CargoDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public List<Cargo> findByUserIdAllCargo(int id) {
        List<Cargo> list=null;
        String sql="SELECT t1.cuserid,t1.cid,t1.cname,t1.cnumber,t1.callnumber,t1.caddtime,t1.uoq,t2.tname FROM cargo t1,TYPE t2 WHERE t1.typeid = t2.tid and t1.cuserid = ?";
        try {
            list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cargo>(Cargo.class), id);
        } catch (DataAccessException e) {
            System.out.println("此用户货存为空");
        }
        return list;
    }
    /**
     *     根据user_id查询总记录数
     * @param user_id
     * @return
     */
    @Override
    public int findTotalCount(int user_id) {
        String sql="select count(*) from cargo where cuserid=?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, user_id);
        return integer;
    }
    /**
     *     根据user_id,start,pageSize查询当前页的数据集合
     * @param user_id
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Cargo> findByPage(int user_id, int start, int pageSize) {
        String sql="SELECT t1.cuserid,t1.cid,t1.cname,t1.cnumber,t1.callnumber,t1.caddtime,t1.uoq,t2.tname FROM cargo t1,TYPE t2 WHERE t1.typeid = t2.tid AND t1.cuserid=? order by t1.cid asc limit ?, ?";
        List<Cargo> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cargo>(Cargo.class),user_id, start, pageSize);
        return query;
    }
    /**
     * 条件查询的总记录数
     * @param user_id
     * @param seek
     * @return
     */
    @Override
    public int findSeekCount(int user_id, String seek) {
        String sql="SELECT COUNT(*) FROM cargo WHERE cuserid=? AND cname LIKE ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, user_id, "%"+seek+"%");
        return integer;
    }
    /**
     * 条件查询的数据集合
     * @param user_id
     * @param start
     * @param pageSize
     * @param seek
     * @return
     */
    @Override
    public List<Cargo> findSeekByPage(int user_id, int start, int pageSize, String seek) {
        String sql="SELECT t1.cuserid,t1.cid,t1.cname,t1.cnumber,t1.callnumber,t1.caddtime,t1.uoq,t2.tname FROM cargo t1,TYPE t2 WHERE t1.typeid = t2.tid AND t1.cuserid=? AND cname LIKE ? ORDER BY t1.cid ASC LIMIT ?, ?";
        List<Cargo> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cargo>(Cargo.class), user_id,"%"+seek+"%", start, pageSize);
        return query;
    }
}
