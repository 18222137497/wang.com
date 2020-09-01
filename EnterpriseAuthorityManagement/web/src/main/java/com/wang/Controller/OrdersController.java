package com.wang.Controller;

import com.github.pagehelper.PageInfo;
import com.wang.domain.Orders;
import com.wang.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单控制器
 */
@Controller
@RequestMapping("/ordersController")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 查询所有订单,这里使用分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/ordersFindAll.do")
    public ModelAndView OrdersFindAll(@RequestParam(name="page",defaultValue = "1")Integer page,@RequestParam(name="size",defaultValue = "4")Integer size) throws Exception{
        List<Orders> all=ordersService.findAll(page,size);
        PageInfo<Orders> pageHelper=new PageInfo<Orders>(all);//进行分页处理
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ordersFindAll",pageHelper);
        modelAndView.setViewName("orders-page-list");
        return modelAndView;
    }

    /**
     * 查询订单详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/ordersFindById.do")//todo 这里有问题
    public ModelAndView OrdersFindById(String id) throws Exception{
        Orders orders=ordersService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orders-show");
        modelAndView.addObject("ordersFindById",orders);
        return modelAndView;
    }


}
