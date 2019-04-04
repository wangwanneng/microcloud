package com.wwn.controller;

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
import java.util.List;

@Controller
@RequestMapping("/consumer")
public class ConsumerProductController {

//    public static final String PRODUCT_GET_URL = "http://localhost:8080/product/get/";
//    public static final String PRODUCT_LIST_URL="http://localhost:8080/product/list/";
//    public static final String PRODUCT_ADD_URL = "http://localhost:8080/product/add/";

    //使用eureka的方式调用
    public static final String PRODUCT_GET_URL = "http://MICROCLOUD-PROVIDER-PRODUCT/product/get/";
    public static final String PRODUCT_LIST_URL="http://MICROCLOUD-PROVIDER-PRODUCT/product/list/";
    public static final String PRODUCT_ADD_URL = "http://MICROCLOUD-PROVIDER-PRODUCT/product/add/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

//    加密之前的调用方式
   /* @RequestMapping("/product/get/{id}")
    @ResponseBody
    public Object getProduct(@PathVariable("id") long id) {
        Product product = restTemplate.getForObject(PRODUCT_GET_URL + id, Product.class);
        return  product;
    }

    @RequestMapping("/product/list")
    @ResponseBody
    public  Object listProduct() {
        List<Product> list = restTemplate.getForObject(PRODUCT_LIST_URL, List.class);
        return  list;
    }

    @RequestMapping("/product/add")
    @ResponseBody
    public Object addPorduct(@RequestBody  Product product) {
        Boolean result = restTemplate.postForObject(PRODUCT_ADD_URL, product, Boolean.class);
        return  result;
    }*/

    /**
     *  加密之后的调用方式
     */
    @RequestMapping("/product/get/{id}")
    @ResponseBody
    public Object getProduct(@PathVariable("id") long id) {
        Product product = restTemplate.exchange(PRODUCT_GET_URL + id, HttpMethod.GET,new HttpEntity<Product>(httpHeaders),Product.class).getBody();
        return  product;
    }

    @RequestMapping("/product/list")
    @ResponseBody
    public  Object listProduct() {
        List<Product> list = restTemplate.exchange(PRODUCT_LIST_URL,HttpMethod.GET,new HttpEntity<List>(httpHeaders),List.class).getBody();
        return  list;
    }

        //这个是报错的，根本就通不过 直接配置在application.properties中有问题，还不知道怎么解决
    @RequestMapping("/product/add")
    @ResponseBody
    public Object addPorduct(Product product) {
        Boolean result = restTemplate.exchange(PRODUCT_ADD_URL,HttpMethod.POST,new HttpEntity<Object>(product,httpHeaders),Boolean.class).getBody();
        return  result;
    }

}
