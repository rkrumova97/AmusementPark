package controller;

import dao.ManagerDao;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Manager;

public class PromoteController {
    public Button promote;
    public TextField manager;
    public TextField profit;
    public TextField percent;
    public Label label;
    public Label newSalary;
    public Button back;

    public void initialize() {
        promote.setOnAction(e -> {
            String m = manager.getText();
            Double pr = Double.parseDouble(profit.getText());
            Double per = Double.parseDouble(percent.getText());

            Double salary = ManagerDao.promote(pr,per,m);

            label.setVisible(true);
            newSalary.setText(salary.toString());
        });

        SaveController saveController = new SaveController();
        saveController.backButton(back);
    }


}
