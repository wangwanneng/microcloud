package com.wwn.controller;

import com.wwn.service.IProductClientService;
import com.wwn.service.IZUUlClientService;
import com.wwn.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consumer")
public class ConsumerProductController {

    @Autowired
    private IProductClientService productClientService;

    @Autowired
    private IZUUlClientService zuUlClientService;


    @RequestMapping("/product/get/{id}")
    @ResponseBody
    public Object getProduct(@PathVariable("id") long id) {
        Product product = productClientService.getProduct(id);
        return  product;
    }

    @RequestMapping("/product/list")
    @ResponseBody
    public  Object listProduct() {
        List<Product> list = productClientService.listProduct();
        return  list;
    }

    @RequestMapping("/product/add")
    @ResponseBody
    public Object addPorduct(Product product) {
        Boolean result = productClientService.addPorduct(product);
        return  result;
    }

    @RequestMapping("/product/getProductAndUser")
    @ResponseBody
    public Object getProductAndUser(long id) {
        Map<String,Object> result = new HashMap();
        result.put("product",zuUlClientService.getProduct(id));
        result.put("user",zuUlClientService.getUsers(id+""));
        return  result;
    }

}
