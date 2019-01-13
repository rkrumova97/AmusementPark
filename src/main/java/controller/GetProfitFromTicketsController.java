package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.util.Duration;
import model.Park;
import util.DaoUtil;

import java.util.List;

public class GetProfitFromTicketsController {
    public BarChart<String, Double> bar;
    public Button back;

    public void initialize() {
        List<Park> parks = DaoUtil.findAll("Park");
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        for(Park p : parks){
            series.getData().add(new XYChart.Data(p.getName(), p.getProfit()));
        }

        bar.getData().addAll(series);

        SaveController saveController = new SaveController();
        saveController.backButton(back);
    }
}
