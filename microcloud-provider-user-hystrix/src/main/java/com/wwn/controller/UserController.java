package com.wwn.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wwn.vo.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @RequestMapping("/get/{name}")
    @HystrixCommand
    public  Object get(@PathVariable("name")String name) {
        Users users = new Users();
        users.setName(name);
        users.setAge(18);
        users.setSex("F");
        return users;
    }

}
