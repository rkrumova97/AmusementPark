package controller;

import dao.ParkDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
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

        //setting the direction to arrange the data
        pieChart.setClockwise(true);

        //Setting the length of the label line
        pieChart.setLabelLineLength(50);

        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);
        Group root = new Group(pieChart);


        avg.insertText(0, ParkDao.getAvgAgeForAllParks().toString());

        SaveController saveController = new SaveController();
        saveController.backButton(back);
    }
}
