package org.example.Controller.Pr2;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static org.example.Job.SvgGenerator.*;

@Controller
public class SvgController {
    @GetMapping("/svg")
    public String draw(@RequestParam(required = false) String num, Model model) {
        if (num != null) {
            int newNum = Integer.valueOf(num);
            // Извлекаем информацию о форме, цвете и размерах из числа
            String shape = getShape(newNum);
            String color = getColor(newNum);
            int size = getSize(newNum);

            // Формируем SVG на основе полученной информации
            String svg = generateSvg(shape, color, size);

            model.addAttribute("svg", svg);
        }
        return "svg";
    }
}
