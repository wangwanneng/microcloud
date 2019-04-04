package com.wwn.test;

import com.wwn.service.IMessageProvider;
import com.wwn.vo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMessageProvider {

    @Autowired
    private IMessageProvider messageProvider;

    @Test
    public void testSend() {
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("messageName");
        product.setProductDesc("desc");
        messageProvider.send(product);
    }

}
