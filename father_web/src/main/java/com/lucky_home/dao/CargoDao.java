package com.lucky_home.dao;

import com.lucky_home.domain.Cargo;

import java.util.List;

public interface CargoDao {
    /**
     * 根据用户登录id获取货存
     * @param id
     * @return
     */
    List<Cargo> findByUserIdAllCargo(int id);

    /**
     *     根据user_id查询总记录数
      * @param user_id
     * @return
     */
    int findTotalCount(int user_id);

    /**
     *     根据user_id,start,pageSize查询当前页的数据集合
     * @param user_id
     * @param start
     * @param pageSize
     * @return
     */
    List<Cargo> findByPage(int user_id,int start,int pageSize);

    /**
     * 条件查询的总记录数
     * @param user_id
     * @param seek
     * @return
     */
    int findSeekCount(int user_id, String seek);

    /**
     * 条件查询的数据集合
     * @param user_id
     * @param start
     * @param pageSize
     * @param seek
     * @return
     */
    List<Cargo> findSeekByPage(int user_id, int start, int pageSize, String seek);
}
