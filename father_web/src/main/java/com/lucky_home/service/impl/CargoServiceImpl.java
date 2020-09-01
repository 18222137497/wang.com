package com.lucky_home.service.impl;

import com.lucky_home.dao.CargoDao;
import com.lucky_home.dao.impl.CargoDaoImpl;
import com.lucky_home.domain.Cargo;
import com.lucky_home.domain.PageBean;
import com.lucky_home.service.CargoService;

import java.util.List;

public class CargoServiceImpl implements CargoService {
    private CargoDao cargoDao=new CargoDaoImpl();
    /**
     * 根据用户id查询库存
     * @param id
     * @return
     */
    @Override
    public List<Cargo> findByUserIdAllCargo(int id) {
        List<Cargo> list = cargoDao.findByUserIdAllCargo(id);
        return list;
    }

    /**
     * 根据登录用户分页查询
     * @param user_id
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Cargo> pageQuery(int user_id, int currentPage, int pageSize) {
        PageBean<Cargo> cargoPageBean=new PageBean<Cargo>();
        //设置当前页码
        cargoPageBean.setCurrentPage(currentPage);
        //设置每页显示条数
        cargoPageBean.setPageSize(pageSize);
        //设置总的记录数
        int totalCount=cargoDao.findTotalCount(user_id);
        cargoPageBean.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        //开始的记录数
        int start=(currentPage-1)*pageSize;//当前页码见一乘每页显示的条数
        List<Cargo> list=cargoDao.findByPage(user_id,start,pageSize);
        cargoPageBean.setList(list);
        //设置总页数=总记录数/每页显示条数
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        cargoPageBean.setTotalPage(totalPage);
        return cargoPageBean;
    }

    /**
     * 条件查询
     * @param user_id
     * @param currentPage
     * @param pageSize
     * @param seek
     * @return
     */
    @Override
    public PageBean<Cargo> seekPageQuery(int user_id, int currentPage, int pageSize, String seek) {
        PageBean<Cargo> cargoPageBean=new PageBean<Cargo>();
        cargoPageBean.setCurrentPage(currentPage);
        cargoPageBean.setPageSize(pageSize);
        int seekCount=cargoDao.findSeekCount(user_id,seek);//条件查询的总记录数
        cargoPageBean.setTotalCount(seekCount);
        //设置当前页显示的数据集合
        //开始的记录数
        int start=(currentPage-1)*pageSize;//当前页码见一乘每页显示的条数
        List<Cargo> list=cargoDao.findSeekByPage(user_id,start,pageSize,seek);
        cargoPageBean.setList(list);
        //设置总页数=总记录数/每页显示条数
        int totalPage=seekCount%pageSize==0?seekCount/pageSize:seekCount/pageSize+1;
        cargoPageBean.setTotalPage(totalPage);
        return cargoPageBean;
    }
}
