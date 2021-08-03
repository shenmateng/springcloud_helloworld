package com.mt.user.contorller;

import com.mt.user.pojo.User;
import com.mt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*根据id查询用户信息*/
    @GetMapping("/user")
    public User getUser(@RequestParam("id") Integer id) {
        User user = userService.getUserById(id);
        return user;
    }

    /*查询电影票信息*/
    @GetMapping("/buyMovie")
    public Map<String, Object> buyMovie(@RequestParam("id") Integer userId) {
        Map<String, Object> map = userService.buyMovie(userId);
        return map;
    }

}
