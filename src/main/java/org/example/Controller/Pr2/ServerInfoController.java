package org.example.Controller.Pr2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.example.Job.UnixClass.unixCommand;

@Controller
public class ServerInfoController {
    @PostMapping("/serverInfo")
    public String postServerInfo(@RequestParam String command, Model model) {
        try {
            // Выполнение команды
            String output = unixCommand(command);
            model.addAttribute("output", output);
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при выполнении команды: " + e.getMessage());
        }
        return "serverInfo";
    }
    @GetMapping("/serverInfo")
    public String getServerInfoWithCommand(@RequestParam(required = false) String command, Model model) {
        try {
            if (command != null) {
                // Выполнение команды с параметром
                String output = unixCommand(command);
                model.addAttribute("output", output);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при выполнении команды: " + e.getMessage());
        }
        return "serverInfo";
    }
}
