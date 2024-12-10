package org.example.Job;

import org.springframework.stereotype.Component;

public class SvgGenerator {
    public static String getShape(int num) {
        // Пример использования битовых операций для извлечения формы (например, первые 2 бита)
        int shapeCode = num & 0b11; // последние 2 бита
        switch (shapeCode) {
            case 0: return "circle";
            case 1: return "rectangle";
            case 2: return "triangle";
            default: return "unknown";
        }
    }

    public static String getColor(int num) {
        // Пример использования битовых операций для извлечения цвета (следующие 2 бита)
        int colorCode = (num >> 2) & 0b11; // сдвиг на 2 бита и извлечение следующих 2 битов
        switch (colorCode) {
            case 0: return "red";
            case 1: return "green";
            case 2: return "blue";
            default: return "black";
        }
    }

    public static int getSize(int num) {
        // Пример извлечения размеров фигуры (последующие биты)
        int size = (num >> 4) & 0b1111; // сдвиг на 4 бита и извлечение следующих 4 битов
        return size * 10;
    }

    public static String generateSvg(String shape, String color, int size) {
        switch (shape) {
            case "circle":
                return "<svg width=\"" + (size * 2) + "\" height=\"" + (size * 2) + "\">" +  // размер SVG
                        "<circle cx=\"" + size + "\" cy=\"" + size + "\" r=\"" + size + "\" stroke=\"" + color + "\" stroke-width=\"4\" fill=\"" + color + "\" />" +
                        "</svg>";
            case "rectangle":
                int width = size;
                int height = size;
                return "<svg width=\"" + width + "\" height=\"" + height + "\">" +
                        "<rect width=\"" + width + "\" height=\"" + height + "\" stroke=\"" + color + "\" stroke-width=\"4\" fill=\"" + color + "\" />" +
                        "</svg>";
            case "triangle":
                int tHeight = size;
                int tBase = size;
                return "<svg width=\"" + tBase + "\" height=\"" + tHeight + "\">" +
                        "<polygon points=\"" + (tBase / 2) + ",10 " + tBase + "," + tHeight + " 10," + tHeight + "\" stroke=\"" + color + "\" stroke-width=\"4\" fill=\"" + color + "\" />" +
                        "</svg>";
            default:
                return "<svg width=\"400\" height=\"100\"><text x=\"10\" y=\"20\">Неизвестная форма</text></svg>";
        }
    }
}
