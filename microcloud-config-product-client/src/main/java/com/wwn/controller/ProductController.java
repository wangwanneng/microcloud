package com.wwn.controller;

import com.wwn.service.IProductService;
import com.wwn.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private IProductService productService;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value="/get/{id}")
    @ResponseBody
    public Object get(@PathVariable("id") long id) {
        return this.productService.get(id) ;
    }
    @RequestMapping(value="/add")
    @ResponseBody
    public Object add(Product product) {
        return this.productService.add(product) ;
    }
    @RequestMapping(value="/list")
    @ResponseBody
    public Object list() {
        return this.productService.list() ;
    }

    @RequestMapping("/discover")
    @ResponseBody
    public Object discover() { // 直接返回发现服务信息
        return this.client ;
    }
}
