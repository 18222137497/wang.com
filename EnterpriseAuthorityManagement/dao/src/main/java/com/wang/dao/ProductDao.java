package com.wang.dao;

import com.wang.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品dao
 */
@Repository
public interface ProductDao {
    /**
     * 查询所有product表信息
     * @return
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from product where id=#{id}")
    Product findById(String id) throws Exception;

    /**
     * 保存操作
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;

}
