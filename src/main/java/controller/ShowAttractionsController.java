package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Attraction;
import model.Park;
import util.DaoUtil;

public class ShowAttractionsController {
    public TableView<Attraction> table;
    public TextField park;
    public Button fill;
    public Button back;

    public void initialize() {
        SaveController saveController = new SaveController();
        saveController.backButton(back);

        TableColumn<Attraction, String> id = new TableColumn<>("ID");
        TableColumn<Attraction, String> name = new TableColumn<>("Име");
        TableColumn<Attraction, String> minAge = new TableColumn<Attraction, String>("Минимална възраст");

        minAge.setMinWidth(200);


        table.getColumns().addAll(id, name, minAge);

        fill.setOnAction(e -> {
            id.setCellValueFactory(
                    new PropertyValueFactory<Attraction, String>("id"));
            name.setCellValueFactory(
                    new PropertyValueFactory<Attraction, String>("name"));
            minAge.setCellValueFactory(
                    new PropertyValueFactory<Attraction, String>("minAge")
            );

            Park parkN = DaoUtil.findByName("Park", park.getText());
            final ObservableList<Attraction> data = FXCollections.observableArrayList(parkN.getAttractions());
            table.setItems(data);
        });
    }
}
