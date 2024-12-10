package org.example.Controller.Pr4;

import org.example.Model.Author;
import org.example.Model.ItemIdDTO;
import org.example.Model.LombardItem;
import org.example.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CRUDAuthor {
    @Autowired
    private AuthorService authorService;
    @GetMapping("/author")
    public ResponseEntity<List<Map<String, Object>>> getApiAuthor() {
        List<Author> authors = authorService.getAllAuthors();
        List<Map<String, Object>> maps = new ArrayList<>();
        authors.stream()
                .map(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", item.getId());
                    map.put("name", item.getName());
                    map.put("surname", item.getSurname());
                    return map;
                })
                .forEach(maps::add);
        return ResponseEntity.ok(maps);
    }
    @PostMapping("/author")
    public ResponseEntity<String> postAuthor(@RequestBody List<Author> items) {
        try {
            authorService.saveAllItems(items);
            return ResponseEntity.ok("Данные добавлены");
        }
        catch (Exception e) {
            return ResponseEntity.ok("Данные не добавлены");
        }
    }
    @DeleteMapping("/author")
    public ResponseEntity<String> deleteAuthor(@RequestBody List<ItemIdDTO> items) {
        try {
            List<Long> newItems = new ArrayList<>();
            items.stream()
                    .map(ItemIdDTO::getId)
                    .forEach(newItems::add);
            authorService.deleteAllItems(newItems);
            return ResponseEntity.ok("Данные удалены");
        }
        catch (Exception e) {
            return ResponseEntity.ok("Данные не удалены");
        }
    }
    @PutMapping("/author")
    public ResponseEntity<String> putAuthor(@RequestBody List<Author> items) {
        try {
            authorService.saveAllItems(items);
            return ResponseEntity.ok("Данные изменены");
        }
        catch (Exception e) {
            return ResponseEntity.ok("Данные не изменены");
        }
    }
}
