package spring.jpa.memcached.demo.user.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.jpa.memcached.demo.user.application.commons.mapper.UserMapper;
import spring.jpa.memcached.demo.user.application.commons.vo.UserVO;
import spring.jpa.memcached.demo.user.domain.model.User;
import spring.jpa.memcached.demo.user.domain.service.UserService;

@SuppressWarnings({"SameReturnValue", "PlaceholderCountMatchesArgumentCount"})
@Controller
public class UserController {


    final UserService userService;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @GetMapping("/")
    public String showLoginFormRedirect(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/authenticate")
    public String authUser(UserVO userVO, RedirectAttributes redirectAttributes) {
        try{
            if(userVO.getEmail().isEmpty() || userVO.getPassword().isEmpty() ){
                return "login";
            }
            User authUser = userService.processUserLogin(userVO.getEmail(), userVO.getPassword());
            if (authUser.getRole().equals("ADMIN")){
                redirectAttributes.addFlashAttribute("user", authUser);
                return "redirect:/admin/homepage";
            }
            if (authUser.getRole().equals("USER")){
                redirectAttributes.addFlashAttribute("user", authUser);
                return "redirect:/homepage";
            }
            return "login";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserVO userVO = new UserVO();
        model.addAttribute("user", userVO);
        return "registration";
    }

    @PostMapping("/register-submit")
    public String registerUserAccount(UserVO userVO, RedirectAttributes redirectAttributes)  {
        try {
            if(!userVO.getPassword().equals(userVO.getMatchingPassword())){
                throw new Exception("Passwords do not match");
            }

            User user = new User();
            user.setUsername( userVO.getUsername());
            user.setPassword( userVO.getPassword());
            user.setEmail( userVO.getEmail());
            user.setRole( userVO.getRole());

            User result = userService.registerNewUserAccount(user);
            if (result.getRole().equals("ADMIN")){
                redirectAttributes.addFlashAttribute("user", result);
                return "redirect:/admin/homepage";
            }
            if (result.getRole().equals("USER")){
                redirectAttributes.addFlashAttribute("user", result);
                return "redirect:/homepage";
            }
            return "registration";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        }
    }
}
