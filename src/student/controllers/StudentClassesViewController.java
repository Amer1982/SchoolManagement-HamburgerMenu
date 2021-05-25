/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class StudentClassesViewController implements Initializable {

    @FXML
    private JFXTextField txtSubject;
    @FXML
    private JFXTextField txtAbbrevation;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXComboBox<?> cmbbAssignTeacher;
    @FXML
    private TableColumn<?, ?> tblAbbrevation;
    @FXML
    private TableColumn<?, ?> tblSubject;
    @FXML
    private TableColumn<?, ?> tblAssignedTchr;
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
