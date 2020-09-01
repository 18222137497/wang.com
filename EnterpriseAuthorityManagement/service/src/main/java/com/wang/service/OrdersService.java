package com.wang.service;

import com.wang.domain.Orders;

import java.util.List;

/**
 * 订单业务层
 */
public interface OrdersService {
    /**
     * 分页查询所有用户
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<Orders> findAll(Integer page,Integer size) throws Exception;

    /**
     * 根据id查询订单
     * @param id
     * @return
     * @throws Exception
     */
    Orders findById(String id) throws Exception;
}
