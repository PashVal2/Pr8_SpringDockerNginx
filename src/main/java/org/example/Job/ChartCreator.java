package org.example.Job;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Model.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ChartCreator {
    public ChartPanel madeNewChart2() throws IOException {
        List<Fixture> fixtures = loadFixtures();
        if (fixtures == null) {
            return new ChartPanel(null);
        }
        DefaultPieDataset dataset = new DefaultPieDataset();

        Map<String, Long> genderCounts = fixtures.stream()
                .collect(Collectors.groupingBy(Fixture::getGender, Collectors.counting()));
        genderCounts // (gender, count) -> dataset.setValue(gender, count)
                .forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart(
                "Распределение по полу",
                dataset
        );
        return new ChartPanel(chart);
    }
    public ChartPanel madeNewChart() throws IOException {
        List<Fixture> fixtures = loadFixtures();
        if (fixtures == null) {
            return new ChartPanel(null);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Статистика по возрастным категориям
        fixtures.stream()
                .map(Fixture::getAge)
                .collect(Collectors.groupingBy(this::getAgeCategory, Collectors.counting()))
                .forEach((category, count) -> {
                    dataset.addValue(count, "Количество", category);
                });

        JFreeChart chart = ChartFactory.createBarChart(
                "Возрастное распределение и пол",
                "Категории",
                "Количество",
                dataset
        );

        return new ChartPanel(chart);
    }
    public ChartPanel madeNewChart3() throws IOException {
        List<Fixture> fixtures = loadFixtures();
        if (fixtures == null) {
            return new ChartPanel(null);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Integer, Long> birthYearCounts = fixtures.stream()
                .collect(Collectors.groupingBy(Fixture::getBirthYear, Collectors.counting()));

        birthYearCounts
                .forEach((year, count) ->
                dataset.addValue(count, "Год рождения", String.valueOf(year)));

        JFreeChart chart = ChartFactory.createLineChart(
                "Распределение по году рождения",
                "Год рождения",
                "Кол-во",
                dataset
        );
        return new ChartPanel(chart);
    }
    public void saveChartIntoImage(JFreeChart chart, String fileName) throws IOException {
        if (chart != null) {
            try {
                String folderPath = "src/main/resources/static/graph/";
                File chartFile = new File(folderPath + fileName);
                ChartUtils.saveChartAsPNG(chartFile, chart, 800, 600);
            } catch (IOException e) {
                throw e;
            }
        } else {
            System.out.println("Ошибка при создании графика.");
        }
    }
    private List<Fixture> loadFixtures() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/static/json/fixtures.json");
        return objectMapper.readValue(file,
                new TypeReference<List<Fixture>>() {});
    }
    private String getAgeCategory(int age) {
        if (age < 20) {
            return "Меньше 18";
        } else if (age < 40) {
            return "18-39";
        } else if (age < 60) {
            return "40-59";
        } else {
            return "60 и больше";
        }
    }
}
