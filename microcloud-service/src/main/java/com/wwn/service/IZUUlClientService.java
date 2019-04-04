package com.wwn.service;


import com.wwn.feign.FeignClientConfig;
import com.wwn.service.fallback.IZUUlClientServiceallbackFactory;
import com.wwn.vo.Product;
import com.wwn.vo.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "MICROCLOUD-ZUUL-GATEWAY",configuration = FeignClientConfig.class
        ,fallbackFactory = IZUUlClientServiceallbackFactory.class)
public interface IZUUlClientService {

    @RequestMapping("/enjoy-api/product-proxy/product/get/{id}")
    public Product getProduct(@PathVariable("id")long id);

    @RequestMapping("/enjoy-api/product-proxy/product/list")
    public List<Product> listProduct() ;

    @RequestMapping("/enjoy-api/product-proxy/product/add")
    public boolean addPorduct(Product product) ;

    @RequestMapping("/enjoy-api/users-proxy/users/get/{name}")
    public Users getUsers(@PathVariable("name")String name);

}
