package org.example.Service;

import org.example.Model.Author;
import org.example.Model.LombardItem;
import org.example.Repository.AuthorRepository;
import org.example.Repository.LombardItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LombardItemService {
    @Autowired
    private LombardItemRepository lombardItemRepository;
    public List<LombardItem> getAllItems() {
        return lombardItemRepository.findAll();
    }
    public void saveAllItems(List<LombardItem> items) {
        lombardItemRepository.saveAll(items);
    }
    public void deleteAllItems(List<Long> items) {
        lombardItemRepository.deleteAllById(items);
    }
}
