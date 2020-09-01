package com.lucky_home.service;

import com.lucky_home.domain.Cargo;
import com.lucky_home.domain.PageBean;

import java.util.List;

public interface CargoService {
    /**
     * 根据用户id查询所有库存
     * @return
     * @param id
     */
    List<Cargo> findByUserIdAllCargo(int id);

    /**
     * 根据登录用户分页查询
     * @param user_id
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Cargo> pageQuery(int user_id,int currentPage,int pageSize);

    /**
     * 条件查询
     * @param user_id
     * @param currentPage
     * @param pageSize
     * @param seek
     * @return
     */
    PageBean<Cargo> seekPageQuery(int user_id, int currentPage, int pageSize, String seek);
}
