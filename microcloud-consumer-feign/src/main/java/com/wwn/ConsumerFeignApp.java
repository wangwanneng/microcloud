package com.wwn;

import com.enoy.config.RibbionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RibbonClient(name = "MICROCLOUD-PROVIDER-PRODUCT",configuration = RibbionConfig.class)
@EnableEurekaClient
@EnableFeignClients("com.wwn.service")
public class ConsumerFeignApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignApp.class,args);
    }
}
