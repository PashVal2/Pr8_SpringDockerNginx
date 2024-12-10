package org.example.Job;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

public class QuickSort {
    public static void quickSort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Рекурсивно сортируем элементы до и после опорного элемента
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    // Метод для разделения массива на две части
    private static int partition(ArrayList<Integer> arr, int low, int high) {
        // Выбираем опорный элемент (в данном случае последний элемент массива)
        int pivot = arr.get(high);
        int i = (low - 1); // индекс меньшего элемента

        // Перебираем массив и меняем местами элементы, которые меньше опорного
        for (int j = low; j < high; j++) {
            if (arr.get(j) <= pivot) {
                i++;
                // Меняем элементы местами
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        // Меняем опорный элемент с элементом на позиции i + 1
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }
}
