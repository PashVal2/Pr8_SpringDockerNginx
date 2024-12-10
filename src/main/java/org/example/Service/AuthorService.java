package org.example.Service;

import org.example.Model.Author;
import org.example.Model.LombardItem;
import org.example.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void saveAllItems(List<Author> items) {
        authorRepository.saveAll(items);
    }

    public void deleteAllItems(List<Long> newItems) {
        authorRepository.deleteAllById(newItems);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
