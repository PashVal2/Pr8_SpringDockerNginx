package org.example.Controller.Pr5;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/files")
public class UploadFilesController {
    // указание на директорию, отностельно текущей рабочей директории (/app)
    private final Path fileStorageLocation = Paths.get("src/main/resources/static/pdf").toAbsolutePath().normalize();
    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        Path path = Paths.get(String.valueOf(fileStorageLocation), fileName).normalize();
        if (Files.notExists(path)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Файл не найден");
        }
        try {
            byte[] fileContent = Files.readAllBytes(path);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(fileContent);
        }
        catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка");
        }
    }
}
