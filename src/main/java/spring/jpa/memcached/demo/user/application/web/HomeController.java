package spring.jpa.memcached.demo.user.application.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SuppressWarnings("SameReturnValue")
@Controller
public class HomeController {

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user/homepage")
    public String login(){
        return "homepage";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/homepage")
    public String loginAdmin(){
        return "homepage_admin";
    }
}
