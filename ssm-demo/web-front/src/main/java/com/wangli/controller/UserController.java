package com.wangli.controller;

import com.wangli.pojo.User;
import com.wangli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangli
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/addOne")
    public String getUserById(String name, int age, Model model) {
        int result = userService.addUser(new User(name, age));
        if (result > 0) {
            return "success";
        }
        return null;
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public User getUserById(int id) {
        return userService.getUserById(id);
    }
}
