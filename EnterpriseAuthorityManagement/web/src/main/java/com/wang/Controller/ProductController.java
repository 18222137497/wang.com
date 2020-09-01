package com.wang.Controller;

import com.wang.domain.Product;
import com.wang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 产品控制器
 */
@Controller
@RequestMapping("/productController")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询product所有数据
     * @return
     */
    @RequestMapping("/productFindAll.do")
//    @RolesAllowed("TEST")  jsr250
//    @Secured("ROLE_ADMIN")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("authentication.principal.username=='dominate'")
    public ModelAndView ProductFindAll() throws Exception{
        List<Product> all = productService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productFindAll",all);
        modelAndView.setViewName("product-datalist");
        return modelAndView;
    }

    /**
     * 保存产品
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("/productSave.do")
    public String ProductSave(Product product) throws Exception{
        productService.save(product);
        return "redirect:productFindAll.do";
    }


}
