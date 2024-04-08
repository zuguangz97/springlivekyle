package net.nvsoftware.springlive.controller;

import net.nvsoftware.springlive.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "NVSoftware Home";
    }
    @RequestMapping("/user")
    public User user() {
        User user = new User();
        user.setId("3100");
        user.setName("nvsoftware");
        user.setEmail("info@nvsoftware.net");
        return user;
    }
}
