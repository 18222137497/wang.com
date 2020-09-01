package com.wang.dao;

import com.wang.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游客dao
 */
@Repository
public interface TravellerDao {
    /**
     * 查询所有游客
     * @return
     * @throws Exception
     */
    @Select("select * from traveller")
    List<Traveller> findAll() throws Exception;

    /**
     * 根据订单id获取拥有该订单的所有游客
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;

}
