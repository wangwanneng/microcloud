package com.wwn.controller;

import com.wwn.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

   public static final String PRODUCT_TOPIC = "xx";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     *  加密之后的调用方式
     */
    @RequestMapping("/product/get/{id}")
    @ResponseBody
    public Object getProduct(@PathVariable("id") long id) {

        ServiceInstance serviceInstance = loadBalancerClient.choose(PRODUCT_TOPIC);
        URI uri = URI.create(String.format("http://%s:%s/product/get/%s", serviceInstance.getHost(), serviceInstance.getPort(),id));
        Product product = restTemplate.exchange(uri, HttpMethod.GET,new HttpEntity<Product>(httpHeaders),Product.class).getBody();
        return  product;
    }

    @RequestMapping("/product/list")
    @ResponseBody
    public  Object listProduct() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(PRODUCT_TOPIC);
        URI uri = URI.create(String.format("http://%s:%s/product/list",serviceInstance.getHost(),serviceInstance.getPort()));
        List<Product> list = restTemplate.exchange(uri,HttpMethod.GET,new HttpEntity<List>(httpHeaders),List.class).getBody();
        return  list;
    }

        //这个是报错的，根本就通不过 直接配置在application.properties中有问题，还不知道怎么解决
    @RequestMapping("/product/add")
    @ResponseBody
    public Object addPorduct(Product product) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(PRODUCT_TOPIC);
        URI uri = URI.create(String.format("http://%s:%s/product/add", serviceInstance.getHost(), serviceInstance.getPort()));
        Boolean result = restTemplate.exchange(uri,HttpMethod.POST,new HttpEntity<Object>(product,httpHeaders),Boolean.class).getBody();
        return  result;
    }

}
