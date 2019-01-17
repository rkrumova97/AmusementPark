package controller;

import dao.AttractionDao;
import dao.ManagerDao;
import dao.ParkDao;
import dao.TicketDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Attraction;
import model.Manager;
import model.Park;
import util.DaoUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SaveController {
    public TextField childName;
    public TextField age;
    public ComboBox<String> childParks;
    public MenuButton childAttractions;

    public TextField attractionName;
    public TextField minAge;

    public TextField parkName;
    public TextField price;
    public ComboBox<String> parkManager;
    public MenuButton parkAttractions;


    public TextField nameManager;
    public TextField salary;

    public Button backChild;
    public Button backAttraction;
    public Button backPark;
    public Button backManager;

    public Button saveManager;
    public Button saveChild;
    public Button saveAttraction;
    public Button savePark;

    public Tab parkTab;
    public Tab childTab;


    public void initialize() {
        childTab.setOnSelectionChanged(e -> {
            List<Park> parks = DaoUtil.findAll("Park");
            List<String> names = parks.stream().map(Park::getName).collect(Collectors.toList());
            ObservableList<String> list = FXCollections.observableArrayList(names);
            childParks.getItems().addAll(list);

            List<CheckMenuItem> checkMenuItems = getNames();
            childAttractions.getItems().addAll(checkMenuItems);
        });

        parkTab.setOnSelectionChanged(e -> {
            List<CheckMenuItem> checkMenuItems1 = getNames();
            parkAttractions.getItems().addAll(checkMenuItems1);

            List<Manager> managers = DaoUtil.findAll("Manager");
            List<String> namesManagers = managers.stream().map(Manager::getName).collect(Collectors.toList());
            ObservableList<String> listMan = FXCollections.observableArrayList(namesManagers);
            parkManager.getItems().addAll(listMan);
        });


        saveChild.setOnAction(e -> {
            String name = childName.getText();
            Integer ages = Integer.parseInt(age.getText());
            String park = childParks.getValue();
            final List<String> selectedItems = setComboBox(childAttractions);

            TicketDao.addTicket(name, ages, park, selectedItems);
        });

        saveAttraction.setOnAction(e -> {
            String attrName = attractionName.getText();
            Integer minAges = Integer.parseInt(minAge.getText());

            AttractionDao.addAttraction(attrName, minAges);
        });

        saveManager.setOnAction(e -> {
            String mname = nameManager.getText();
            Double msalary = Double.parseDouble(salary.getText());
            ManagerDao.addManager(mname, msalary);
        });

        savePark.setOnAction(e -> {
            String pname = parkName.getText();
            Double tp = Double.parseDouble(price.getText());
            String pm1 = parkManager.getValue();
            final List<String> selectedItems = setComboBox(parkAttractions);
            ParkDao.addPark(pname, pm1, tp, selectedItems);
        });

        backButton(backChild);
        backButton(backManager);
        backButton(backAttraction);
        backButton(backPark);
    }

    private List<CheckMenuItem> getNames() {
        List<Attraction> attractions = DaoUtil.findAll("Attraction");
        List<String> attractionNames = attractions.stream().map(Attraction::getName).collect(Collectors.toList());
        List<CheckMenuItem> checkMenuItems = new ArrayList<>();
        for (String s : attractionNames) {
            checkMenuItems.add(new CheckMenuItem(s));
        }
        return checkMenuItems;
    }

    private List<String> setComboBox(MenuButton parkAttractions) {
        return parkAttractions.getItems().stream()
                .filter(item -> CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());
    }

    void backButton(Button backAttraction) {
        backAttraction.setOnAction(e -> {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/templates/start.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Scene scene = new Scene(Objects.requireNonNull(root));
            stage.setScene(scene);
            stage.show();
        });
    }
}
