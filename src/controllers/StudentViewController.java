/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class StudentViewController implements Initializable {

    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private DatePicker dateDoB;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXTextField txtSearchLName;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private Text rbtnGender;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private JFXTextField txtFee;
    @FXML
    private DatePicker dateDaE;
    @FXML
    private JFXTextField txtCountry;
    @FXML
    private JFXTextField txtCity;
    @FXML
    private JFXTextField txtStudentID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
