package controller;

import dao.ParkDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Park;
import util.DaoUtil;

import java.util.ArrayList;
import java.util.List;

public class AverageAgesController {
    public PieChart pieChart;
    public TextField avg;
    public Button back;

    private ObservableList<PieChart.Data> createPie() {
        List<Park> parks = DaoUtil.findAll("Park");
        List<PieChart.Data> data = new ArrayList<>();
        for (Park park : parks) {
            PieChart.Data pd = new PieChart.Data(park.getName(), ParkDao.getAvgAge(park));
            data.add(pd);
        }
        return FXCollections.observableArrayList(data);
    }

    public void initialize() {
        pieChart.setTitle("Average ages in the parks:");
        pieChart.setData(createPie());


        avg.insertText(0, ParkDao.getAvgAgeForAllParks().toString());

        SaveController saveController = new SaveController();
        saveController.backButton(back);
    }
}
