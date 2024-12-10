package org.example.Controller.Pr5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SessionPageController {
    @GetMapping("/sessionPage")
    public String getUserPreferences(HttpSession session, Model model) {
        String login = (String) session.getAttribute("login");
        String language = (String) session.getAttribute("language");
        String theme = (String) session.getAttribute("theme");

        model.addAttribute("login", login);
        model.addAttribute("language", language);
        model.addAttribute("theme", theme);

        return "sessionPage";
    }
}
