package com.wang.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wang.dao.OrdersDao;
import com.wang.domain.Orders;
import com.wang.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ordersService")
@Transactional(rollbackFor = Exception.class)
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(Integer page,Integer size) throws Exception {
        //设置分页 page 当前页数 size 显示条数+
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findById(id);
    }
}
