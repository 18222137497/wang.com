package com.wang.service;

import com.wang.domain.Product;

import java.util.List;

/**
 * 产品业务层
 */
public interface ProductService {


    /**
     * 查询所有Product表内容
     * @return
     */
    List<Product> findAll() throws Exception;

    /**
     * 保存产品
     * @param product
     */
    void save(Product product) throws Exception;
}
