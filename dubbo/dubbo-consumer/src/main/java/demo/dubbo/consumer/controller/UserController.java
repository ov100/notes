package demo.dubbo.consumer.controller;

import com.alibaba.fastjson.JSON;
import demo.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : gb 2019/4/20
 */
@RestController("test/user")
public class UserController {

    @Reference(version = "${demo.service.version}")
    private UserService userService;

    @RequestMapping("info")
    public String getUser(){

        return JSON.toJSONString(userService.getUser());
    }
}
