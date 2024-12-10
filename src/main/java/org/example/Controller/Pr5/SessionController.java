package org.example.Controller.Pr5;

import org.example.Model.UserPreferencesDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/session")
public class SessionController {
    @PostMapping("/userPref")
    public ResponseEntity<String> postUserPreferences(
            HttpSession session, @RequestBody UserPreferencesDTO userPreferencesDTO) {
        session.setAttribute("login", userPreferencesDTO.getLogin());
        session.setAttribute("theme", userPreferencesDTO.getTheme());
        session.setAttribute("language", userPreferencesDTO.getLanguage());

        return ResponseEntity.ok("Сохранено");
    }
}
