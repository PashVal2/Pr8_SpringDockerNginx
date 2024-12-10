package org.example.Controller.Pr3;

import org.example.Service.LombardItemService;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static org.example.Job.AuthChecker.isAdmin;
import static org.example.Job.AuthChecker.isAuth;
@Controller
public class LombardController {
    @Autowired
    private UserService userService;
    @Autowired
    private LombardItemService lombardItemService;
    @GetMapping("/register") // GET-запрос страници регистрации
    public String showRegistrationPage(Model model, Authentication authentication) {
        model.addAttribute("showLogout", isAuth(authentication));
        if(isAuth(authentication)) {
            model.addAttribute("name", authentication.getName());
        }
        return "register";
    }
    @PostMapping("/register") // POST-запрос для добавления нового юзера
    public String registerUser(String username, String password, Authentication authentication,
                               String confirmPassword, Model model) {
        model.addAttribute("showLogout", isAuth(authentication));
        if(isAuth(authentication)) {
            model.addAttribute("name", authentication.getName());
        }
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Пароли не совападают");
            return "register";
        }
        try {
            userService.register(username, password);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.toString());
            return "register";
        }
    }
    @GetMapping("/login") // GET-запрос страници входа
    public String showLoginPage(Model model, Authentication authentication) {
        model.addAttribute("showLogout", isAuth(authentication));
        if(isAuth(authentication)) {
            model.addAttribute("name", authentication.getName());
        }
        return "login";
    }
    @GetMapping("/lombard") // GET-запрос страници входа
    public String getLombardPage(Model model, Authentication authentication) {
        model.addAttribute("items", lombardItemService.getAllItems());
        model.addAttribute("showLogout", isAuth(authentication));
        model.addAttribute("ADMIN", isAdmin(authentication));
        if(isAuth(authentication)) {
            model.addAttribute("name", authentication.getName());
        }
        return "lombard";
    }
    @GetMapping("/users") // GET-запрос страници входа
    public String getUsersPage(Model model, Authentication authentication) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("showLogout", isAuth(authentication));
        if(isAuth(authentication)) {
            model.addAttribute("name", authentication.getName());
        }
        return "users";
    }
}
