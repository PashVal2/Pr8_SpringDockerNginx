package org.example.Controller.Pr1;

import org.example.Model.Author;
import org.example.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @GetMapping("/author")
    public String getAuthor(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "author"; // Возвращает имя шаблона (index.html)
    }
}
