package org.example.Controller.Pr2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;

import static org.example.Job.QuickSort.quickSort;

@Controller
public class Sort {
    @GetMapping("/sort")
    public String getServerInfoWithAArray(@RequestParam(required = false) String arr, Model model) {
        if (arr != null) {
            ArrayList<Integer> sortArr = new ArrayList<Integer>();

            Arrays.stream(arr.split(" ")) // создается поток stream
                    .map(Integer::parseInt) // тут каждый элемент массива преобразуется
                    .forEach(sortArr::add); // тут тоже для кадого

            quickSort(sortArr, 0, sortArr.size() - 1);
            model.addAttribute("arr", sortArr.toString());
        }
        return "sort";
    }
}
