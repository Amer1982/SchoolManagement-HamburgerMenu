/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class TeachersViewController implements Initializable {

    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private DatePicker dateDoB;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtCity;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtCountry;
    @FXML
    private JFXTextArea txtfAdditional;
    @FXML
    private ToggleGroup doctor;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
