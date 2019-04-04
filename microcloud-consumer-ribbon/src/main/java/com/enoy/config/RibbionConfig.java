package com.enoy.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

public class RibbionConfig {

    @Bean
    public IRule ribbonRule(){
        System.out.println("-------------");
        return new RandomRule();
    }
}
