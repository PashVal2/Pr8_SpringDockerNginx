package org.example.Controller.Pr5;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {
    private final Path fileStorageLocation = Paths.get("src/main/resources/static/pdf/").toAbsolutePath().normalize();
    @GetMapping("/fileList")
    public String getFilesList(Model model) throws Exception {
        List<String> fileNames = Files.walk(fileStorageLocation, 1)
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());
        model.addAttribute("files", fileNames);
        return "fileList";
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".pdf")) {
                redirectAttributes.addAttribute("message", "Не тот тип файла");
                return "redirect:/fileList";
            }
            Path path = fileStorageLocation.resolve(file.getOriginalFilename()).normalize();
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            redirectAttributes.addAttribute("message", "Файл загружен: " + file.getOriginalFilename());
            return "redirect:/fileList";
        } catch (IOException e) {
            redirectAttributes.addAttribute("message", "Ошибка");
            return "redirect:/fileList";
        }
    }
}
