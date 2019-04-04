package com.wwn;

import com.wwn.service.IProductService;
import com.wwn.vo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ProductApp.class)
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private IProductService productService;

    @Test
    public void testGet() {
        System.out.println(productService.get(1));
    }
    @Test
    public void testAdd() {
        Product dept = new Product() ;
        dept.setProductName("lison-" + System.currentTimeMillis());
        System.out.println(productService.add(dept));
    }
    @Test
    public void testList() {
        System.out.println(productService.list());
    }

}
