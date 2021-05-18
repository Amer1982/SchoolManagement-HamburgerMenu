/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class HomeViewController implements Initializable {

    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane MessageHolder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initPieChart();
    }

    private void initPieChart() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Expenses", 20d),
                new PieChart.Data("Students", 40d),
                new PieChart.Data("Income", 30d),
                new PieChart.Data("Profit", 10d)
        );
        pieChart.setData(data);
    }
}
