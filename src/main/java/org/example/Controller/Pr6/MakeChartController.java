package org.example.Controller.Pr6;

import org.example.Job.ChartCreator;
import org.example.Job.FixtureGenerator;
import org.example.Job.WatermarkUtil;
import org.example.Model.Fixture;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class MakeChartController {
    @Autowired
    private ChartCreator chartCreator;
    @Autowired
    private FixtureGenerator fixtureGenerator;
    @Autowired
    private WatermarkUtil watermarkUtil;
    @GetMapping("/makeChart")
    public String makeChart(Model model) {
        try {
            fixtureGenerator.saveFixtures();
            // 1
            JFreeChart chart = chartCreator.madeNewChart().getChart();
            chartCreator.saveChartIntoImage(chart, "chart1.png");
            watermarkUtil.addWatermark("src/main/resources/static/graph/chart1.png",
                    "src/main/resources/static/graph/chart1WT.png");
            // 2
            JFreeChart chart2 = chartCreator.madeNewChart2().getChart();
            chartCreator.saveChartIntoImage(chart2, "chart2.png");
            watermarkUtil.addWatermark("src/main/resources/static/graph/chart2.png",
                    "src/main/resources/static/graph/chart2WT.png");
            // 3
            JFreeChart chart3 = chartCreator.madeNewChart3().getChart();
            chartCreator.saveChartIntoImage(chart3, "chart3.png");
            watermarkUtil.addWatermark("src/main/resources/static/graph/chart3.png",
                    "src/main/resources/static/graph/chart3WT.png");
            model.addAttribute("chart", "Да");
            return "index";
        }
        catch (IOException e) {
            model.addAttribute("chart", "Нет");
        }
        return "index";
    }
    @GetMapping("/statistics")
    public String getStatistics() {
        return "statistics";
    }
}
