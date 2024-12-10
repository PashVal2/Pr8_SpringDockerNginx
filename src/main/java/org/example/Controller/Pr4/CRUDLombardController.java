package org.example.Controller.Pr4;

import org.example.Model.ItemIdDTO;
import org.example.Model.LombardItem;
import org.example.Service.LombardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CRUDLombardController {
    @Autowired
    private LombardItemService lombardItemService;
    @GetMapping("/lombard")
    public ResponseEntity<List<Map<String, Object>>> getLombardItem() {
        List<LombardItem> lombardItems = lombardItemService.getAllItems();
        List<Map<String, Object>> maps = new ArrayList<>();
        lombardItems.stream()
                .map(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", item.getId());
                    map.put("name", item.getName());
                    map.put("description", item.getDescription());
                    map.put("cost", item.getCost());
                    return map;
                })
                .forEach(maps::add);
        return ResponseEntity.ok(maps);
    }
    @PostMapping("/lombard")
    public ResponseEntity<String> postLom(@RequestBody List<LombardItem> items) {
        try {
            lombardItemService.saveAllItems(items);
            return ResponseEntity.ok("Данные добавлены");
        }
        catch (Exception e) {
            return ResponseEntity.ok("Данные не добавлены");
        }
    }
    @DeleteMapping("/lombard")
    public ResponseEntity<String> deleteLom(@RequestBody List<ItemIdDTO> items) {
        try {
            List<Long> newItems = new ArrayList<>();
            items.stream()
                    .map(ItemIdDTO::getId)
                    .forEach(newItems::add);
            lombardItemService.deleteAllItems(newItems);
            return ResponseEntity.ok("Данные удалены");
        }
        catch (Exception e) {
            return ResponseEntity.ok("Данные не удалены");
        }
    }
    @PutMapping("/lombard")
    public ResponseEntity<String> putLom(@RequestBody List<LombardItem> items) {
        try {
            lombardItemService.saveAllItems(items);
            return ResponseEntity.ok("Данные изменены");
        }
        catch (Exception e) {
            return ResponseEntity.ok("Данные не изменены");
        }
    }
}
